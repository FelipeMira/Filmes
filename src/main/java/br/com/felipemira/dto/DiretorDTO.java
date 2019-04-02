package br.com.felipemira.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.felipemira.model.Diretor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DiretorDTO {

	private String id;

    @NotNull
    @Size(max = Diretor.MAX_LENGTH_NOME)
    private String nome;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@ApiModelProperty(value="Data de nascimento, Ex.:2018-09-05.", position=2)
	@NotNull
	private LocalDate nascimento;

	public String getId() {
		return id;
	}

	@ApiModelProperty(hidden = true)
	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "DiretorDTO{" +
				"id='" + id + '\'' +
				", nascimento=" + nascimento +
				", nome='" + nome + '\'' +
				'}';
	}
}
