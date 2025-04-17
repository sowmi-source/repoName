import java.sql.*;

public class AprilPurchase {

    public static void main(String[] args) {
        try {
            aprilSummary();
        } catch (Exception e) {
            System.out.println("An error occurred the April purchase summary.");
            e.printStackTrace();  
        }
    }
 // connection details
    public static void aprilSummary() throws Exception {
        String userName = "root";
        String password = "12345";
        String url = "jdbc:mysql://localhost:3306/logicfirst";

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement stmt = con.createStatement();   

        
        String totalQuery = "SELECT SUM(amount) AS total_spent FROM purchase " +
                            "WHERE MONTH(purchase_date) = 4 AND YEAR(purchase_date) = 2025";

        ResultSet rs = stmt.executeQuery(totalQuery);
        if (rs.next()) {
            int totalSpent = rs.getInt("total_spent");
            System.out.println("Total Spending in April 2025: " + totalSpent);
        }

    //jkklk
        String categoryQuery = "SELECT category, SUM(amount) AS total FROM purchase " +
                               "WHERE MONTH(purchase_date) = 4 AND YEAR(purchase_date) = 2025 " +
                               "GROUP BY category ORDER BY total DESC";

        ResultSet rs = stmt.executeQuery(categoryQuery);

        System.out.println("Spending by Category in April 2025:");
        while (rs.next()) {
            String category = rs.getString("category");
            int total = rs.getInt("total");
            System.out.println( category + ": " + total);
        } 

        rs.close();
        stmt.close();
        con.close();
    }
}



