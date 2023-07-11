package br.com.marmitas.controle.model.receitas;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "receita")
@Table(name = "receita")
public class Receita {


    private Long idReceita;
    private String nomeReceita;

}
