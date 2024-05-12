package com.paguepouco.api.dtos.Farmaceutico;

import com.paguepouco.api.model.Especialidade;
import jakarta.validation.constraints.NotBlank;


public record CadastroFarmaceutico(@NotBlank String nome, @NotBlank String tipo, String crf , Especialidade especialidade , String turno) {
}
