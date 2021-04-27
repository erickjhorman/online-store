package com.com.online.store.online.store.repository;

import com.com.online.store.online.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
