package dataBase.Queries;

import dataBase.DbConnection;

import java.sql.SQLException;

public class CommonTable
{
    public DbConnection dbConnection;

    public CommonTable() throws SQLException {
        dbConnection = DbConnection.getInstance();
    }
}
