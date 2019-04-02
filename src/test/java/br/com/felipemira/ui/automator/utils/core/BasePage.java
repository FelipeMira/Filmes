package br.com.felipemira.ui.automator.utils.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author felipe.mira.ext criado em 01/04/2019.
 * @param <T>
 */
public abstract class BasePage<T> {

	private static JavascriptExecutor js;

	private static WebDriver driver;

	@Autowired
	Selenium selenium;


	private Class<T> type;

	public BasePage() {
		driver = Selenium.driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	public BasePage(Class<T> type) {
		this.type = type;
		driver = Selenium.driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	@SuppressWarnings("unused")
	public void reloadElements(){
		driver = Selenium.driver;
		PageFactory.initElements(driver, type);
		js = (JavascriptExecutor) driver;
	}

	public String getUrl() {
		return "";
	}


}