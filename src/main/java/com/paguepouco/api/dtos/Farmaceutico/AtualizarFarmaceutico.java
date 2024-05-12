package com.paguepouco.api.dtos.Farmaceutico;

import jakarta.validation.constraints.NotNull;

public record AtualizarFarmaceutico(

        @NotNull Long id ,  String nome , String tipo, String crf, String turno
) {
}
