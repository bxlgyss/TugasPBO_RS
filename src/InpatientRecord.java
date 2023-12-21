import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InpatientRecord {
    private String patientName;
    private String roomType;
    private String diagnosis;
    private String responsibleNurse;

    public InpatientRecord(String patientName, String roomType, String diagnosis, String responsibleNurse) {
        this.patientName = patientName;
        this.roomType = roomType;
        this.diagnosis = diagnosis;
        this.responsibleNurse = responsibleNurse;
    }

    public InpatientRecord() {

    }

    public String getPatientName() {
        return patientName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getResponsibleNurse() {
        return responsibleNurse;
    }

    // 1. Create (menambahkan data pasien rawat inap baru)
    public void addInpatientRecordToDatabase(InpatientRecord inpatientRecord) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "INSERT INTO InpatientRecord (patientName, roomType, diagnosis, responsibleNurse) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, inpatientRecord.getPatientName());
            statement.setString(2, inpatientRecord.getRoomType());
            statement.setString(3, inpatientRecord.getDiagnosis());
            statement.setString(4, inpatientRecord.getResponsibleNurse());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Read (mengambil informasi data pasien rawat inap)
    public List<InpatientRecord> getAllInpatientRecordsFromDatabase() {
        List<InpatientRecord> inpatientRecords = new ArrayList<>();
        Connection connection = Koneksi.getKoneksi();
        String sql = "SELECT * FROM InpatientRecord";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String patientName = resultSet.getString("patientName");
                String roomType = resultSet.getString("roomType");
                String diagnosis = resultSet.getString("diagnosis");
                String responsibleNurse = resultSet.getString("responsibleNurse");
                inpatientRecords.add(new InpatientRecord(patientName, roomType, diagnosis, responsibleNurse));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inpatientRecords;
    }

    // 3. Update (mengubah data pasien rawat inap)
    public void updateInpatientRecordInDatabase(String patientName, String roomType, String diagnosis, String responsibleNurse) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "UPDATE InpatientRecord SET roomType = ?, diagnosis = ?, responsibleNurse = ? WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, roomType);
            statement.setString(2, diagnosis);
            statement.setString(3, responsibleNurse);
            statement.setString(4, patientName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // 4. Delete (menghapus data pasien rawat inap)
    public static void deleteInpatientRecordFromDatabase(String patientName) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "DELETE FROM InpatientRecord WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patientName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
