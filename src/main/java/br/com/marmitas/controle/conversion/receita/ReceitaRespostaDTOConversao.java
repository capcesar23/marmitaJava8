package br.com.marmitas.controle.conversion.receita;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.marmitas.controle.dtos.receita.ReceitaRespostaDTO;
import br.com.marmitas.controle.model.receitas.Receita;

public class ReceitaRespostaDTOConversao {
    // converter em ReceitaRespostaDTO
    public static ReceitaRespostaDTO converter(Receita receita) {
        return new ReceitaRespostaDTO(
                receita.getId(),
                receita.getNomeReceita());
    }

    // converter em lista de ReceitaREspostaDTO
    public static List<ReceitaRespostaDTO> converterLista(List<Receita> receitas) {
        List<ReceitaRespostaDTO> listaReceitaDTO = new ArrayList<>();
        for (Receita receita : receitas) {
            listaReceitaDTO.add(converter(receita));
        }
        return listaReceitaDTO;
    }

    // converter em Optional de ReceitaRespostaDTO
    public static Optional<ReceitaRespostaDTO> converterOptional(Optional<Receita> buscaReceita) {
        return buscaReceita.map(ReceitaRespostaDTOConversao::converter);
    }

}

