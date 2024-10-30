// Main.java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Επιλέξτε μια επιλογή:");
            System.out.println("1. Ανάκτηση στοιχείων δικαιούχου");
            System.out.println("2. Ανάκτηση των λογαριασμών ενός δικαιούχου");
            System.out.println("3. Ανάκτηση των συναλλαγών ενός δικαιούχου");
            System.out.println("4. Ανάκτηση του υπολοίπου των λογαριασμών ενός ανθρώπου");
            System.out.println("5. Ανάκτηση της μεγαλύτερης ανάληψης για έναν δικαιούχο τον τελευταίο μήνα");
            System.out.println("6. Έξοδος");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Εισάγετε beneficiaryId: ");
                    int beneficiaryId = scanner.nextInt();
                    Beneficiary beneficiary = accountManager.getBeneficiary(beneficiaryId);
                    System.out.println(beneficiary != null ? beneficiary : "Δεν βρέθηκε δικαιούχος.");
                    break;

                case 2:
                    System.out.print("Εισάγετε beneficiaryId: ");
                    beneficiaryId = scanner.nextInt();
                    List<Account> accounts = accountManager.getAccountsForBeneficiary(beneficiaryId);
                    System.out.println("Λογαριασμοί: " + accounts);
                    break;

                case 3:
                    System.out.print("Εισάγετε beneficiaryId: ");
                    beneficiaryId = scanner.nextInt();
                    List<Transaction> transactions = accountManager.getTransactionsForBeneficiary(beneficiaryId);
                    System.out.println("Συναλλαγές: " + transactions);
                    break;

                case 4:
                    System.out.print("Εισάγετε beneficiaryId: ");
                    beneficiaryId = scanner.nextInt();
                    double balance = accountManager.getBalanceForBeneficiary(beneficiaryId);
                    System.out.println("Υπόλοιπο: " + balance);
                    break;

                case 5:
                    System.out.print("Εισάγετε beneficiaryId: ");
                    beneficiaryId = scanner.nextInt();
                    double largestWithdrawal = accountManager.getLargestWithdrawalLastMonth(beneficiaryId);
                    System.out.println("Μεγαλύτερη ανάληψη τον τελευταίο μήνα: " + largestWithdrawal);
                    break;

                case 6:
                    System.out.println("Έξοδος...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Μη έγκυρη επιλογή. Παρακαλώ προσπαθήστε ξανά.");
                    break;
            }
        }
    }
}
