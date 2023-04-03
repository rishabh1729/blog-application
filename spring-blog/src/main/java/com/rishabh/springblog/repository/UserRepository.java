package com.rishabh.springblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.springblog.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByUserName(String username);

}
