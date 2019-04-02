package br.com.felipemira.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

import static util.PreCondition.*;

/***
 * @author felipe.mira.ext 01/04/2019
 */
public final class Filme {

    public static final int MAX_LENGTH_NOME = 100;

	@Id
    private String id;
	private String nome;
	private LocalDate lancamento;

	public Filme() {}

	public Filme(Builder builder) {
		super();
		this.nome = builder.nome;
		this.lancamento = builder.lancamento;
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

	public LocalDate getLancamento() {
		return lancamento;
	}

	
   public void update(String nome, LocalDate lancamento) {
	   checkObject(nome, lancamento);

        this.nome = nome;
        this.lancamento = lancamento;
    }


	/**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    public static class Builder {

        private String nome;
        private LocalDate lancamento;

        private Builder() {}

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder lancamento(LocalDate lancamento) {
            this.lancamento = lancamento;
            return this;
        }

        public Filme build() {
            Filme build = new Filme(this);

            build.checkObject(build.getNome(), build.getLancamento());

            return build;
        }
    }

    private void checkObject(String nome, LocalDate lancamento) {
        notNull(nome, "Nome não pode ser nulo");
        notEmpty(nome, "Nome não pode estar vazio");
        isTrue(nome.length() <= MAX_LENGTH_NOME,
                "Nome cannot be longer than %d characters",
                MAX_LENGTH_NOME
        );

        notNull(lancamento, "lancamento não pode ser nulo");
    }
}
