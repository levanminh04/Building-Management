package com.javaweb.repository.custom.impl;

import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserEntity> findByRole(String roleCode) {
		//JPQL
		String sql = "FROM UserEntity";
		Query query = entityManager.createNativeQuery(sql, UserEntity.class);
		return query.getResultList();
	}

	//	@Override
//	public List<UserEntity> getAllUsers(Pageable pageable) {
//
//		StringBuilder sql = new StringBuilder(buildQueryFilter())
//				.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
//				.append(" OFFSET ").append(pageable.getOffset());
//		System.out.println("Final query: " + sql.toString());
//
//		Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
//		return query.getResultList();
//	}
	@Override
	public List<UserEntity> getAllUsers() {

		StringBuilder sql = new StringBuilder(buildQueryFilter());
		System.out.println("Final query: " + sql.toString());

		Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
		return query.getResultList();
	}

	@Override
	public int countTotalItem() {
		String sql = buildQueryFilter();
		Query query = entityManager.createNativeQuery(sql.toString());
		return query.getResultList().size();
	}

	private String buildQueryFilter() {
		String sql = "SELECT * FROM user u WHERE u.status = 1";
		return sql;
	}
}
