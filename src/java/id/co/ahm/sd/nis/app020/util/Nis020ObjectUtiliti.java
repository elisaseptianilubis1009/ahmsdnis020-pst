package id.co.ahm.sd.nis.app020.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.beanutils.PropertyUtils;

public class Nis020ObjectUtiliti {

//	copy dari a ke b
    public final static Object copyObject(Object origin, Object dest) {
        try {
            PropertyUtils.copyProperties(dest, origin);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dest;
    }

    public final static Object copyObjectFromMap(HashMap<String, Object> origin, Object dest) {

        Field[] fields = dest.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obx = origin.get(field.getName());
            try {
                if (obx == null) {
                    obx = origin.get(field.getName().toLowerCase());
                }
                if (obx == null) {
                    obx = origin.get(field.getName().toUpperCase());
                }

                PropertyUtils.setProperty(dest, field.getName(), obx);
            } catch (Exception e) {

            }


        }

        return dest;
    }

    public final static Map<String, Object> getConstraintErr(Object x) {
        Map<String, Object> msg = new HashMap<>();
        Field[] declaredFields = x.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                Object ob = PropertyUtils.getProperty(x, field.getName());
                Annotation[] annotations = field.getAnnotations();
                for (Annotation ann : annotations) {
                    if (ann instanceof Size) {
                        Size s = (Size) ann;
                        if (ob != null) {
                            String st = (String) ob;
                            if (st.length() > s.max()) {
                                msg.put("err", "Jumlah maksimal karakter " + field.getName() + " harus " + s.max() + " karakter");
                            }
                        }
                    }

                    if (ann instanceof NotNull) {
                        if (ob == null) {
                            msg.put("err", "Kolom " + field.getName() + " wajib diisi");
                        }
                    }

                    if (ann instanceof Id) {
                        if (ob == null) {
                            msg.put("err", "Field " + field.getName() + " tidak boleh kosong");
                        }
                    }

                    if (ann instanceof EmbeddedId) {
                        if (ob == null) {
                            msg.put("err", "Field " + field.getName() + " tidak boleh kosong");
                        }
                    }
                }

            } catch (Exception e) {
            }
        }

        return msg;
    }
}
