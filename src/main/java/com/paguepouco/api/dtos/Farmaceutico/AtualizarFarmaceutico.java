package com.paguepouco.api.dtos.Farmaceutico;

import jakarta.validation.constraints.NotNull;

public record AtualizarFarmaceutico(

        @NotNull(message = "ERRO, ID DO FARMACEUTICO NÃO INFORMADO ")  Long id ,
        String nome ,
        String tipo,
        String crf,
        String turno
) {
}
