package com.savitoh.multipledatasourcesdemo.cartorio.repository;

import com.savitoh.multipledatasourcesdemo.cartorio.model.Cartorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Long> {
}
