package com.licenta.repository;

import com.licenta.models.ApplicationUser;

public interface ApplicationUserRepository extends Repository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);

}
