package com.gustavovilela.proposta_app.controller;

import com.gustavovilela.proposta_app.dto.PropostaRequestDto;
import com.gustavovilela.proposta_app.dto.PropostaResponseDto;
import com.gustavovilela.proposta_app.service.PropostaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto) {
        PropostaResponseDto response =  propostaService.criar(requestDto);
        return ResponseEntity.ok(response);
    }
}
