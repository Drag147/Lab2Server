package dbconnector;

import essence.Bolids;
import essence.Pilots;
import formula1.Formula1;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args){

        String DB_NAME = "F1";
        String USER = "postgres";
        String PASS = "postgres";

        Formula1 formula1 = new Formula1();

        try{

            Connector connector = new Connector(DB_NAME, USER, PASS);

            connector.connect();

            List<HashMap<String, String>> resultQuery = connector.querySelect("select * from \"Pilots\";",
                    Pilots.fieldClassNames);

            for (HashMap<String, String> onePilot: resultQuery) {
                formula1.addNewPilot(onePilot);
            }

            System.out.println(formula1.pilotsToString());

            List<HashMap<String, String>> resultQueryB = connector.querySelect("select * from \"Bolids\";",
                    Bolids.fieldClassNames);

            for (HashMap<String, String> oneBolid: resultQueryB) {
                formula1.addNewBolid(oneBolid);
            }

            System.out.println(formula1.bolidsToString());

            connector.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
