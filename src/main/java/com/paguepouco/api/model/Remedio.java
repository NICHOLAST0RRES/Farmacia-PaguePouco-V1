package com.paguepouco.api.model;


import com.paguepouco.api.dtos.CadastroRemedioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nome ;
    private String preco;
    private String descricao ;
    private String validade;
    private String quantidade;


    public Remedio(CadastroRemedioDTO dados)    {

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



    }