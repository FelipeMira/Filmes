package br.com.felipemira.cucumber;

import org.springframework.test.context.ActiveProfilesResolver;

public class ProfilesResolver implements ActiveProfilesResolver {

	@Override
	public String[] resolve(Class<?> testClass) {

		if (System.getProperties().containsKey("spring.profiles.active")) {

			final String profiles = System.getProperty("spring.profiles.active");
			return profiles.split("\\s*,\\s*");

		} else {
			String[] retorno = {"test"};
			return retorno;
		}
	}
}
