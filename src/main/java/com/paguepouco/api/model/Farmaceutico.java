package com.paguepouco.api.model;


import com.paguepouco.api.dtos.CadastroFarmaceuticoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Farmaceutico(CadastroFarmaceuticoDTO dados) {
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
        if (this.especialidade != null){
            this.especialidade = dados.especialidade();
        }
    }
}
