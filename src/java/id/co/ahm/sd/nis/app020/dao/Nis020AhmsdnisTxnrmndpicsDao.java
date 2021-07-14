/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app020.dao;

import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnrmndpics;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnrmndpicsPK;
import id.co.ahm.sd.nis.app020.vo.Nis020VoTable;

/**
 *
 * @author admin
 */
public interface Nis020AhmsdnisTxnrmndpicsDao extends DefaultDao<AhmsdnisTxnrmndpics, AhmsdnisTxnrmndpicsPK>{
    
	public  Nis020VoTable retrieve(DtoParamPaging input, Class clazz);
 
    
}
