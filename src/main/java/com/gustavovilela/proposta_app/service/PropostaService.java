package com.gustavovilela.proposta_app.service;

import com.gustavovilela.proposta_app.dto.PropostaRequestDto;
import com.gustavovilela.proposta_app.dto.PropostaResponseDto;
import com.gustavovilela.proposta_app.entity.Proposta;
import com.gustavovilela.proposta_app.mapper.PropostaMapper;
import com.gustavovilela.proposta_app.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    private NotificacaoRabbitService notificacaoRabbitService;

    private String exchange;

    public PropostaService(NotificacaoRabbitService notificacaoRabbitService,
                           PropostaRepository propostaRepository,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.propostaRepository = propostaRepository;
        this.exchange = exchange;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta) {
        try {
        notificacaoRabbitService.notificar(proposta, exchange);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDto> obterProposta() {
        Iterable<Proposta> propostas = propostaRepository.findAll();

        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostas);
    }
}
