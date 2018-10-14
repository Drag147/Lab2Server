package dbconnector;

import formula1.Formula1;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Connector {

    private String DB_URL = "jdbc:postgresql://127.0.0.1:5432/";
    private final String USER; //= "postgres";
    private final String PASS; //= "postgres";

    private Connection connection;

    public Connector (String nameDd, String user, String password){
        this.DB_URL = this.DB_URL.concat(nameDd);
        this.USER = user;
        this.PASS = password;
    }

    public void connect () throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);

    }

    List<HashMap<String, String>> querySelect(String queryString, List<String> nameFields) throws SQLException {

        List<HashMap<String, String>> resultQuery = new LinkedList<>();
        HashMap<String, String> tmpMap = new HashMap<>();
        try{
            Statement statement =  this.connection.createStatement();

            ResultSet result1 = statement.executeQuery(queryString);

            while (result1.next()) {
                //System.out.println("Номер строки #" + result1.getRow());
                for (String nameField:nameFields) {
                    //System.out.println("\t" + nameField + " : " + result1.getString(nameField));
                    tmpMap.put(nameField, result1.getString(nameField));
                }
                resultQuery.add(tmpMap);
                tmpMap = new HashMap<>();
            }

            statement.close();

            return resultQuery;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void querySelectAndInsert(String queryString, List<String> nameFields, Formula1 formula1, String nameClass) throws SQLException {

        try{
            switch (nameClass) {
                case "Pilots":
                    for (HashMap<String, String> newObj:querySelect(queryString, nameFields)) {
                        formula1.addNewPilot(newObj);
                    }
                    break;
                case "Bolids":
                    for (HashMap<String, String> newObj:querySelect(queryString, nameFields)) {
                        formula1.addNewBolid(newObj);
                    }
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
