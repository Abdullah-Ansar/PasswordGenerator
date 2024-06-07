package com.project.passwordgenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.security.SecureRandom;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Password Generator");

        // Create UI elements
        Label lengthLabel = new Label("Password Length:");
        TextField lengthField = new TextField();

        CheckBox upperCaseCheckBox = new CheckBox("Include Uppercase");
        CheckBox lowerCaseCheckBox = new CheckBox("Include Lowercase");
        CheckBox numbersCheckBox = new CheckBox("Include Numbers");
        CheckBox specialCharsCheckBox = new CheckBox("Include Special Characters");

        Button generateButton = new Button("Generate Password");
        TextField passwordField = new TextField();
        passwordField.setEditable(false);

        // Layout
        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid-pane");
        grid.add(lengthLabel, 0, 0);
        grid.add(lengthField, 1, 0);
        grid.add(upperCaseCheckBox, 0, 1);
        grid.add(lowerCaseCheckBox, 1, 1);
        grid.add(numbersCheckBox, 0, 2);
        grid.add(specialCharsCheckBox, 1, 2);
        grid.add(generateButton, 0, 3);
        grid.add(passwordField, 1, 3);

        // Set generate button action
        generateButton.setOnAction(e -> {
            int length = Integer.parseInt(lengthField.getText());
            boolean includeUpperCase = upperCaseCheckBox.isSelected();
            boolean includeLowerCase = lowerCaseCheckBox.isSelected();
            boolean includeNumbers = numbersCheckBox.isSelected();
            boolean includeSpecialChars = specialCharsCheckBox.isSelected();

            String password = generatePassword(length, includeUpperCase, includeLowerCase, includeNumbers, includeSpecialChars);
            passwordField.setText(password);
        });

        Scene scene = new Scene(grid, 400, 200);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String generatePassword(int length, boolean includeUpperCase, boolean includeLowerCase, boolean includeNumbers, boolean includeSpecialChars) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;':,.<>?";

        StringBuilder passwordCharacters = new StringBuilder();
        SecureRandom random = new SecureRandom();

        if (includeUpperCase) passwordCharacters.append(upperCaseLetters);
        if (includeLowerCase) passwordCharacters.append(lowerCaseLetters);
        if (includeNumbers) passwordCharacters.append(numbers);
        if (includeSpecialChars) passwordCharacters.append(specialChars);

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(passwordCharacters.length());
            password.append(passwordCharacters.charAt(index));
        }
        return password.toString();
    }
}
