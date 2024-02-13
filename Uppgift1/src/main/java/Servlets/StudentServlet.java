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


@WebServlet(urlPatterns = "/students") //mapping
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //PORT and DbName should be changed

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3387/gritacademy2",
                    "root", "");
            Statement stmt = con.createStatement();
            //TABLENAME should be changed

            ResultSet rs = stmt.executeQuery("select * from students");

            String top = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>Studenter på Grit Academy</title>\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"ISO-8859-1\">" +
                    "<style>" +
                    "body {" +
                    "  background-image: linear-gradient(purple, white);\n" +
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
                    "        <h1>Här har ni alla studenter</h1>";

            PrintWriter out = resp.getWriter();
            out.println(top);
            out.println(
                    "<table style=\"text-align:center;\">\n" +
                            "  <tr>\n" +
                            "   \t<th style=\"text-decoration: underline;\">Förnamn</th>\n" +
                            "   \t<th style=\"text-decoration: underline;\">Efternamn</th>\n" +
                            "   \t<th style=\"text-decoration: underline;\">Stad</th>\n" +
                            "   \t<th style=\"text-decoration: underline;\">Hobby</th>\n" +
                            "  </tr>\n");
            while (rs.next()) {

                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String town = rs.getString(4);
                String hobby = rs.getString(5);
                //System.out.print(", First name: " + fname + ", Last name: " + lname + ", town: " + town + ", Hobby: " + hobby + "\n");

                out.println(
                        "<tr>\n" +
                                "\t<th>" + fname + "</th>\n" +
                                "\t<th>" + lname + "</th>\n" +
                                "\t<th>" + town + "</th>\n" +
                                "\t<th>" + hobby + "</th>\n" +
                                "  </tr>\n");
            }
            out.println("</table>" +
                    "    </body>\n" +
                    "       <footer>\n" +
                    "        <p>Author: Andreas Jabrell</p>\n" +
                    "        <p><a href=\"http://localhost:9090/\" title=\"TILLBAKA TILL MAIN\"> TILLBAKA TILL MAIN</a></p>\n" +
                    "       </footer>" +
                    "</html>");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}