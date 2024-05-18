package com.paguepouco.api.dtos.Medicamento;

import jakarta.validation.constraints.NotBlank;

public record CadastroMedicamento(@NotBlank  String nome,
                                  @NotBlank(message = "ERRO, O NOME DO MEDICAMENTO NÃO PODE SER CADASTRADO EM BRANCO")  String preco,
                                  String descricao,
                                  @NotBlank(message = "ERRO, A VALIDZADE DO MEDICAMENTO NÃO PODE SER CADASTRADA EM BRANCO")  String validade,
                                  @NotBlank(message = "ERRO, A QUANTIDADE DO MEDICAMENTO NÃO PODE SER CADASTRADA EM BRANCO")  String quantidade ) {
}
