package dev.devzero.api.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import dev.devzero.api.util.enums.SortingDirection;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Getter
@Setter
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable> extends CrudService<T, ID> {

	protected boolean distinct = false;

	public Page<T> findAll(int pageNo, int pageSize, String sortBy, String direction, BooleanExpression conditions) {
		return findAll(pageNo, pageSize, sortBy, direction, conditions, distinct);
	}

	public Page<T> findAll(int pageNo, int pageSize, List<Sort.Order> orders, BooleanExpression conditions) {
		return findAll(pageNo, pageSize, orders, conditions, distinct);
	}

	public Page<T> findAll(int pageNo, int pageSize, String sortBy, String direction, BooleanExpression conditions,
			boolean distinct) {

		Sort sortOrder = Sort.by((new Sort.Order(
				(SortingDirection.ASCENDING.getCode().equals(direction) ? Sort.Direction.ASC : Sort.Direction.DESC),
				sortBy).nullsLast()));

		return findAll(pageNo, pageSize, sortOrder, conditions, distinct);
	}

	public Page<T> findAll(int pageNo, int pageSize, List<Sort.Order> orders, BooleanExpression conditions,
			boolean distinct) {
		Sort sortOrder = Sort.by(orders);
		return findAll(pageNo, pageSize, sortOrder, conditions, distinct);
	}

	public Page<T> findAll(int pageNo, int pageSize, Sort sort, BooleanExpression conditions, boolean distinct) {
		int page = Math.max(pageNo / pageSize, 0);
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		return findAll(conditions, pageable, distinct);
	}

	public List<T> getPageContent(JPAQuery q) {
		return getContent(q);
	}

	public List<T> getContent(JPAQuery q) {
		return q.from(getQEntity()).fetch();
	}

	public static <T> Page<T> getPage(List<T> content, Pageable pageable, long dataCount) {

		if (pageable.getOffset() == 0) {
			return new PageImpl<>(content, pageable, dataCount);
		}
		if (pageable.getPageSize() > content.size()) {
			return new PageImpl<>(content, pageable, content.size());
		}

		if (!content.isEmpty() && pageable.getPageSize() > content.size()) {
			return new PageImpl<>(content, pageable, pageable.getOffset() + content.size());
		}

		return new PageImpl<>(content, pageable, dataCount);
	}

	private Page<T> findAll(BooleanExpression conditions, Pageable pageable, boolean distinct) {
		EntityPathBase<T> qentity = getQEntity();
		PathBuilder<T> entityPath = new PathBuilder<>(clazz, qentity.getMetadata());
		JPAQuery q = (JPAQuery) newJpaQuery().from(qentity);
		if (distinct) {
			q = (JPAQuery) q.distinct();
		}
		q = (JPAQuery) q.where(conditions);
		long dataCount = q.fetchCount();
		q.offset(pageable.getOffset());
		q.limit(pageable.getPageSize());

		if (pageable.getSort() != null) {
			for (Sort.Order order : pageable.getSort()) {
				if (order.getProperty().toUpperCase().endsWith(".TONUMBER")) {
					q = (JPAQuery) q.orderBy(new OrderSpecifier(Order.valueOf(order.getDirection().name()),
							Expressions.numberTemplate(Long.class,
									"TO_NUMBER(" + order.getProperty().replaceAll("\\.toNumber", "") + ")")));
				} else {
					PathBuilder path = entityPath.get(order.getProperty());
					q = (JPAQuery) q.orderBy(new OrderSpecifier(Order.valueOf(order.getDirection().name()), path));
				}
			}
		}
		return getPage(getPageContent(q), pageable, dataCount);
	}

}
