package com.samir.studentapi.model.repository;

import com.samir.studentapi.model.entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
}
