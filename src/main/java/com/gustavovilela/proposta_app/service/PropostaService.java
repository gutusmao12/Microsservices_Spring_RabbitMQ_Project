package com.gustavovilela.proposta_app.service;

import com.gustavovilela.proposta_app.dto.PropostaRequestDto;
import com.gustavovilela.proposta_app.dto.PropostaResponseDto;
import com.gustavovilela.proposta_app.entity.Proposta;
import com.gustavovilela.proposta_app.mapper.PropostaMapper;
import com.gustavovilela.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    private NotificacaoService notificacaoService;

    private String exchange;

    public PropostaService(NotificacaoService notificacaoService,
                           PropostaRepository propostaRepository,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.notificacaoService = notificacaoService;
        this.propostaRepository = propostaRepository;
        this.exchange = exchange;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        PropostaResponseDto response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        notificacaoService.notificar(response, exchange);

        return response;
    }

    public List<PropostaResponseDto> obterProposta() {
        Iterable<Proposta> propostas = propostaRepository.findAll();

        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostas);
    }
}
