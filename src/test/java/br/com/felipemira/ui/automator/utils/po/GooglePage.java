package br.com.felipemira.ui.automator.utils.po;

import br.com.felipemira.ui.automator.utils.core.BasePage;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
@Setter
public class GooglePage extends BasePage<GooglePage> {

	@FindBy(xpath = "//input[@type='text']")
	public WebElement campoPesquisa;

	@FindBy(xpath = "(//input[contains(@value,'Pesquisa Google')])[2]")
	public WebElement btnPesquisa;

	@FindBy(id = "resultStats")
	public WebElement resultadoPesquisa;

}
