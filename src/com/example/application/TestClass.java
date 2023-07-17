package com.example.application;

import com.example.ui.ApplicationUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestClass extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Employee Records");

		ApplicationUI appUI = new ApplicationUI();
		appUI.initComponents();

		Scene scene = new Scene(appUI, 200, 150);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}