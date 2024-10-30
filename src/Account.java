// Account.java
public class Account {
    private int accountId;
    private int beneficiaryId;

    public Account(int accountId, int beneficiaryId) {
        this.accountId = accountId;
        this.beneficiaryId = beneficiaryId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getBeneficiaryId() {
        return beneficiaryId;
    }
}
