package dev.devzero.api.common;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Log
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseCrud<T extends BaseEntity<ID>, ID extends Serializable> {

	protected final static int BATCH_SIZE = 100;
	protected Class clazz;
	protected Class idclazz;

	@PersistenceContext
	protected EntityManager em;

	protected JPAQuery query;

	public BaseCrud() {
		Type[] args = null;
		Type genericSuperclass = getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			args = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		}

		if (args != null && args.length >= 2) {
			clazz = (Class) args[0];
			idclazz = (Class) args[1];
		}
	}

	public Session getSession() {
		return em.unwrap(Session.class);
	}

	public JPAQuery newJpaQuery() {
		return new JPAQuery(em);
	}

	public long deleteOne(T entity) {
		EntityPathBase<T> qentity = getQEntity();
		return newJPADeleteClause().where(Expressions.numberPath(idclazz, qentity, "id").eq(entity.getId())).execute();
	}

	public JPADeleteClause newJPADeleteClause() {
		return new JPADeleteClause(em, getQEntity());
	}

	public JPADeleteClause newJPADeleteClause(EntityPath<?> entity) {
		return new JPADeleteClause(em, entity);
	}

	protected JPAUpdateClause newJPAUpdateClause(EntityPath<?> entity) {
		return new JPAUpdateClause(em, entity);
	}

	public EntityPathBase<T> getQEntity() {
		EntityPathBase<T> ret = null;
		try {
			int lastpoint = getClazz().getName().lastIndexOf('.');
			String qname = getClazz().getName();
			String sname = qname.substring(0, lastpoint) + ".Q" + qname.substring(lastpoint + 1, qname.length());
			Class claxx = Class.forName(sname);
			Constructor<?> ctor = claxx.getConstructor(String.class);
			String ctorArgument = StringUtils.uncapitalize(getClazz().getSimpleName());
			ret = (EntityPathBase<T>) ctor.newInstance(new Object[] { ctorArgument });
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			log.log(Level.SEVERE, null, ex);
		}
		return ret;
	}

	public void doWork(Work work) throws HibernateException {
		getSession().doWork(work);
	}

}
