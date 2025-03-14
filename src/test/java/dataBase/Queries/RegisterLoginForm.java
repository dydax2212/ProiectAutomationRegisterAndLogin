package dataBase.Queries;

import ObjectData.GoogleLoginFormObjectData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterLoginForm extends CommonTable {
    public RegisterLoginForm() throws SQLException {
    }

    public synchronized void updateEntryById(GoogleLoginFormObjectData data, Integer idGoogleForm) throws SQLException{
        Statement stm = dbConnection.getConnection().createStatement();
        String query = "update users set password='" + data.getGpassword() +
                "' where id =" + idGoogleForm + ";";
        stm.execute(query);
    }

    public synchronized boolean loginWithEmailAndPassword(String email, String password) throws SQLException {
        Statement stm = dbConnection.getConnection().createStatement();

        String query = "select * from users where email='" +
                email + "' and password1='" + password + "';";
        ResultSet resultSet = stm.executeQuery(query);

        return resultSet.next(); // Returnează true dacă există utilizatorul, altfel false
    }
}
