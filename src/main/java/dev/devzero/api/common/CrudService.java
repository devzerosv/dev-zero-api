package dev.devzero.api.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPADeleteClause;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings({ "rawtypes", "unchecked", "hiding" })
public abstract class CrudService<T extends BaseEntity<ID>, ID extends Serializable> extends BaseCrud<T, ID> {

	public abstract BaseRepository<T, ID> getRepository();

	public long count() {
		return getRepository().count();
	}

	public long count(Predicate p) {
		return getRepository().count(p);
	}

	public T findOne(ID id) {
		return findOne(id);
	}

	public Optional<T> findOne(Predicate predicate) {
		return getRepository().findOne(predicate);
	}

	public List<T> findAll() {
		return getRepository().findAll();
	}

	public List<T> findAll(Predicate predicate) {
		return (List<T>) getRepository().findAll(predicate);
	}

	public List<T> findAll(Predicate predicate, Sort sort) {
		return (List<T>) getRepository().findAll(predicate, sort);
	}

	public Page<T> findAll(Predicate predicate, Pageable pageable) {
		return getRepository().findAll(predicate, pageable);
	}

	public <S extends T> S save(S entity) {
		return getRepository().save(entity);
	}

	public <S extends T> List<S> save(Iterable<S> entities) {
		return getRepository().saveAll(entities);
	}

	public void delete(T entity) {
		beforeDelete(entity);
		getRepository().delete(entity);
	}

	public void delete(Iterable<T> iterable) {
		beforeDelete(iterable);
		getRepository().deleteAll(iterable);
	}

	public long removeOne(T entity) {
		EntityPathBase<T> qentity = getQEntity();
		return newJPADeleteClause().where((Expressions.numberPath(idclazz, qentity, "id").eq(entity.getId())))
				.execute();
	}

	public long remove(EntityPath<?> qentity, BooleanExpression bexp) {
		JPADeleteClause jpadelete = new JPADeleteClause(em, qentity);
		return jpadelete.where(bexp).execute();
	}

	protected <T extends BaseEntity> Collection<T> bulkSave(Collection<T> entities) {
		final List<T> savedEntities = new ArrayList<>(entities.size());
		int count = 0;
		for (T t : entities) {
			savedEntities.add(persistOrMerge(t));
			count++;
			if (count % BATCH_SIZE == 0) {
				em.flush();
				em.clear();
			}
		}

		em.flush();
		em.clear();
		return savedEntities;
	}

	protected <T extends BaseEntity> Collection<T> bulkSave(Collection<T> entities, int size) {
		final List<T> savedEntities = new ArrayList<>(entities.size());
		int count = 0;
		for (T t : entities) {
			savedEntities.add(persistOrMerge(t));
			count++;
			if (count % size == 0) {
				em.flush();
				em.clear();
			}
		}

		em.flush();
		em.clear();
		return savedEntities;
	}

	private <T extends BaseEntity> T persistOrMerge(T t) {
		if (t.getId() == null) {
			em.persist(t);
			return t;
		} else {
			return em.merge(t);
		}
	}

	public void beforeDelete(T entity) {

	}

	public void beforeDelete(Iterable<T> iterable) {

	}

}
