package br.com.marmitas.controle.controller.receita;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.marmitas.controle.conversion.receita.ReceitaRespostaDTOConversao;
import br.com.marmitas.controle.dtos.receita.ReceitaDTO;
import br.com.marmitas.controle.dtos.receita.ReceitaRespostaDTO;
import br.com.marmitas.controle.exceptions.exception.EntidadeJaExisteException;
import br.com.marmitas.controle.exceptions.exception.RecursoNaoEncontradoException;
import br.com.marmitas.controle.model.receitas.Receita;
import br.com.marmitas.controle.service.receita.ReceitaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final ReceitaService receitaService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReceitaRespostaDTO> salvar(@RequestBody @Validated ReceitaDTO novaReceita,
            UriComponentsBuilder uriBuilder)
            throws EntidadeJaExisteException {
        Receita receitaSalva = receitaService.salvar(new Receita(
                novaReceita.getNomeReceita()));
        ReceitaRespostaDTO receitaDTO = ReceitaRespostaDTOConversao.converter(receitaSalva);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(receitaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(receitaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReceitaRespostaDTO>> listarReceitas() {
        List<ReceitaRespostaDTO> listaReceitaDTO = receitaService.listar()
                .stream()
                .map(ReceitaRespostaDTOConversao::converter)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listaReceitaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaRespostaDTO> buscarReceita(@PathVariable Long id) {
        Optional<Receita> buscaReceita = receitaService.buscarId(id);
        Optional<ReceitaRespostaDTO> buscaReceitaDTO = ReceitaRespostaDTOConversao.converterOptional(buscaReceita);
        return buscaReceitaDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Optional<ReceitaRespostaDTO>> update(@PathVariable Long id,
            @RequestBody @Validated Receita novaReceita) throws RecursoNaoEncontradoException {
        Optional<Receita> optionalReceitaAtualizada = receitaService.atualizar(novaReceita, id);
        Optional<ReceitaRespostaDTO> receitaDTOAtualizada = ReceitaRespostaDTOConversao
                .converterOptional(optionalReceitaAtualizada);
        return ResponseEntity.ok(receitaDTOAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirReceita(@PathVariable Long id) throws RecursoNaoEncontradoException {
        receitaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
