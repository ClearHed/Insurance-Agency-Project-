package com.InsuranceAgency.userservice.repo;

import com.InsuranceAgency.userservice.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
