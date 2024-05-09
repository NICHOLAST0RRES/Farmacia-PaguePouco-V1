package com.paguepouco.api.controller;


import com.paguepouco.api.dtos.CadastroFarmaceuticoDTO;
import com.paguepouco.api.dtos.DetalhamentoFarmaceutico;
import com.paguepouco.api.model.Farmaceutico;
import com.paguepouco.api.repositories.FarmaceuticoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("farmaceutico")
public class FarmaceuticoController {

    @Autowired
    private FarmaceuticoRepository repository;


    @PostMapping
    public ResponseEntity cadastrarFarma(@Valid @RequestBody CadastroFarmaceuticoDTO dados, UriComponentsBuilder uriBuilder)    {
        var farmaceutico = new Farmaceutico(dados);
        repository.save(farmaceutico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(farmaceutico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoFarmaceutico(farmaceutico));

    }


}
