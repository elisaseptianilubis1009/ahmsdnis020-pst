package id.co.ahm.sd.nis.app020.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.sd.nis.app020.vo.Nis020VoGeonetBackendUtil;


public class Nis020GeonetBackendUtil {
	
	public static Nis020VoGeonetBackendUtil retrieveObjFromQueryWithParam(Session currentSession,String sql,
			String[] param,Object[] paramval,String tableName){
		
        Query qTotal     = currentSession.createSQLQuery(countQuery(sql, tableName));
        Query qList      = currentSession.createSQLQuery(sql);
        
        int i=0;
        for (String p : param) {
        	qTotal.setParameter(p, paramval[i]);
        	i++;
		}
        
        int x=0;
        for (String p : param) {
        	qList.setParameter(p, paramval[x]);
        	x++;
		}
 

        int total = (int) qTotal.uniqueResult();
        Nis020VoGeonetBackendUtil vo = new Nis020VoGeonetBackendUtil();
        vo.setTotal(total);
        vo.setList(qList.list());
        return vo;
        
	}
	
	public static Nis020VoGeonetBackendUtil retrieveObjFromQuery(Session currentSession,String[] params, 
			DtoParamPaging input,String sql,String tableName){
		
		String sortby   = "";
		if(input != null && input.getSort()!= null && input.getSort().trim().length()>0){
			sortby = "order by "+input.getSort();
		}
		
		String orderby   = "";
		if(input != null && input.getOrder()!= null && input.getOrder().trim().length()>0){
			orderby =  " "+input.getOrder();
		}
		
		if(sortby.length()>0){
			sortby = sortby + orderby;
		}
		
		
        Query qTotal     = currentSession.createSQLQuery(countQuery(getSqlWithParam(input, sql, params, sortby,false), tableName));
        Query qList      = currentSession.createSQLQuery(getSqlWithParam(input, sql, params, sortby,true));

        
        if(input != null && params != null){
        	setParameterLovWithParam(qTotal, input, params );
        	setParameterLovWithParam(qList, input, params );
       }
        
        qList.setFirstResult(input.getOffset());
        qList.setMaxResults(input.getLimit());

        BigDecimal total = (BigDecimal) qTotal.uniqueResult();
        Nis020VoGeonetBackendUtil vo = new Nis020VoGeonetBackendUtil();
        vo.setTotal(total.intValue());
        vo.setList(qList.list());
        return vo;
        
	}

	public static Nis020VoGeonetBackendUtil retrieveObj(Session currentSession, DtoParamPaging input, boolean isUseFilter, 
			boolean isUseResultTransformer,Class clazz,Class clazzVoPopulation){
		
		String sql       = getSql(input, isUseFilter, clazz, true);
		String sqlCount  = getSql(input, isUseFilter, clazz, false);
		String tableName = getTableName(clazz);
        Query qTotal     = currentSession.createSQLQuery(countQuery(sqlCount, tableName));
        Query qList      = currentSession.createSQLQuery(sql);
 
       
        
        if(input != null){
        	 setParameterLov(qTotal, input, clazz );
             setParameterLov(qList, input, clazz);
        	
        	   qList.setFirstResult(input.getOffset());
               qList.setMaxResults(input.getLimit());
        }
     

        int total = (int) qTotal.uniqueResult();
        Nis020VoGeonetBackendUtil vo = new Nis020VoGeonetBackendUtil();
        vo.setTotal(total);
        if(!isUseResultTransformer){
        	  vo.setList(qList.list());
        }else{
        	  qList.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	  List<HashMap<String, Object>> list = qList.list();
        	  Integer index = 1;
        	  for (HashMap<String, Object> obj : list) {
        		 try {
        			 Object objVo = clazzVoPopulation.getConstructor().newInstance();
        			 objVo = Nis020ObjectUtiliti.copyObjectFromMap(obj, objVo);
        			 if(isContainsFieldNo(clazzVoPopulation)){
        				 PropertyUtils.setProperty(objVo, "no", index);
        			 }
        			 vo.getListObj().add(objVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
        		 index++;
			}
        }
      
       
        return vo;
        
	}
	
	private static String getTableName(Class clazz) {
		Annotation[] annheader = clazz.getAnnotations();
	 	for (Annotation a : annheader) {
	 		if(a instanceof Table){
	 			Table tbl = (Table) a;
	 			return tbl.name();
	 		}
	 		if(a instanceof Nis020GeonetHeader) {
	 			Nis020GeonetHeader h = (Nis020GeonetHeader) a;
	 			return h.tableName();
	 		}
		}
	 	
	 	
		return null;
	}

	private static boolean isContainsFieldNo(Class clazz) {
		try {
			Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
            	if(field.getName().toLowerCase().contains("no")){
            		return true;
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	static String countQuery(String sqlQuery, String tableName) {
	        return "SELECT COUNT(*) FROM (" + sqlQuery + " ) "+tableName;
	    }
	
private static void setParameterLov(Query q, DtoParamPaging input, Class clazz) {
		
		Field[] fields = clazz.getDeclaredFields();
	 	for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation ann : annotations) {
				if(ann instanceof Nis020GeonetUtil){
					Nis020GeonetUtil geo = (Nis020GeonetUtil)ann;
					if(geo.isSearchField()){
						Object obj = getObjFromMap(field.getName(), input.getSearch());
						if (obj != null  && !String.valueOf(obj).isEmpty()) {
							if(geo.dateFormat().length()>0){
								q.setParameter(field.getName(), DateUtil.stringToDate(String.valueOf(obj), geo.dateFormat()));	
							}else{
								 q.setParameter(field.getName(), obj);	
							}
							
				        }
						
						if(geo.joinFieldQuery().length()>0){
							Object objjoin = getObjFromMap(geo.joinFieldQuery(), input.getSearch());
							if (objjoin != null  && !String.valueOf(objjoin).isEmpty()) {
								if(geo.dateFormatJoinQuery().length()>0){
									 q.setParameter(geo.joinFieldQuery(), DateUtil.stringToDate(String.valueOf(objjoin), geo.dateFormat()));	
								}else{
									 q.setParameter(geo.joinFieldQuery(), objjoin);	
								}
								
					        }
							
						}
					}else if(geo.embedSearchField().trim().length()>0){
						String embed  = geo.embedSearchField().trim();
						if(embed.contains("#")){
							String[] split = embed.split("#");
							for (String s : split) {
								if (input.getSearch().get(s) != null) {
									Object obj =  getObjFromMap(s, input.getSearch());
									if (obj != null  && !String.valueOf(obj).isEmpty()) {
										if(geo.dateFormat().length()>0){
											q.setParameter(s, DateUtil.stringToDate(String.valueOf(obj), geo.dateFormat()));	
										}else{
											q.setParameter(s, obj);		
										}
										 
									}
									
						        }
							}
							
						}else{
							Object obj = getObjFromMap(embed, input.getSearch());
							if (obj != null  && !String.valueOf(obj).isEmpty()) {
								if(geo.dateFormat().length()>0){
									q.setParameter(embed, DateUtil.stringToDate(String.valueOf(obj), geo.dateFormat()));	
								}else{
									q.setParameter(embed, obj);		
								}
								 
							}
						}
						
					}
				}
			}
		}
	 
    }

private static void setParameterLovWithParam(Query q, DtoParamPaging input, String[] params) {
	
    if(input != null && params != null){
    	for (String p : params) {
    		if(p.contains("#")){
    			
    			 String[] split = p.split("#");
    			 String operator = split[0];
    			 String param = split[1];
        		 Object obj = getObjFromMap(param,input.getSearch());
        		if(obj != null && !String.valueOf(obj).isEmpty()){
        			if(obj.getClass().getSimpleName().equalsIgnoreCase("String")){
        				q.setParameter(param, "%"+((String)obj).toLowerCase()+"%" );
        			}else{
        				q.setParameter(param, obj );
        			}
        		}
    			
    		}else{
        		 Object obj = getObjFromMap(p,input.getSearch());
        		if(obj != null && !String.valueOf(obj).isEmpty()){
        			if(obj.getClass().getSimpleName().equalsIgnoreCase("String")){
        				q.setParameter(p, "%"+((String)obj).toLowerCase()+"%" );
        			}else{
        				q.setParameter(p, obj );
        			}
        		}
    		}
    		
		}
   	
   }
 
}

private static String getSqlWithParam(DtoParamPaging input,String sql,String[] params, String orderby, boolean isUseOrderby) {
	 if( params != null && params.length>0){
		 
		 boolean isUseParam = false;
		 String sqlParam = "";
		 int countParam = 0;
		 for (String p : params) {
			 if(p.contains("#")){
				 String[] split = p.split("#");
				 String operator = split[0];
				 String param = split[1];
				 
				 Object obj = getObjFromMap(param,input.getSearch());
				 if(obj != null && !String.valueOf(obj).isEmpty()){
					 countParam++;
					 isUseParam = true;
					 if(countParam == 1){
						 sqlParam += " (LOWER("+param+") LIKE :"+param+" ) ";	
					 }else{
						 sqlParam += " "+operator+" (LOWER("+param+") LIKE :"+param+" ) ";	
					 }
					 
				 }
			 }else{
				 
				 Object obj = getObjFromMap(p,input.getSearch());
				 if(obj != null && !String.valueOf(obj).isEmpty()){
					 countParam++;
					 isUseParam = true;
					 sqlParam += " (LOWER("+p+") LIKE :"+p+" ) ";	
					 
				 }
			 }
			 
			 
		}
		 
		 if(isUseParam){
			 sql = sql +" AND ("+sqlParam+") ";
		 }
		
	 	if(isUseOrderby && orderby.trim().length()>0){
	 		return sql +=  orderby;
	 	}else{
	 		return sql;
	 	}
	 	
	 }
	 return sql;
   }
	
	 private static String getSql(DtoParamPaging input,boolean isUseFilter, Class clazz, boolean isUseOrderBy) {
		 String sql = getSqlHeader(clazz, "default");
		 if( isUseFilter){
		 	Field[] fields = clazz.getDeclaredFields();
		 	for (Field field : fields) {
				Annotation[] annotations = field.getAnnotations();
				for (Annotation ann : annotations) {
					if(ann instanceof Nis020GeonetUtil){
						Nis020GeonetUtil geo = (Nis020GeonetUtil)ann;
						if(geo.isSearchField()){
							sql += getSearchQuery(field.getName(), geo, input);
						}else if(geo.embedSearchField().trim().length()>0){
							String embed  = geo.embedSearchField().trim();
							if(embed.contains("#")){
								String[] split = embed.split("#");
								for (String s : split) {
									sql += getSearchQuery(s, geo, input);
								}
							}else{
								sql += getSearchQuery(embed, geo, input);
								
							}
						}
					}
				}
			}
		 
		 }
		 
		 String order = getSqlHeader(clazz, "order");
		 	if(isUseOrderBy){
		 		
		 		
				String sortby   = "";
				if(input != null && input.getSort()!= null && input.getSort().trim().length()>0){
					sortby = "order by "+input.getSort();
				}
		 		
		 		String orderby   = " desc";
				if(input != null && input.getOrder()!= null && input.getOrder().trim().length()>0){
					orderby = input.getOrder();
				}
				
				
				if(sortby.length()>0){
					sortby =  sortby+" "+ orderby;
				}else{
					sortby = order;
				}
		 		
		 		return sql +=  sortby;
		 	}else{
		 		return sql += order;
		 	}
		 	
	        
	    }
	 
	 private static String getSearchQuery(String key, Nis020GeonetUtil geo, DtoParamPaging input) {
		 String sql ="";
		 Object obj = getObjFromMap(key, input.getSearch());
			if ( obj != null && !String.valueOf(obj).isEmpty()) {
				if(geo.query().length()>0){
					if(geo.joinFieldQuery().length()>0){
						Object objjoin = getObjFromMap(geo.joinFieldQuery(), input.getSearch());
						if ( objjoin != null && !String.valueOf(objjoin).isEmpty()) {
							sql += geo.query();	
						}
					}else{
						sql += geo.query();	
					}
				}else{
					sql += " AND (LOWER("+key+") LIKE :"+key+" ) ";	
				}
			}
			
			return sql;
	}

	private static Object getObjFromMap(String key, Map<String, Object> map) {
		Object o =  map.get(key.toLowerCase());
		if(o == null){
			o =  map.get(key.toUpperCase());
		}
		return o;
	}

	private static String getSqlHeader(Class clazz,String key) {
			Annotation[] annheader = clazz.getAnnotations();
		 	for (Annotation a : annheader) {
		 		if(a instanceof Nis020GeonetHeader){
		 			Nis020GeonetHeader geo = (Nis020GeonetHeader) a;
		 			if(key.toLowerCase().contains("default")){
		 				return geo.sqldefault();
		 			}else if(key.toLowerCase().contains("order")){
		 				return geo.sqlorder();
		 			}
		 			
		 		}
			}
			return null;
		}
	 
	
	 
	
		
}
