package br.com.marmitas.controle.dtos.receita;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaDTO {

    private Long id;

    private String nomeReceita;

    private LocalDate dataInclusao;

    private String horaInclusao ;

}
