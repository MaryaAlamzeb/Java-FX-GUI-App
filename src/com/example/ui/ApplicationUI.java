package com.example.ui;

import com.example.database.DatabaseManager;
import com.example.validation.Validator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ApplicationUI extends GridPane {

	private DatabaseManager dbManager = DatabaseManager.getInstance();

	private Label errorLabel;

	public ApplicationUI() {
		initComponents();
	}

	public void initComponents() {
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10));

		Label nameLabel = new Label("Name:");
		TextField nameField = new TextField();

		Label ageLabel = new Label("Age:");
		TextField ageField = new TextField();

		errorLabel = new Label();

		Button saveButton = new Button("Save");
		saveButton.setOnAction(event -> {
			String name = nameField.getText();
			String ageText = ageField.getText();
			if (Validator.validateData(name, ageText, errorLabel)) {
				int age = Integer.parseInt(ageText);
				dbManager.saveData(name, age);
				showSuccessDialog();
				clearFields();
			}
		});

		add(nameLabel, 0, 0);
		add(nameField, 1, 0);
		add(ageLabel, 0, 1);
		add(ageField, 1, 1);
		add(errorLabel, 0, 2, 2, 1);
		add(saveButton, 0, 3, 2, 1);
	}

	private void showSuccessDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Success");
		alert.setHeaderText("Data sent successfully");
		alert.setContentText("Do you want to add another data?");

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.setAlwaysOnTop(true);

		Button yesButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.OK);
		yesButton.setText("Yes");

		Button noButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.CANCEL);
		noButton.setText("No");

		alert.showAndWait().ifPresent(response -> {
			if (response == javafx.scene.control.ButtonType.CANCEL) {
				showExitMessage();
			}
		});
	}

	private void showExitMessage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Exit");
		alert.setHeaderText(null);
		alert.setContentText("Thank you for using the application.");
		alert.showAndWait();
		System.exit(0);
	}

	private void clearFields() {
		((TextField) getChildren().get(1)).clear();
		((TextField) getChildren().get(3)).clear();
	}
}
