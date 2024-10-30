// AccountManager.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<Beneficiary> beneficiaries = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public AccountManager() {
        loadBeneficiaries();
        loadAccounts();
        loadTransactions();
    }

    private void loadBeneficiaries() {
        try (BufferedReader br = new BufferedReader(new FileReader("java project\\data\\beneficiaries.csv"))) {
            String line;
            br.readLine(); // Ignore header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Beneficiary beneficiary = new Beneficiary(Integer.parseInt(values[0]), values[1], values[2]);
                beneficiaries.add(beneficiary);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader("java project\\data\\accounts.csv"))) {
            String line;
            br.readLine(); // Ignore header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Account account = new Account(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader("java project\\data\\transactions.csv"))) {
            String line;
            br.readLine(); // Ignore header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Transaction transaction = new Transaction(
                        Integer.parseInt(values[0]),
                        Integer.parseInt(values[1]),
                        Double.parseDouble(values[2]),
                        values[3],
                        values[4]
                );
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Beneficiary getBeneficiary(int beneficiaryId) {
        return beneficiaries.stream()
                .filter(b -> b.getBeneficiaryId() == beneficiaryId)
                .findFirst()
                .orElse(null);
    }

    public List<Account> getAccountsForBeneficiary(int beneficiaryId) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getBeneficiaryId() == beneficiaryId) {
                result.add(account);
            }
        }
        return result;
    }

    public List<Transaction> getTransactionsForBeneficiary(int beneficiaryId) {
        List<Account> beneficiaryAccounts = getAccountsForBeneficiary(beneficiaryId);
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            for (Account account : beneficiaryAccounts) {
                if (transaction.getAccountId() == account.getAccountId()) {
                    result.add(transaction);
                }
            }
        }
        return result;
    }

    public double getBalanceForBeneficiary(int beneficiaryId) {
        double balance = 0;
        List<Transaction> beneficiaryTransactions = getTransactionsForBeneficiary(beneficiaryId);
        for (Transaction transaction : beneficiaryTransactions) {
            if (transaction.getType().equals("deposit")) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }

    public double getLargestWithdrawalLastMonth(int beneficiaryId) {
        double largestWithdrawal = 0;
        List<Transaction> beneficiaryTransactions = getTransactionsForBeneficiary(beneficiaryId);
        for (Transaction transaction : beneficiaryTransactions) {
            if (transaction.getType().equals("withdrawal")) {
                // Simple check for the last month (in a real app, use a date library)
                if (transaction.getDate().startsWith("09/")) { // Assuming September for simplicity
                    if (transaction.getAmount() > largestWithdrawal) {
                        largestWithdrawal = transaction.getAmount();
                    }
                }
            }
        }
        return largestWithdrawal;
    }
}
