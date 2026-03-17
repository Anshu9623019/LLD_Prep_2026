package org.example.OppsByKunalKushwaha.SingletonClass;

public class DatabaseConnection {

    private DatabaseConnection(){

    }

    private static class DBConnectionHelper{
        private static final DatabaseConnection INSTANCE_OBJECT = new DatabaseConnection();
    }

    public static DatabaseConnection getInstance(){
        return DBConnectionHelper.INSTANCE_OBJECT;
    }
}
