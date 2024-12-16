package com.gustavovilela.analisecredito.service.strategy.impl;

import com.gustavovilela.analisecredito.domain.Proposta;
import com.gustavovilela.analisecredito.service.strategy.CalculoPonto;

import java.util.Random;

public class EmprestimosEmAndamento implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return emprestimosEmAndamento() ? 0 : 80;
    }

    private boolean emprestimosEmAndamento() {
        return new Random().nextBoolean();
    }
}
