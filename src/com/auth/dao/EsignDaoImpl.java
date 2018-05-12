package com.auth.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auth.bean.Esign;
import com.auth.bean.UserLogin;

@Repository
@Transactional
public class EsignDaoImpl implements EsignDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Esign getByEsign_ID(int Esign_ID) {
		return (Esign) sessionFactory.getCurrentSession().get(Esign.class, Esign_ID);
	}

	public List<Esign> getAllEsignu(String Esign_USER) {

		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Esign.class);
		Criterion cn = Restrictions.eq("esign_request_by", Esign_USER);
		Criterion cn1 = Restrictions.eq("esign_status", 1);
		criteria.add(cn);
		criteria.add(cn1);
		return criteria.list();
	}

	public List<Esign> getAllEsign() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Esign.class);
		return criteria.list();
	}

	public int save(Esign esign) {
		return (Integer) sessionFactory.getCurrentSession().save(esign);
	}

	public void update(Esign esign) {
		sessionFactory.getCurrentSession().merge(esign);

	}

	public void view(Esign esign) {
		sessionFactory.getCurrentSession().merge(esign);

	}

	public int delete(int Esign_ID) {
		Esign e = getByEsign_ID(Esign_ID);
		sessionFactory.getCurrentSession().delete(e);
		return Esign_ID;

	}

	

	

}
