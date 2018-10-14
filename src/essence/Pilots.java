package essence;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class Pilots implements IExportHelperJson, IExportHelperString {
    private short personalPilotNumber;
    private String name;
    private String surname;
    private LocalDate dateBirthDay;

    //поля из xml
    final static public List<String> fieldClassNames = Arrays.asList("Personal_pilot_number", "Name", "Surname", "Date_of_birth");

    public Pilots(short personalPilotNumber, String name, String surname, LocalDate dateBirthDay)
    {
        this.personalPilotNumber = personalPilotNumber;
        this.name = name;
        this.surname = surname;
        this.dateBirthDay = dateBirthDay;
    }

    public Pilots(Map<String, String> params)
    {
        for (String nameField : fieldClassNames) {
            switch (nameField){
                case "Personal_pilot_number":
                    this.personalPilotNumber = Short.parseShort(params.get(nameField));
                    break;
                case "Name":
                    this.name = params.get(nameField);
                    break;
                case "Surname":
                    this.surname = params.get(nameField);
                    break;
                case "Date_of_birth":
                    this.dateBirthDay = LocalDate.parse(params.get(nameField));
                    break;
                default:
                    System.out.println("No NameField in Pilots");
            }

        }
    }

    public String toString(String formatDateOutput)
    {
        String resString = "\nЛичный номер пилота: " + this.personalPilotNumber;
        resString += "\nИмя пилота: " + this.name;
        resString += "\nФамилия пилота: " + this.surname;
        resString += "\nДата рождения: " + this.dateBirthDay.format(DateTimeFormatter.ofPattern(formatDateOutput)) + "\n";

        return resString;
    }

    @Override
    public String toString()
    {
        return toString("yyyy-MM-dd");
    }

    @Override
    public String toHtmlString() {
        return "<td>"+this.personalPilotNumber+"</td>\n"+
                "<td>"+this.name+"</td>\n" +
                "<td>"+this.surname+"</td>\n" +
                "<td>"+this.dateBirthDay.format(DateTimeFormatter.ofPattern("dd MMM, yyyy г."))+"</td>\n";
    }

    @Override
    public String toJson() {
        return "{\"Personal_pilot_number\": \""+ this.personalPilotNumber +
                "\",\"Name\": \"" + this.name +
                "\",\"surname\": \"" + this.surname +
                "\",\"Date_of_birth\": \"" + this.dateBirthDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                "\"}";
    }
}
