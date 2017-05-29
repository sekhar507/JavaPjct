package hello;

import java.util.*;

public class SimpleJDBC {
	public static void main(String[] args) {
		Locale locale = Locale.getDefault();

		String ch = locale.getDisplayCountry();
		System.out.println(locale.getDisplayCountry());
		if (ch == "United States") {
			System.out.println("Print error message in English");
		} else {
			System.out.println("print error message in chinees");
		}

	}
}
