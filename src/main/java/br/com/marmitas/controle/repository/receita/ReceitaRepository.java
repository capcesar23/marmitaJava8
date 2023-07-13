package br.com.marmitas.controle.repository.receita;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marmitas.controle.model.receitas.Receita;

public interface ReceitaRepository  extends JpaRepository<Receita, Long>{
    
}
