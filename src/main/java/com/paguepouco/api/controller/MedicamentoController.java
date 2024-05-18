package com.paguepouco.api.controller;



import com.paguepouco.api.dtos.Medicamento.AtualizarMedicamento;
import com.paguepouco.api.dtos.Medicamento.CadastroMedicamento;
import com.paguepouco.api.dtos.Medicamento.DetalhamentoMedicamento;
import com.paguepouco.api.model.Medicamento;
import com.paguepouco.api.repositories.MedicamentoRepository;
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
@RequestMapping("/medicamento")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroMedicamento dados, UriComponentsBuilder capturarURI) {
        var medicamento = new Medicamento(dados);
        repository.save(medicamento);
        var uri = capturarURI.path("/medicamento/{id}").buildAndExpand(medicamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoMedicamento(medicamento));
    }


    @GetMapping
    public ResponseEntity<Page<DetalhamentoMedicamento>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DetalhamentoMedicamento::new);
        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid AtualizarMedicamento dados){
        var medicamento = repository.getReferenceById(dados.id());
        medicamento.atualizarMedicamento(dados);
        return ResponseEntity.ok(new DetalhamentoMedicamento(medicamento));
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medicamento = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoMedicamento(medicamento));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id)    {
        var medicamento = repository.getReferenceById(id);
        medicamento.desativar();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity reativar(@PathVariable Long id) {

        var medicamento = repository.getReferenceById(id);
        medicamento.reativar();

        return ResponseEntity.ok(new DetalhamentoMedicamento(medicamento));
    }





}
