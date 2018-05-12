package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.bean.EsignSession;
import com.auth.dao.EsignSessionDAO;

@Service("esignsessionservice")
@Transactional("hibernateTransactionManager")
public class EsignSessionServiceImpl implements EsignSessionService{

	@Autowired
	EsignSessionDAO esignsession;
	
	@Override
	public int save(EsignSession esignsess) {
		// TODO Auto-generated method stub
		return esignsession.save(esignsess);
	}

	@Override
	public EsignSession getByTxn(String txn) {
		// TODO Auto-generated method stub
		return esignsession.getByTxn(txn);
	}

}
