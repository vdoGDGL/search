import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");

        String resCode = "";
        String resMessage = "";
        String userID = "";

        ArrayList<commodity> list = new ArrayList<>();

        try {
            Connection c = (Connection) DBlink.getConnection();
            Statement statement1 = (Statement) c.createStatement();
            Statement statement2 = (Statement) c.createStatement();
            Statement statement3 = (Statement) c.createStatement();
            ResultSet result1;
            ResultSet result2;
            ResultSet result3;

            String sql1 = "select * from platform1 where name like '%" + name+ "%'";
            String sql2 = "select * from platform2 where name like '%" + name+ "%'";
            String sql3 = "select * from platform3 where name like '%" + name+ "%'";

            result1 = statement1.executeQuery(sql1);
            while(result1.next()){
                commodity c1 = new commodity();
                c1.cname = result1.getString(1);
                c1.cprice = result1.getString(2);
                c1.clink = result1.getString(3);
                list.add(c1);
            }

            result2 = statement2.executeQuery(sql2);
            while(result2.next()){
                commodity c1 = new commodity();
                c1.cname = result2.getString(1);
                c1.cprice = result2.getString(2);
                c1.clink = result2.getString(3);
                list.add(c1);
            }

            result3 = statement2.executeQuery(sql3);
            while(result3.next()){
                commodity c1 = new commodity();
                c1.cname = result3.getString(1);
                c1.cprice = result3.getString(2);
                c1.clink = result3.getString(3);
                list.add(c1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        commodity c2 = new commodity();
        int i = 0;
        while(i < list.size()){
            c2 = list.get(i);
            pw.println("<p>" +
                    c2.cname + ": price: " + c2.cprice + " link: " + c2.clink + "</p>");
            i = i + 1;
        }
        pw.println("</html>");
        pw.flush();
    }
}
