package com.auth.dao;

import com.auth.bean.EsignSession;

public interface EsignSessionDAO {

	int save(EsignSession esignsession);
	
	EsignSession getByTxn(String txn);
	
}
