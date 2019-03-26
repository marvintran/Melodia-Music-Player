package comp3350.melodia.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.melodia.objects.Account;
import comp3350.melodia.persistence.AccountPersistence;

public class AccountPersistenceHSQLDB implements AccountPersistence {

    private final String dbPath;

    public AccountPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Account fromResultSet(final ResultSet rs) throws SQLException {

        final String fullName = rs.getString("fullName");
        final String userName = rs.getString("userName");
        final String email = rs.getString("email");
        final String profile = rs.getString("profile");

        return new Account(fullName, userName, email, profile, null);
    }

    @Override
    public List<Account> getAllAccounts() {
        final List<Account> accounts = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM ACCOUNT");
            while (rs.next())
            {
                final Account account = fromResultSet(rs);
                accounts.add(account);
            }
            rs.close();
            st.close();

            return accounts;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Account insertAccount(Account currentAccount) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO ACCOUNT VALUES(?, ?, ?, ?)");
            st.setString(1, currentAccount.getFullName());
            st.setString(2, currentAccount.getUserName());
            st.setString(3, currentAccount.getEmail());
            st.setString(4, currentAccount.getProfile());

            st.executeUpdate();

            return currentAccount;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Account updateAccount(Account currentAccount) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE ACCOUNT SET name = ? WHERE fullName = ? WHERE email = ? WHERE profile = ? ");
            st.setString(1, currentAccount.getUserName());
            st.setString(2, currentAccount.getFullName());
            st.setString(3, currentAccount.getEmail());
            st.setString(4, currentAccount.getProfile());

            st.executeUpdate();

            return currentAccount;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteAccount(Account currentAccount) {

        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM accounts WHERE fullName = ?");
            sc.setString(1, currentAccount.getFullName());
            sc.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
