package br.com.marmitas.controle.service.receita;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.marmitas.controle.exceptions.exception.EntidadeJaExisteException;
import br.com.marmitas.controle.exceptions.exception.RecursoNaoEncontradoException;
import br.com.marmitas.controle.model.receitas.Receita;
import br.com.marmitas.controle.repository.receita.ReceitaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    // POST salvar (Usando DTO)
    public Receita salvar(Receita receita) throws EntidadeJaExisteException {
        if (receita.getId() != null && receitaRepository.existsById(receita.getId())) {
            throw new EntidadeJaExisteException("A Receita já existe");
        }
        return receitaRepository.save(receita);
    }

    // GET listar receita
    public List<Receita> listar() {
        return new ArrayList<>(receitaRepository.findAll());
    }

    // GET Buscar pelo id da receita
    public Optional<Receita> buscarId(Long id) {
           return receitaRepository.findById(id);
    }

    // PUT atualizar pelo id da receita
    public Optional<Receita> atualizar(Receita novaReceita, Long id) throws RecursoNaoEncontradoException {
        Optional<Receita> optionalReceita = receitaRepository.findById(id);
        
        if (optionalReceita.isPresent()) {
            Receita receita = optionalReceita.get();
            receita.setNomeReceita(novaReceita.getNomeReceita());
            return Optional.of(receita);
        } else {
            throw new RecursoNaoEncontradoException("Receita não encontrada");
        }
    }
    
    // DELETE excluir pelo id da receita
    public void excluir(Long id) throws RecursoNaoEncontradoException {
         Optional<Receita> optionalReceita = receitaRepository.findById(id);
         Receita receita = optionalReceita.orElseThrow(()-> new RecursoNaoEncontradoException(""));
        receitaRepository.delete(receita);

    }
}
