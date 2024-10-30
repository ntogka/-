// Beneficiary.java
public class Beneficiary {
    private int beneficiaryId;
    private String firstName;
    private String lastName;

    public Beneficiary(int beneficiaryId, String firstName, String lastName) {
        this.beneficiaryId = beneficiaryId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "beneficiaryId=" + beneficiaryId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
