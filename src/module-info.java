module com.example.javafx {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	
	opens com.example.application to javafx.graphics, javafx.fxml;
}
