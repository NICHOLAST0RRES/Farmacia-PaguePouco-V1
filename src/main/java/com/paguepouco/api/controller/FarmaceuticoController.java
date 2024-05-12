package com.paguepouco.api.controller;


import com.paguepouco.api.dtos.Farmaceutico.AtualizarFarmaceutico;
import com.paguepouco.api.dtos.Farmaceutico.CadastroFarmaceutico;
import com.paguepouco.api.dtos.Farmaceutico.DetalhamentoFarmaceutico;
import com.paguepouco.api.model.Farmaceutico;
import com.paguepouco.api.repositories.FarmaceuticoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("farmaceutico")
public class FarmaceuticoController {

    @Autowired
    private FarmaceuticoRepository repository;

    // metodo post que lança 201
    @PostMapping
    @Transactional
                                                                                        // essa classe "UriComponentsBuilder " captura a uri
    public ResponseEntity cadastrar(@Valid @RequestBody CadastroFarmaceutico dados, UriComponentsBuilder capturarURI)    {


        var farmaceutico = new Farmaceutico(dados); // colocando o farmaceutico numa variavel para usar ele dps na uri

        repository.save(farmaceutico); // salvando o farmaceutico no repositorio


        /* essa linha captura a uri e o id do medico que acabou de ser criado, não necessita do endereço fisico, o Spring faz isso automaticamente, tudo isso é representado
         na variavel "capturarURI"*/
        var uri = capturarURI.path("/famaceutico/{id}").buildAndExpand(farmaceutico.getId()).toUri(); // colando o farmaceutico como parametro para retorna o farmaceutico criado

        // retornando o codigo 201 e o farmaceutico recem criado
        return ResponseEntity.created(uri).body(new DetalhamentoFarmaceutico(farmaceutico));

    }
    // metodo get que lista os farmaceuticos ativos
    @GetMapping                                                           // essa anotation fornece alguns recursos de ordenação
    public ResponseEntity<Page<DetalhamentoFarmaceutico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        /* esse metodo "findAllByAtivoTrue" foi criado na classe repository, se faz necessario para retornar apenas os
        registros ativos da tabela farmaseuticos*/
        var page = repository.findAllByAtivoTrue(paginacao).map(DetalhamentoFarmaceutico::new);

        //retornando a variavel "page"
        return ResponseEntity.ok(page);
    }


    // metodo put que retorna o farmaceutico atuzalizado
    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid AtualizarFarmaceutico dados){

        //colocando o medico requisitado em uma variavel
        var farmaceutico = repository.getReferenceById(dados.id());

        //chamando o metodo que atualiza o farmaceutico
        farmaceutico.atualizarFarmaceutico(dados);

        // retornando o farmaceutico atualizado
        return ResponseEntity.ok(new DetalhamentoFarmaceutico(farmaceutico));
    }

    //esse medico imprime um farmaceutico pelo id
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {

        var farmaceutico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoFarmaceutico(farmaceutico));


    }


    // esse metodo desativa o Farmaceutico do BDs
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id)    {

        // colocando o farmaceutico solicitado numa variavel.
        var farmaceutico = repository.getReferenceById(id);

        // chamando o metodo excluir da classe farmaceutico.
        farmaceutico.desativar();

        return ResponseEntity.noContent().build();
    }

    // esse metodo reativa o Farmaceutico do BDs
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity reativar(@PathVariable Long id) {

        var farmaceutico = repository.getReferenceById(id);
        farmaceutico.reativar();

        return ResponseEntity.ok(new DetalhamentoFarmaceutico(farmaceutico));
    }



}
