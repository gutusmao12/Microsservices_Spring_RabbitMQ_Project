package com.gustavovilela.analisecredito.service.strategy.impl;

import com.gustavovilela.analisecredito.domain.Proposta;
import com.gustavovilela.analisecredito.service.strategy.CalculoPonto;

import java.util.Random;

public class PontuacaoScoreImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        int score = score();

        if(score <= 400) {
            throw new RuntimeException("Score abaixo do valor mínimo");
        } else if (score > 400 && score <= 600) {
            return 150;
        } else if (score > 600 && score <= 800) {
            return 180;
        } else {
            return 200;
        }
    }

    private int score() {
        return new Random().nextInt(0, 1000);
    }
}
