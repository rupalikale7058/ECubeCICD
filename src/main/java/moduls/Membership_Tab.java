package moduls;

import com.github.javafaker.Faker;

public class Membership_Tab {
    private Faker faker;

    public Membership_Tab() {
        this.faker = new Faker();
    }

    public String getMembershipCategory() {
        return faker.options().option("Premium");
    }

    public String getMembershipType() {
        return faker.options().option("Premium Membership");
    }

    public String getMemberCode() {
        // Generate a 4-digit unique member code
        String generatedCode = String.valueOf(faker.number().numberBetween(1000, 9999));
        System.out.println(generatedCode);
        return generatedCode;
        //return faker.options().option("8866");
    }

    public String getRegistrationDate() {
        return faker.date().birthday().toInstant().toString(); // Example date, adjust format as needed
    }

    public String getFees() {
        return String.valueOf(faker.number().randomDouble(2, 100, 1000));
    }

    public String getCreditLimit() {
        return String.valueOf(faker.number().numberBetween(1000, 50000));
    }

    public String getAvailableCredit() {
        return String.valueOf(faker.number().numberBetween(100, 40000));
    }

    public String getNetAmount() {
        return String.valueOf(faker.number().randomDouble(2, 100, 1000)); // Example net amount
    }

    public String getNetTransferFee() {
        return String.valueOf(faker.number().randomDouble(2, 10, 100)); // Example net transfer fee
    }

    public String getRemarks() {
        return faker.lorem().sentence(); // Example remarks
    }

    public String getRCIMemberCode() {
        return faker.idNumber().valid(); // Example RCI member code
    }

    public boolean isGst1Checked() {
        return faker.bool().bool(); // Example checkbox value
    }
}
