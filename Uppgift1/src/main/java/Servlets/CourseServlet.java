package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "/coursesservlet", urlPatterns = "/courses") //mapping
public class CourseServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //PORT and DbName should be changed

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3387/gritacademy2",
                    "root", "");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from courses");
            String top = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>Kurser på Grit Academy</title>\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"ISO-8859-1\">" +
                    "<style>" +
                    "body {" +
                    "  background-image: linear-gradient(pink, white);\n" +
                    "}" +

                    "h1 {" +
                    "text-align:center;" +
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
                    "margin-top: 280px;" +
                    "border-style: dotted;\n" +
                    "border-width: thick;" +
                    "text-align:center;" +
                    "}" +
                    "}" +

                    "</style>" +

                    "</head>\n" +
                    "    <body>\n" +
                    "        <h1>Här har ni alla kurser</h1>";
            PrintWriter out = resp.getWriter();
            out.println(top);
            out.println("<table style=\"text-align:center;\">\n" +
                    "  <tr>\n" +
                    "   \t<th style=\"text-decoration: underline;\">Kurs</th>\n" +
                    "   \t<th style=\"text-decoration: underline;\">YH-poäng</th>\n" +
                    "   \t<th style=\"text-decoration: underline;\">Beskrivning</th>\n" +
                    "  </tr>\n");
            while (rs.next()) {
                String course = rs.getString(2);
                int yhp = rs.getInt(3);
                String descr = rs.getString(4);
                //System.out.print(", Kurs: " + course + ", YH-poäng: " + yhp + ", Beskrivning: " + descr + "\n");

                out.println("<tr>\n" +
                        "\t<th>" + course + "</th>\n" +
                        "\t<th>" + yhp + "</th>\n" +
                        "\t<th>" + descr + "</th>\n" +
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
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    //doPost ör vidarebefordrat från HomeServlet
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("fname"));
        System.out.println(req.getParameter("lname"));
        resp.sendRedirect("/students");
    }
}