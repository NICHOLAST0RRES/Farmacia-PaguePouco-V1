package com.paguepouco.api.dtos.Medicamento;

import jakarta.validation.constraints.NotBlank;

public record CadastroMedicamento(@NotBlank String nome, @NotBlank String preco, String descricao, @NotBlank String validade, @NotBlank String quantidade ) {
}
