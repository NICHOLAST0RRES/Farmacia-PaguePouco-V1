package com.paguepouco.api.model;


import com.paguepouco.api.dtos.Farmaceutico.AtualizarFarmaceutico;
import com.paguepouco.api.dtos.Farmaceutico.CadastroFarmaceutico;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "farmaceuticos")
@Entity(name = "Farmaceutico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Farmaceutico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nome;
    private String tipo ;
    private String crf ;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private String turno;

    private boolean ativo;




    public Farmaceutico (CadastroFarmaceutico dados){
        this.ativo = true ;
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.crf = dados.crf();
        this.especialidade = dados.especialidade();
        this.turno = dados.turno();

    }


    public void atualizarFarmaceutico(AtualizarFarmaceutico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
        if (dados.crf() != null) {
            this.crf = dados.crf();
        }

        if(dados.turno() != null) {
            this.turno = dados.turno();
        }

    }


    // em banco de dados é mais recomendavel mudar o status para inativo doq apagar completamente do BDs
    public void desativar() {
        this.ativo = false ;
    }

    public void reativar(){
        this.ativo = true ;
    }
}

