import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/TEST";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database");


            String insertQuery = "INSERT INTO employee VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery);


            ps.setInt(1, 1);
            ps.setString(2, "Rahul");
            ps.setString(3, "IT");
            ps.setInt(4, 50000);
            ps.executeUpdate();


            ps.setInt(1, 2);
            ps.setString(2, "Priya");
            ps.setString(3, "HR");
            ps.setInt(4, 45000);
            ps.executeUpdate();

            System.out.println("Records Inserted");


            String updateQuery = "UPDATE employee SET salary=? WHERE id=?";
            PreparedStatement ps2 = con.prepareStatement(updateQuery);

            ps2.setInt(1, 55000);
            ps2.setInt(2, 1);
            ps2.executeUpdate();

            System.out.println("Salary Updated");
            String selectQuery = "SELECT * FROM employee";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            System.out.println("Employee Records:");

            while(rs.next()){
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("department") + " " +
                                rs.getInt("salary")
                );
            }
            rs.close();
            stmt.close();
            ps.close();
            ps2.close();
            con.close();

            System.out.println("Connection Closed");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}