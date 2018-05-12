package com.auth.service;

import com.auth.bean.EsignSession;

public interface EsignSessionService {

	int save(EsignSession esignsession);
	
	EsignSession getByTxn(String txn);
	
}
