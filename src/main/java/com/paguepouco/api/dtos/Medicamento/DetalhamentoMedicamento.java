package com.paguepouco.api.dtos.Medicamento;

import com.paguepouco.api.model.Medicamento;

public record DetalhamentoMedicamento(Long id, String nome, String preco, String descricao, String validade, String quantidade) {

    public DetalhamentoMedicamento(Medicamento medicamento) {

        this(medicamento.getId(),medicamento.getNome(),medicamento.getPreco(),medicamento.getDescricao(),medicamento.getValidade(),medicamento.getQuantidade());

    }

}
