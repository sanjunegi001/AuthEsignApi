package com.auth.dao;

import java.util.List;

import com.auth.bean.Esign;


public interface EsignDao {

	Esign getByEsign_ID(int Esign_ID);

	List<Esign> getAllEsign();

	List<Esign> getAllEsignu(String Esign_USER);

	int save(Esign esign);

	void update(Esign esign);

	void view(Esign esign);

	int delete(int Esign_ID);


}
