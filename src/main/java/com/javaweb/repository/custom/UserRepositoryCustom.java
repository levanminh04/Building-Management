package com.javaweb.repository.custom;

import com.javaweb.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {
	List<UserEntity> findByRole(String roleCode);
	List<UserEntity> getAllUsers();
	int countTotalItem();
}
