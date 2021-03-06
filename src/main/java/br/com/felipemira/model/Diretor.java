package br.com.felipemira.model;

import static util.PreCondition.isTrue;
import static util.PreCondition.notEmpty;
import static util.PreCondition.notNull;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/***
 * @author felipe.mira.ext 01/04/2019
 */
public final class Diretor {

    public static final int MAX_LENGTH_NOME = 100;
    
	@Id
    private String id;
	private String nome;
	private LocalDate nascimento;
	
	public Diretor() {}
	
	public Diretor(Builder builder) {
		super();
		this.nome = builder.nome;
		this.nascimento = builder.nascimento;
	}
	
	public static Builder getBuilder() {
        return new Builder();
    }

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	
   public void update(String nome, LocalDate nascimento) {
	   checkObject(nome, nascimento);

        this.nome = nome;
        this.nascimento = nascimento;
    }


    public static class Builder {

        private String nome;
        private LocalDate nascimento;

        private Builder() {}

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder nascimento(LocalDate nascimento) {
            this.nascimento = nascimento;
            return this;
        }

        public Diretor build() {
            Diretor build = new Diretor(this);

            build.checkObject(build.getNome(), build.getNascimento());

            return build;
        }
    }

    private void checkObject(String nome, LocalDate nascimento) {
        notNull(nome, "Nome não pode ser nulo");
        notEmpty(nome, "Nome não pode estar vazio");
        isTrue(nome.length() <= MAX_LENGTH_NOME,
                "Nome cannot be longer than %d characters",
                MAX_LENGTH_NOME
        );

        notNull(nascimento, "Nascimento não pode ser nulo");
    }
}
