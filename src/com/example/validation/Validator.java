package com.example.validation;

import javafx.scene.control.Label;

public class Validator {

	public static boolean validateData(String name, String ageText, Label errorLabel) {
		try {
			int age = Integer.parseInt(ageText);
			if (age < 10 || age > 90) {
				errorLabel.setText("Age should be between 10 and 90");
				return false;
			}
		} catch (NumberFormatException e) {
			errorLabel.setText("Invalid age");
			return false;
		}

		if (name.isEmpty() || ageText.isEmpty()) {
			errorLabel.setText("Field data required");
			return false;
		}

		errorLabel.setText("");
		return !name.isEmpty() && !ageText.isEmpty();
	}
}
