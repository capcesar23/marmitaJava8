package br.com.marmitas.controle.dtos.receita;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaRespostaDTO {

    private Long id;

    private String nomeReceita;
}
