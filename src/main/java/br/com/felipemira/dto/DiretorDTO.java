package br.com.felipemira.dto;

import javax.validation.constraints.Size;

import br.com.felipemira.model.Diretor;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@SuppressWarnings("deprecation")
public class DiretorDTO {

	private String id;

    @NotEmpty
    @Size(max = Diretor.MAX_LENGTH_NOME)
    private String nome;

	@NotEmpty
	private Date nascimento;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
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
