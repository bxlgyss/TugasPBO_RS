import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRecord {
    private String patientName;
    private double treatmentCost;
    private double medicineCost;

    public PaymentRecord(String patientName, double treatmentCost, double medicineCost) {
        this.patientName = patientName;
        this.treatmentCost = treatmentCost;
        this.medicineCost = medicineCost;
    }

    public String getPatientName() {
        return patientName;
    }

    public double getTreatmentCost() {
        return treatmentCost;
    }

    public double getMedicineCost() {
        return medicineCost;
    }

    // 1. Create (menambahkan pembayaran baru)
    public void addPaymentRecordToDatabase(PaymentRecord paymentRecord) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "INSERT INTO PaymentRecord (patientName, treatmentCost, medicineCost) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, paymentRecord.getPatientName());
            statement.setDouble(2, paymentRecord.getTreatmentCost());
            statement.setDouble(3, paymentRecord.getMedicineCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Read (mengambil data pembayaran)
    public List<PaymentRecord> getAllPaymentRecordsFromDatabase() {
        List<PaymentRecord> paymentRecords = new ArrayList<>();
        Connection connection = Koneksi.getKoneksi();
        String sql = "SELECT * FROM PaymentRecord";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String patientName = resultSet.getString("patientName");
                double treatmentCost = resultSet.getDouble("treatmentCost");
                double medicineCost = resultSet.getDouble("medicineCost");
                paymentRecords.add(new PaymentRecord(patientName, treatmentCost, medicineCost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentRecords;
    }

    // 3. Update (mengubah data pembayaran)
    public void updatePaymentRecordInDatabase(String patientName, double treatmentCost, double medicineCost) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "UPDATE PaymentRecord SET treatmentCost = ?, medicineCost = ? WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, treatmentCost);
            statement.setDouble(2, medicineCost);
            statement.setString(3, patientName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // 4. Delete (menghapus data pembayaran)
    public void deletePaymentRecordFromDatabase(String patientName) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "DELETE FROM PaymentRecord WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patientName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
