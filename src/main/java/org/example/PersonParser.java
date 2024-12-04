package org.example;



public class PersonParser {

    public static Person parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        String[] parts = input.trim().split("\\s+");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid input format: '" + input + "'");
        }

        String name = parts[0];
        String surname = parts[1];
        int age = 18; // Значение по умолчанию

        if (parts.length == 3) {
            try {
                age = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format: '" + input + "'");
            }
        }

        return new Person(name, surname, age);
    }

}





