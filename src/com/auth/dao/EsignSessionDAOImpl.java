package com.auth.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.auth.bean.EsignSession;
import com.auth.dao.EsignSessionDAO;



@Repository
public class EsignSessionDAOImpl implements EsignSessionDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int save(EsignSession esignsession) {
		// TODO Auto-generated method stub
		return (Integer) sessionFactory.getCurrentSession().save(esignsession);
	}

	@Override
	public EsignSession getByTxn(String txn) {
		// TODO Auto-generated method stub
		Criteria query = sessionFactory.getCurrentSession().createCriteria(EsignSession.class);
		query.add(Restrictions.eq("transaction_id", txn));
		return (EsignSession) query.setMaxResults(1).uniqueResult();
	}

}
