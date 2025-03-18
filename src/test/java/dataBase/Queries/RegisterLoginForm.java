package dataBase.Queries;

import ObjectData.RegisterFormObjectData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterLoginForm extends CommonTable {
    public RegisterLoginForm() throws SQLException {
    }

    public synchronized void updateEntryById(RegisterFormObjectData data, Integer idGoogleForm) throws SQLException{
        Statement stm = dbConnection.getConnection().createStatement();

        String query = "UPDATE users SET password2='" + data.getPassword1() +
                "' WHERE id =" + idGoogleForm + ";";

        stm.execute(query);
    }

    public synchronized boolean enterEmailText(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email='" + email + "';";

        Statement stm = dbConnection.getConnection().createStatement();

        ResultSet resultSet = stm.executeQuery(query);

        return resultSet.next();
    }

    public synchronized RegisterFormObjectData getUserDataById(int userId) throws SQLException {
        String query = "SELECT fullName, phoneNumber, password1, password2 FROM users WHERE id = " + userId + ";";

        Statement stm = dbConnection.getConnection().createStatement();

        ResultSet resultSet = stm.executeQuery(query);

        if (resultSet.next()) {
            RegisterFormObjectData userData = new RegisterFormObjectData();

            userData.setFullName(resultSet.getString("fullName"));
            userData.setPhoneNumber(resultSet.getString("phoneNumber"));
            userData.setPassword1(resultSet.getString("password1"));
            userData.setPassword2(resultSet.getString("password2"));

            return userData;
        }
        return null;
    }

    public synchronized boolean loginWithEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email='" + email + "' AND password1='" + password + "';";

        Statement stm = dbConnection.getConnection().createStatement();

        ResultSet resultSet = stm.executeQuery(query);

        return resultSet.next();
    }
}
