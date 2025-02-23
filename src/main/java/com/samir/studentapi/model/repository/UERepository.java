package com.samir.studentapi.model.repository;

import com.samir.studentapi.model.entity.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UERepository extends JpaRepository<UE, Long> {
}

