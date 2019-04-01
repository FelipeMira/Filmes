package br.com.felipemira.model;

import static util.PreCondition.isTrue;
import static util.PreCondition.notEmpty;
import static util.PreCondition.notNull;

import org.springframework.data.annotation.Id;

import java.util.Date;

public final class Diretor {

    public static final int MAX_LENGTH_NOME = 100;
    
	@Id
    private String id;
	private String nome;
	private Date nascimento;
	
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

	public Date getNascimento() {
		return nascimento;
	}

	
   public void update(String nome, Date nascimento) {
	   checkObject(nome, nascimento);

        this.nome = nome;
        this.nascimento = nascimento;
    }


	/**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    public static class Builder {

        private String nome;
        private Date nascimento;

        private Builder() {}

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder nascimento(Date nascimento) {
            this.nascimento = nascimento;
            return this;
        }

        public Diretor build() {
            Diretor build = new Diretor(this);

            build.checkObject(build.getNome(), build.getNascimento());

            return build;
        }
    }

    private void checkObject(String nome, Date nascimento) {
        notNull(nome, "Nome não pode ser nulo");
        notEmpty(nome, "Nome não pode estar vazio");
        isTrue(nome.length() <= MAX_LENGTH_NOME,
                "Nome cannot be longer than %d characters",
                MAX_LENGTH_NOME
        );

        notNull(nascimento, "Nascimento não pode ser nulo");
    }
}
