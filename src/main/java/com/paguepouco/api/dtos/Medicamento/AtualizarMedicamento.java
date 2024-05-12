package com.paguepouco.api.dtos.Medicamento;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedicamento(@NotNull Long id , String nome , String preco, String descricao, String validade, String quantidade) {
}
