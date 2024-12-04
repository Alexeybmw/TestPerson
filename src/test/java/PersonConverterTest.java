import org.example.Person;
import org.example.PersonConverter;
import org.example.PersonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonConverterTest {


    @Test
    void testValidInputWithAge() {
        // Проверяем корректное создание объекта Person из строки, содержащей имя, фамилию и возраст.
        Person person = PersonConverter.convertStringToPerson("Иван Иванов 25");
        assertEquals("Иван", person.name);
        assertEquals("Иванов", person.surname);
        assertEquals(25, person.age);
    }

    @Test
    void testValidInputWithoutAge() {
        // Проверяем, что возраст по умолчанию равен 18, если он не указан в строке.
        Person person = PersonConverter.convertStringToPerson("Марья Петрова");
        assertEquals("Марья", person.name);
        assertEquals("Петрова", person.surname);
        assertEquals(18, person.age);
    }

    @Test
    void testValidInputWithLeadingAndTrailingSpaces() {
        // Проверяем, что функция корректно обрабатывает лишние пробелы в начале и в конце строки.
        Person person = PersonConverter.convertStringToPerson("    Алексей       Смирнов       ");
        assertEquals("Алексей", person.name);
        assertEquals("Смирнов", person.surname);
        assertEquals(18, person.age);
    }

    @Test
    void testValidInputWithMultipleSpacesBetweenWords() {
        // Проверяем, что функция корректно обрабатывает множественные пробелы между именем, фамилией и возрастом.
        Person person = PersonConverter.convertStringToPerson("Оля      Волкова     30");
        assertEquals("Оля", person.name);
        assertEquals("Волкова", person.surname);
        assertEquals(30, person.age);
    }

    @Test
    void testInvalidInputTooFewElements() {
        // Проверяем, что выбрасывается исключение, если строка содержит недостаточно элементов (только имя).
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Аня");
        });
        assertEquals("Invalid input: must contain at least a name and a surname.", exception.getMessage());
    }

    @Test
    void testInvalidInputNonNumericAge() {
        // Проверяем, что выбрасывается исключение, если указание возраста не является числом.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Иван Иванов тридцать");
        });
        assertEquals("Invalid input format: 'Иван Иванов тридцать'", exception.getMessage());
    }

    @Test
    void testInvalidInputWithExtraWords() {
        // Проверяем, что выбрасывается исключение, если строка содержит больше элементов, чем допустимо (например, лишние слова).
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Иван Иванов 25 лет");
        });
        assertEquals("Invalid input format: 'Иван Иванов 25 лет'", exception.getMessage());
    }

    @Test
    void testInvalidInputWithSpecialCharacters() {
        // Проверяем, что выбрасывается исключение, если в имени или фамилии есть специальные символы, такие как '@'.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Иван@ Иванов");
        });
        assertEquals("Invalid input: must contain only letters in name and surname.", exception.getMessage());
    }

    @Test
    void testValidInputWithAgeAsZero() {
        // Проверяем, что программа корректно обрабатывает возраст, равный 0.
        Person person = PersonConverter.convertStringToPerson("Иван Иванов 0");
        assertEquals("Иван", person.name);
        assertEquals("Иванов", person.surname);
        assertEquals(0, person.age);
    }

    @Test
    void testValidInputWithMaxAge() {
        // Проверяем, что программа корректно обрабатывает максимальное целое значение возраста (например, 150).
        Person person = PersonConverter.convertStringToPerson("Марья Петрова 150");
        assertEquals("Марья", person.name);
        assertEquals("Петрова", person.surname);
        assertEquals(150, person.age);
    }

    @Test
    void testStringWithOnlyWhitespace() {
        // Проверяем, что программа выбрасывает исключение, если строка состоит только из пробелов.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("        ");
        });
        assertEquals("Invalid input: must contain at least a name and a surname.", exception.getMessage());
    }

    @Test
    void testInputWithSpecialCharactersInSurname() {
        // Проверяем, что программа корректно обрабатывает специальные символы в фамилии (например, 'Smith-Jones').
        Person person = PersonConverter.convertStringToPerson("Иван Смирнов-Смит 28");
        assertEquals("Иван", person.name);
        assertEquals("Смирнов-Смит", person.surname);
        assertEquals(28, person.age);
    }

    @Test
    void testInputWithNegativeAge() {
        // Проверяем, что программа бросает исключение, если возраст задан как отрицательное значение.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Даша Бурова -5");
        });
        assertEquals("Invalid input format: 'Даша Бурова -5'", exception.getMessage());
    }

    @Test
    void testInputWithAgeAsDecimal() {
        // Проверяем, что программа бросает исключение, если возраст задан с плавающей точкой (дробное число).
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Иван Иванов 25.5");
        });
        assertEquals("Invalid input format: 'Иван Иванов 25.5'", exception.getMessage());
    }
ПШЕ

    @Test
    void testInputWithExtraWhitespace() {
        // Проверяем, что функция корректно удаляет лишние пробелы между словами.
        Person person = PersonConverter.convertStringToPerson(" Лена     Сидорова     ");
        assertEquals("Лена", person.name);
        assertEquals("Сидорова", person.surname);
        assertEquals(18, person.age);
    }

    @Test
    void testInvalidInputSpecialCharactersInSurname() {
        // Проверяем, что функция выбрасывает исключение при наличии специальных символов в фамилии.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Иван Иван@ов");
        });
        assertEquals("Invalid input: must contain only letters in name and surname.", exception.getMessage());
    }

    @Test
    void testInvalidInputWithNegativeAge() {
        // Проверяем, что выбрасывается исключение при указании отрицательного возраста.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Даша Бурова -5");
        });
        assertEquals("Invalid input format: 'Даша Бурова -5'", exception.getMessage());
    }

    @Test
    void testInputWithDecimalAge() {
        // Проверяем, что выбрасывается исключение при указании возраста с плавающей точкой (дробное число).
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PersonConverter.convertStringToPerson("Иван Иванов 25.5");
        });
        assertEquals("Invalid input format: 'Иван Иванов 25.5'", exception.getMessage());
    }
}

