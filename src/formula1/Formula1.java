package formula1;

import essence.Bolids;
import essence.Pilots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class Formula1 {

    private List<Pilots> pilotsList = new LinkedList<>();
    private List<Bolids> bolidsList = new LinkedList<>();

    final private List<String> classNames = Arrays.asList("Pilots", "Bolids", "Teams",
            "Grand_prix", "Results_qualification", "Results_race");

    public boolean addNewPilot(HashMap<String, String> params) {
        try {
            pilotsList.add(new Pilots(params));
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return  false;
        }
    }

    public boolean addNewBolid(HashMap<String, String> params) {
        try {
            bolidsList.add(new Bolids(params));
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Error in addNewBolid: " +  e.getMessage());
            return  false;
        }
    }

    public String pilotsToString()
    {
        String resString = "Всего пилотов: " + pilotsList.size()+"\n";
        int i = 1;
        for (Pilots pilot: pilotsList)
        {
            resString = resString.concat("---------------\nПилот №" + i + ":");
            resString = resString.concat(pilot.toString("dd MMMM, yyyy года")+"---------------\n");
            i++;
        }
        return resString;
    }

    public String pilotsToHtmlString()
    {
        String resString = "";
        for (Pilots pilot: pilotsList)
        {
            resString =  resString.concat("<tr>\n");
            resString = resString.concat(pilot.toHtmlString());
            resString = resString.concat("</tr>\n");
        }
        return resString;
    }

    public String bolidsToString()
    {
        String resString = "Всего болидов: " + bolidsList.size()+"\n";
        int i = 1;
        for (Bolids bolid: bolidsList)
        {
            resString = resString.concat("---------------\nБолид №" + i + ":");
            resString = resString.concat(bolid.toString()+"---------------\n");
            i++;
        }
        return resString;
    }

    public String bolidsToHtmlString()
    {
        String resString = "";
        for (Bolids bolid: bolidsList)
        {
            resString =  resString.concat("<tr>\n");
            resString = resString.concat(bolid.toHtmlString());
            resString = resString.concat("</tr>\n");
        }
        return resString;
    }


    public String toJson()
    {
        String json = "{\"Formula_1\": {\"Pilots\": [";

        for(Pilots pilots: pilotsList)
        {
            json = json.concat(pilots.toJson());
            if(pilotsList.get(pilotsList.size()-1)!=pilots)
            {
                json += ",";
            }
        }
        json += "],\"Bolids\": [";
        for(Bolids bolids: bolidsList)
        {
            json = json.concat(bolids.toJson());
            if(bolidsList.get(bolidsList.size()-1)!=bolids)
            {
                json += ",";
            }
        }
        json += "]}}";

        return  json;
    }

    public List<String> getClassNames(){return classNames;}
}
