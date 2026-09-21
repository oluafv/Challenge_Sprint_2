package br.com.fiap.Challenge_Sprint_2.DTOs;

import java.util.List;

public record AnaliseAiResponse(
        int pontuacaoViabilidade,
        String classificacao,
        String feedbackCritico,
        List<String> sugestoesMelhoria
) {}