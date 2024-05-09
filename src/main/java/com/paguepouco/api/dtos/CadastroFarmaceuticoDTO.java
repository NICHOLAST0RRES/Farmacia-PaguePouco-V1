package com.paguepouco.api.dtos;

import com.paguepouco.api.model.Especialidade;

public record CadastroFarmaceuticoDTO(String nome, Especialidade especialidade , String tipo, String crf ,String turno) {
}
