package org.example;

public class PersonConverter {

    public static Person convertStringToPerson(String input) throws IllegalArgumentException {
        String[] parts = input.trim().split("\\s+");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid input: must contain at least a name and a surname.");
        }
        if (parts.length > 3) {
            throw new IllegalArgumentException("Invalid input format: '" + input + "'");
        }

        String name = parts[0];
        String surname = parts[1];
        int age = 18; // Default age

        if (!name.matches("[а-яА-ЯёЁa-zA-Z]+") || !surname.matches("[а-яА-ЯёЁa-zA-Z-]+")) {
            throw new IllegalArgumentException("Invalid input: must contain only letters in name and surname.");
        }

        if (parts.length == 3) {
            try {
                age = Integer.parseInt(parts[2]);
                if (age < 0) {
                    throw new IllegalArgumentException("Invalid input format: '" + input + "'");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format: '" + input + "'");
            }
        }

        return new Person(name, surname, age);
    }
}
