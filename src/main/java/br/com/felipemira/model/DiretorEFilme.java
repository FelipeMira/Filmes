package br.com.felipemira.model;

public class DiretorEFilme {

	private Diretor diretor;

	private Filme filme;

	public DiretorEFilme(Filme filme, Diretor diretor) {
		this.filme = filme;
		this.diretor = diretor;
	}

	public DiretorEFilme() {
	}

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
}
