
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalSummary {
    private String patientName;
    private String medicalExamination;
    private String progressNotes;
    private String prescription;

    public MedicalSummary(String patientName, String medicalExamination, String progressNotes, String prescription) {
        this.patientName = patientName;
        this.medicalExamination = medicalExamination;
        this.progressNotes = progressNotes;
        this.prescription = prescription;
    }

    public MedicalSummary() {

    }

    // Getter dan Setter untuk setiap atribut (patientName, medicalExamination, progressNotes, prescription)
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedicalExamination() {
        return medicalExamination;
    }

    public void setMedicalExamination(String medicalExamination) {
        this.medicalExamination = medicalExamination;
    }

    public String getProgressNotes() {
        return progressNotes;
    }

    public void setProgressNotes(String progressNotes) {
        this.progressNotes = progressNotes;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    // 1. Create (menambahkan ringkasan medis baru)
    public void addMedicalSummaryToDatabase(MedicalSummary medicalSummary) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "INSERT INTO MedicalSummary (patientName, medicalExamination, progressNotes, prescription) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medicalSummary.getPatientName());
            statement.setString(2, medicalSummary.getMedicalExamination());
            statement.setString(3, medicalSummary.getProgressNotes());
            statement.setString(4, medicalSummary.getPrescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Read (mengambil ringkasan medis)
    public List<MedicalSummary> getAllMedicalSummariesFromDatabase() {
        List<MedicalSummary> medicalSummaries = new ArrayList<>();
        Connection connection = Koneksi.getKoneksi();
        String sql = "SELECT * FROM MedicalSummary";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String patientName = resultSet.getString("patientName");
                String medicalExamination = resultSet.getString("medicalExamination");
                String progressNotes = resultSet.getString("progressNotes");
                String prescription = resultSet.getString("prescription");
                medicalSummaries.add(new MedicalSummary(patientName, medicalExamination, progressNotes, prescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicalSummaries;
    }

    // 3. Update (mengubah data ringkasan medis)
    public boolean updateMedicalSummaryInDatabase(String patientName, String medicalExamination, String progressNotes, String prescription) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "UPDATE MedicalSummary SET medicalExamination = ?, progressNotes = ?, prescription = ? WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medicalExamination);
            statement.setString(2, progressNotes);
            statement.setString(3, prescription);
            statement.setString(4, patientName);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row is affected
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // 4. Delete (menghapus ringkasan medis)
    public boolean deleteMedicalSummaryFromDatabase() {
        Connection connection = Koneksi.getKoneksi();
        String sql = "DELETE FROM MedicalSummary WHERE patientName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patientName);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row is affected
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

