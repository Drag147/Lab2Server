package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<style>\n" +
                ".wrapper{\n" +
                "  display: flex;\n" +
                "  flex-wrap: wrap;\n" +
                "  justify-content: space-between;\n" +
                "}\n" +
                ".wrapper .elem1{\n" +
                "  padding: 90px;\n" +
                "  font-size: 50px;\n" +
                "  border: 1px solid black;\n" +
                "  border-radius: 50px;\n" +
                "  display: inline-block;\n" +
                "  width: auto;\n" +
                "  height: auto;\n" +
                "  background-color: mintcream;\n" +
                "}\n" +
                ".wrapper .elem2{\n" +
                "  padding: 90px;\n" +
                "  font-size: 50px;\n" +
                "  border: 1px solid black;\n" +
                "  border-radius: 50px;\n" +
                "  display: inline-block;\n" +
                "  background-color: blanchedalmond;\n" +
                "}\n" +
                ".elem1:hover{\n" +
                "  color: white;\n" +
                "}\n" +
                ".elem2:hover{\n" +
                "  background-color: black;\n" +
                "}\n" +
                "</style>\n" +
                "<div class=\"wrapper\">\n" +
                "  <div class=\"elem1\">Привет я блок 1!!!!!!!!</div>\n" +
                "  <div class=\"elem2\">Привет я блок 2!!!!!!!!</div>\n" +
                "</div>");

    }
}
