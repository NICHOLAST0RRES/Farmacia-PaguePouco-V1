package com.paguepouco.api.dtos.Medicamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarMedicamento(@NotNull(message = "ERRO, ID DO MEDICAMENTO NÃO INFORMADO")  Long id ,
                                    String nome ,
                                    String preco,
                                    String descricao,
                                    String validade,
                                    String quantidade) {
}
