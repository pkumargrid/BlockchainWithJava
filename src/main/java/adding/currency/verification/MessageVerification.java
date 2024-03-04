package adding.currency.verification;


import adding.currency.transaction.Transaction;

public class MessageVerification {

    public static boolean isValid(Transaction transaction) {
        if (transaction.getVc() > transaction.getSender().getVC()
        || transaction.getVc() == 0) {
            return false;
        }
        transaction.getSender().setVC(transaction.getSender().getVC() - transaction.getVc());
        transaction.getReceiver().setVC(transaction.getReceiver().getVC() + transaction.getVc());
        return true;
    }

}
