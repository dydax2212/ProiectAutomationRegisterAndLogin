package dataBase.Queries;

import ObjectData.GoogleLoginFormObjectData;

import java.sql.SQLException;
import java.sql.Statement;

public class GmailLoginForm extends CommonTable {
    public GmailLoginForm() throws SQLException {
    }

    public synchronized void insertGoogleObject(GoogleLoginFormObjectData data) throws SQLException{
        Statement stm = dbConnection.getConnection().createStatement();
        String query = "insert into users(gmail, gpassword) " +
                "values ('" + data.getGmail() + "'" + "," +
                "'" + data.getGpassword() + "'" + ");";
        stm.execute(query);
    }

    public synchronized void updateEntryById(GoogleLoginFormObjectData data, Integer idGoogleForm) throws SQLException{
        Statement stm = dbConnection.getConnection().createStatement();
        String query = "update users set gpassword='" + data.getGpassword() +
                "' where id =" + idGoogleForm + ";";
        stm.execute(query);
    }

}
