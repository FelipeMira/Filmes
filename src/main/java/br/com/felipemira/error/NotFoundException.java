package br.com.felipemira.error;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {
        super(String.format("Nenhum registro encontrado pelo id: <%s>", id));
    }
}
