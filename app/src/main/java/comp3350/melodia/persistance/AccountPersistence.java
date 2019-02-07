package comp3350.melodia.persistance;

import java.util.List;
import comp3350.melodia.objects.Account;

public interface AccountPersistence {

    List<Account> getAccountSequential();

    List<Account> getAccountRandom(Account currentAccount);

    Account insertAccount(Account currentAccount);

    Account updateAccount(Account currentAccount);

    void deleteAccount(Account currentAccount);

}
