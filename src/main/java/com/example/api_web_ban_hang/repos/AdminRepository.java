package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
}
