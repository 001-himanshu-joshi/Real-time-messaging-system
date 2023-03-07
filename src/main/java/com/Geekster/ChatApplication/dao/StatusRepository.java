package com.Geekster.ChatApplication.dao;

import com.Geekster.ChatApplication.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
