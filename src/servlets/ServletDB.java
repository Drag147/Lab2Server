package servlets;

import dbconnector.Connector;
import essence.Bolids;
import essence.Pilots;
import formula1.Formula1;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ServletDB extends HttpServlet {

    private Formula1 formula1 = new Formula1();

    private final String nameBd = "F1";
    private final String user ="postgres";
    private final String pass ="postgres";

    private Connector connector = new Connector(nameBd, user, pass);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");

        try {
            connectToDb();

            PrintWriter pw = resp.getWriter();
            pw.println("<style>\n" +
                    ".wrapper{\n" +
                    "    display: flex;\n" +
                    "    flex-wrap: wrap;\n" +
                    "    justify-content: space-between;\n" +
                    "}\n" +
                    ".wrapper .elem{\n" +
                    "    display: inline-block;\n" +
                    "}\n" +
                    "table {\nfont-family: \"Lucida Sans Unicode\", \"Lucida Grande\", Sans-Serif;\n" +
                    "    text-align: center;\n" +
                    "    border-collapse: collapse;\n" +
                    "    border-spacing: 0px;\n" +
                    "    background: #ECE9E0;\n" +
                    "    color: #656665;\n" +
                    "    border: 20px solid #ECE9E0;\n" +
                    "    border-radius: 20px;"+
                    "}\n" +
                    "th {\n" +
                    "    font-size: 18px;\n" +
                    "    padding: 10px;\n" +
                    "}\n" +
                    "td {\n" +
                    "    background: #F5D7BF;\n" +
                    "    padding: 10px;\n" +
                    "}\n" +
                    "tr:hover{\n" +
                    "    color: white;\n" +
                    "    background-color: black;\n" +
                    "}\n" +
                    "td:hover{\n" +
                    "    color: white;\n" +
                    "    background-color: black;\n" +
                    "}"+
                    "</style>");

            pw.println("<div class=\"wrapper\">");

            pw.println("<div class=\"elem\">\n" +
                    "  <table class=\"table-pilots\" border=\"1\">\n" +
                    "      <tr>\n" +
                    "        <th>Личный номер пилота</th>\n" +
                    "        <th>Имя пилота</th>\n" +
                    "        <th>Фамилия пилота</th>\n" +
                    "        <th>Дата рождения</th>\n" +
                    "      </tr>\n" + formula1.pilotsToHtmlString() +
                    "</table>\n" +
                    "</div>");

            pw.println("<div class=\"elem\">\n" +
                    "  <table class=\"table-bolids\" border=\"1\">\n" +
                    "      <tr>\n" +
                    "        <th>Название болида</th>\n" +
                    "        <th>Название движка</th>\n" +
                    "        <th>Название шасси</th>\n" +
                    "        <th>Год болида</th>\n" +
                    "      </tr>\n" + formula1.bolidsToHtmlString() +
                    "  </table>\n" +
                    "</div>");

            pw.println("</div>");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void connectToDb() throws ClassNotFoundException, SQLException {
        connector.connect();
        connector.querySelectAndInsert("select * from \"Pilots\";", Pilots.fieldClassNames, formula1,
                "Pilots");
        connector.querySelectAndInsert("select * from \"Bolids\";", Bolids.fieldClassNames, formula1,
                "Bolids");
        connector.closeConnection();

    }
}
