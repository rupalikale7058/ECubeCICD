package moduls;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Member_General_Tab {
    private Faker faker;
    private SimpleDateFormat dateFormat;

    public Member_General_Tab() {
        this.faker = new Faker();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }
    public String getTitle() {
        return faker.options().option("Mr.", "Mrs.", "Dr.");
    }
    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getMiddleName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getAddress() {
        return faker.address().streetAddress();
    }

    public String getZipCode() {
        return faker.address().zipCode();
    }

    public String getArea() {
        return faker.address().cityName();
    }

    public String getCity() {
        return faker.address().city();
    }

    public String getState() {
        return faker.address().state();
    }

    public String getCountry() {
        return faker.options().option("INDIA","CHINA");
    }
    public String getMaritalStatus() {
        return faker.options().option("Single", "Married", "Divorced", "Widowed");
    }

    public String getGender() {
        return faker.options().option("Male", "Female");
    }

    public String getDateOfBirth() {
        Date birthDate = faker.date().birthday(18, 65);
        return dateFormat.format(birthDate);
    }

    public String getCategory() {
        return faker.options().option("Adult", "Child");
    }

    public String getNationality() {
        return faker.country().name();
    }
}
