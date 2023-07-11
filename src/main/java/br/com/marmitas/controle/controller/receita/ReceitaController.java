package br.com.marmitas.controle.controller.receita;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marmitas.controle.dtos.receita.ReceitaDTO;
import br.com.marmitas.controle.model.receitas.Receita;
import br.com.marmitas.controle.service.receita.ReceitaService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/receita")
public class ReceitaController {
    
    private ReceitaService receitaService;

    //metodo salvar usando classe ReceitaDTO 
    //chamando o metodo converteReceita para pegar a receitaDto e salvar como receita
    //depois buscando a receita e transformando em ReceitaRespostaDTO para mostrar no console
    @PostMapping
    public ResponseEntity<ReceitaDTO> salvar(@RequestBody ReceitaDTO dto){
        Receita receita = receitaService.salvar(dto.converteReceita());
        return new ResponseEntity<>(ReceitaDTO.ReceitaRespostaDTO(receita), HttpStatus.CREATED);
    }

    //metodo para listar busca direto usando a classe receita
    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas(){
        return ResponseEntity.ok().body(receitaService.listar());
    }



}
