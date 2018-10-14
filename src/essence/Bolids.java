package essence;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Bolids implements IExportHelperJson, IExportHelperString {

    private String nameBolid;
    private String nameEngine;
    private String nameChassis;
    private Year yearBolid;

    //поля из xml
    final static public List<String> fieldClassNames = Arrays.asList("Name_bolid", "Name_engine", "Name_chassis", "Year_bolid");

    public Bolids(String nameBolid, String nameEngine, String nameChassis, Year yearBolid) {
        this.nameBolid = nameBolid;
        this.nameEngine = nameEngine;
        this.nameChassis = nameChassis;
        this.yearBolid = yearBolid;
    }

    public Bolids(Map<String, String> params) {
        for (String nameField: fieldClassNames) {
            switch (nameField){
                case "Name_bolid":
                    this.nameBolid = params.get(nameField);
                    break;
                case "Name_engine":
                    this.nameEngine = params.get(nameField);
                    break;
                case "Name_chassis":
                    this.nameChassis = params.get(nameField);
                    break;
                case "Year_bolid":
                    this.yearBolid = Year.parse(params.get(nameField), DateTimeFormatter.ofPattern("yyyy"));
                    break;
                default:
                    System.out.println("No name Field in Class Bolids");
            }
        }
    }

    @Override
    public String toString() {
        String resString = "\nНазвание болида: " + this.nameBolid;
        resString += "\nНазвание движка: " + this.nameEngine;
        resString += "\nНазвание шасси: " + this.nameChassis;
        resString += "\nГод болида: " + this.yearBolid.toString() + "\n";

        return resString;
    }

    @Override
    public String toHtmlString() {
        return "<td>"+this.nameBolid+"</td>\n"+
                "<td>"+this.nameEngine+"</td>\n" +
                "<td>"+this.nameChassis+"</td>\n" +
                "<td>"+this.yearBolid.toString()+"</td>\n";
    }

    @Override
    public String toJson() {
        return "{\"Name_bolid\": \""+ this.nameBolid +
                "\",\"Name_engine\": \"" + this.nameEngine +
                "\",\"Name_chassis\": \"" + this.nameChassis +
                "\",\"Year_bolid\": \"" + this.yearBolid.format(DateTimeFormatter.ofPattern("yyyy")) + "\"}";
    }
}
