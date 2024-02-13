package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/attendance") //mapping
public class AttendanceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //PORT and DbName should be changed

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3387/gritacademy2",
                    "root", "");
            Statement stmt = con.createStatement();
            String top ="<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>Närvaro</title>\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"ISO-8859-1\">" +
                    "<style>" +
                    "body {" +
                    "  background-image: linear-gradient(blue, white);\n" +
                    "}" +
                    "}" +

                    "h1 {" +
                    "text-align: center;" +
                    "color: black;" +
                    "}" +

                    "table {" +
                    "padding-top: 50px;" +
                    "padding-right: 30px;" +
                    "padding-bottom: 50px;" +
                    "padding-left: 80px;" +
                    "margin-left: auto;" +
                    "margin-right: auto;" +
                    "}" +

                    "footer {" +
                    "margin-top: 175px;" +
                    "border-style: dotted;\n" +
                    "border-width: thick;" +
                    "text-align:center;" +
                    "}" +
                    "}" +

                    "</style>" +

                    "</head>\n" +
                    "    <body>\n" +
                    "        <h1 style=\"text-align: center;\">Här ser man vilka kurser en del av våra studenter går</h1>" +
                    "<table style=\"text-align:center;\">\n" +
                    "  <tr>\n" +
                    "   \t<th style=\"text-decoration: underline;\">Namn</th>\n" +
                    "   \t<th style=\"text-decoration: underline;\">Kurs</th>\n" +
                    "   \t<th style=\"text-decoration: underline;\">Beskrivning</th>\n" +
                    "  </tr>\n";
            out.println(top);
            ResultSet rs = stmt.executeQuery("SELECT s.fname, s.lname AS \"Student\", c.name AS \"Course\", c.description AS " +
                    "\"Course description\" FROM attendance a LEFT JOIN students s ON s.id = a.students_id INNER JOIN " +
                    "courses c ON c.id = a.courses_id ORDER BY s.fname;");
            while (rs.next()) {

                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String course = rs.getString(3);
                String description = rs.getString(4);
                //System.out.print("name: " + fname + " " + lname + ", course: " + course + ", Beskrivning: " + description + "\n");

                out.println("<tr>\n" +
                        "\t<th>" + fname + " " + lname + "</th>\n" +
                        "\t<th>" + course + "</th>\n" +
                        "\t<th>" + description + "</th>\n" +
                        "  </tr>\n");
            }
            out.println("</table>" +
                    "    </body>\n" +
                    "<footer>\n" +
                    "  <p>Author: Andreas Jabrell</p>\n" +
                    "  <p><a href=\"http://localhost:9090/\" title=\"TILLBAKA TILL MAIN\"> TILLBAKA TILL MAIN</a></p>\n" +
                    "</footer>" +
                    "</html>");
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

