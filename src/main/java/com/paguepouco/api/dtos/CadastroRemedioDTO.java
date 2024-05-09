package com.paguepouco.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record CadastroRemedioDTO(@NotBlank String nome, @NotBlank String preco, String descricao, @NotBlank String validade, @NotBlank String quantidade ) {
}
