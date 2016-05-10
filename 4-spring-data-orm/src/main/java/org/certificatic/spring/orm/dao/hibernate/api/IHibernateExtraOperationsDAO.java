package org.certificatic.spring.orm.dao.hibernate.api;

import java.io.Serializable;

public interface IHibernateExtraOperationsDAO<T, ID extends Serializable> {

	void insert(final T entity, final Class<?>... validatorGroups);

	void detach(final T entity);

	void update(final T entity, final Class<?>... validatorGroups);
}
