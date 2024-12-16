package com.gustavovilela.analisecredito.service.strategy;

import com.gustavovilela.analisecredito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);
}
