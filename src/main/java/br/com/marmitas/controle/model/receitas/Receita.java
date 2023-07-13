package br.com.marmitas.controle.model.receitas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receita")
    private Long id;

    @Column(name = "nome_receita")
    private String nomeReceita;

    @JsonFormat(pattern = "dd/MM/yyyy ")
    @Column(name = "data_inclusao", updatable = false)
    private LocalDate dataInclusao = LocalDate.now();

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "hora_inclusao", updatable = false)
     private String horaInclusao = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

    // construtor para receber só nome e data
    public Receita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
        this.dataInclusao = LocalDate.now();
        this.horaInclusao = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        
    }

}
