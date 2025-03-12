import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FraudDetection {

    public static void detectFraud() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM transactions";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String transactionID = rs.getString("transaction_id");
                int customerID = rs.getInt("customer_id");
                double amount = rs.getDouble("amount");
                String type = rs.getString("transaction_type");
                String location = rs.getString("location");
                int isFraud = rs.getInt("is_fraud");

                // Simple fraud detection logic
                if (amount > 10000 || type.equalsIgnoreCase("International Transfer")) {
                    System.out.println("🚨 Fraud detected! Transaction ID: " + transactionID);
                } else {
                    System.out.println("✅ Transaction ID: " + transactionID + " is safe.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        detectFraud();
    }
}
