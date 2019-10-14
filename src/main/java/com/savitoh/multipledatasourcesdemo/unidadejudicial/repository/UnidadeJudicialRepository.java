package com.savitoh.multipledatasourcesdemo.unidadejudicial.repository;

import com.savitoh.multipledatasourcesdemo.unidadejudicial.model.UnidadeJudicial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeJudicialRepository extends JpaRepository<UnidadeJudicial, Long> {
}
