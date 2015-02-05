package ch.erni.community.idealab.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rap
 */
public abstract class CrudService<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public abstract Class<T> entityClass();

	public T findOne(String pkName, Object pkValue) {
		Map<String, Object> tmpMap = new HashMap<>();
		tmpMap.put(pkName, pkValue);
		return findOne(tmpMap);
	}

	public T findOne(Map<String, Object> primaryKeyValues) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass());

		Root<T> root = criteriaQuery.from(entityClass());

		for (Map.Entry<String, Object> pk : primaryKeyValues.entrySet()) {
			criteriaQuery.where(criteriaBuilder.equal(root.get(pk.getKey()), pk.getValue()));
		}

		criteriaQuery.select(root);

		List<T> results = entityManager.createQuery(criteriaQuery).getResultList();

		if (results.size() == 1) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public List<T> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass());

		Root<T> root = criteriaQuery.from(entityClass());

		criteriaQuery.select(root);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
