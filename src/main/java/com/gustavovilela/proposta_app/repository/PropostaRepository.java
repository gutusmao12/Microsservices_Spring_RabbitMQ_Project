package com.gustavovilela.proposta_app.repository;

import com.gustavovilela.proposta_app.entity.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    List<Proposta> findAllByIntegradaIsFalse();
}
