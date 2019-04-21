package com.licenta.repository;

import com.licenta.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends JpaRepository<Credentials,Long> {
    Credentials findByUsername(String username);
}
