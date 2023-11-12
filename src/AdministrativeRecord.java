import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrativeRecord {
    private String patientName;
    private double paymentAmount;

    public AdministrativeRecord(String patientName, double paymentAmount) {
        this.patientName = patientName;
        this.paymentAmount = paymentAmount;
    }

    public String getPatientName() {
        return patientName;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    // 1. Create (menambahkan catatan administrasi baru)
    public void addAdministrativeRecordToDatabase(AdministrativeRecord adminRecord) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "INSERT INTO AdministrativeRecord (patientName, paymentAmount) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, adminRecord.getPatientName());
            statement.setDouble(2, adminRecord.getPaymentAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Read (mengambil informasi catatan administrasi)
    public List<AdministrativeRecord> getAllAdministrativeRecordsFromDatabase() {
        List<AdministrativeRecord> adminRecords = new ArrayList<>();
        Connection connection = Koneksi.getKoneksi();
        String sql = "SELECT * FROM AdministrativeRecord";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String patientName = resultSet.getString("patientName");
                double paymentAmount = resultSet.getDouble("paymentAmount");
                adminRecords.add(new AdministrativeRecord(patientName, paymentAmount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminRecords;
    }

    // 3. Update (mengubah data catatan administrasi)
    public void updateAdministrativeRecordInDatabase(String patientName, double paymentAmount) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "UPDATE AdministrativeRecord SET paymentAmount = ? WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, paymentAmount);
            statement.setString(2, patientName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // 4. Delete (menghapus catatan administrasi)
    public void deleteAdministrativeRecordFromDatabase(String patientName) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "DELETE FROM AdministrativeRecord WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patientName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
