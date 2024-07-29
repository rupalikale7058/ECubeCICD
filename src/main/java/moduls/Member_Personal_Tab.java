package moduls;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Member_Personal_Tab {
    private Faker faker;
    private SimpleDateFormat dateFormat;

    public Member_Personal_Tab() {
        this.faker = new Faker();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public String getOccupation() {
        return faker.job().title();
    }

    public String getProfessionIndustry() {
        return faker.options().option("ACCOUNTS", "ARCHITECT ", "ARCQUB DESIGN STUDIO");
    }

    public String getProfessionCategory() {
        return faker.options().option("Self Employed", "Employed");
    }

    public String getDesignation() {
        return faker.job().position();
    }

    public String getBloodGroup() {
        return faker.options().option("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
    }

    public String getCompanyName() {
        return faker.company().name();
    }

    public String getYearlyIncome() {
        return String.valueOf(faker.number().numberBetween(30000, 200000));
    }

    public String getFamilyDoctorName() {
        return faker.name().fullName();
    }

    public String getDoctorPhoneNo() {
        return faker.phoneNumber().phoneNumber();
    }

    public String getEmergencyNo() {
        return faker.phoneNumber().cellPhone();
    }

    public String getAllergies() {
        return faker.medical().symptoms();
    }

    public String getEducation() {
        return faker.educator().university();
    }

    public String getAnniversaryDate() {
        Date anniversaryDate = faker.date().past(365 * 30, TimeUnit.DAYS);
        return dateFormat.format(anniversaryDate);
    }

    public String getCaste() {
        return faker.lordOfTheRings().character();  // Placeholder, replace with actual data
    }

}
