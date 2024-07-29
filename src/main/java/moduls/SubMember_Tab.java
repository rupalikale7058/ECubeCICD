package moduls;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SubMember_Tab {
    private Faker faker;

    public SubMember_Tab() {
        this.faker = new Faker();
    }

    public String getTitle() {
        return faker.options().option("Mr.","Mrs.", "Dr.");
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getMiddleName() {
        return faker.name().firstName(); // Assumes the middle name is a first name
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getDateOfBirth() {
        return faker.date().birthday(18, 65).toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public String getCategory() {
        return faker.options().option("Adult", "Child");
    }

    public String getBloodGroup() {
        return faker.options().option("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
    }

    public String getRelation() {
        return faker.options().option("Father", "Mother", "Brother", "Sister", "Spouse");
    }

    public String getMaritalStatus() {
        return faker.options().option("Single", "Married", "Divorced", "Widowed");
    }

    public String getGender() {
        return faker.options().option("Male", "Female");
    }
}
