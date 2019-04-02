package br.com.felipemira.error;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {
        super(String.format("Nenhum registro encontrado pelo id: <%s>", id));
    }
}
