package org.certificatic.spring.soba.dao.hibernate.impl;

import java.util.List;

import org.certificatic.spring.soba.dao.api.IAuthorityDAO;
import org.certificatic.spring.soba.domain.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAO extends GenericEntityDAO<Authority, Long> implements IAuthorityDAO {

	public AuthorityDAO() {
		super(Authority.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Authority> findByUserId(Long id) {
		return (List<Authority>) this.sessionFactory.getCurrentSession()
				.createQuery("FROM " + this.persistentClass.getName() + " WHERE user.customer.id = " + id).list();
	}

}
