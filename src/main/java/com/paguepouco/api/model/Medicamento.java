package com.paguepouco.api.model;


import com.paguepouco.api.dtos.Medicamento.AtualizarMedicamento;
import com.paguepouco.api.dtos.Medicamento.CadastroMedicamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nome ;
    private String preco;
    private String descricao ;
    private String validade;
    private String quantidade;
    private boolean ativo ;


    public Medicamento(CadastroMedicamento dados) {

        this.ativo = true;
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.descricao = dados.descricao();
        this.validade = dados.validade();
        this.quantidade = dados.quantidade();

    }

    public void atualizarMedicamento(AtualizarMedicamento dados)    {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.validade() != null) {
            this.validade = dados.validade();
        }
        if (dados.quantidade() != null) {
            this.quantidade = dados.quantidade();
        }
                                        }


    public void desativar(){
        this.ativo = false;
    }

    public void reativar(){
        this.ativo = true;
    }
    }