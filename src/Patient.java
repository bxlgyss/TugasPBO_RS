import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Patient {
    private String name;
    private String dateOfBirth;
    private String address;

    public Patient(String name, String dateOfBirth, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Patient() {

    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    // 1. Create (menambahkan pasien baru)
    public void addPatientToDatabase(Patient patient) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "INSERT INTO Pasient (name, dateOfBirth, address) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getDateOfBirth());
            statement.setString(3, patient.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Read (mengambil informasi pasien)
    public List<Patient> getAllPatientsFromDatabase() {
        List<Patient> patients = new ArrayList<>();
        Connection connection = Koneksi.getKoneksi();
        String sql = "SELECT * FROM Pasient";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String address = resultSet.getString("address");
                patients.add(new Patient(name, dateOfBirth, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }


    // 3. Update (mengubah data pasien)
    public void updatePatientInDatabase(String originalName, String updatedDOB, String updatedAddress) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "UPDATE Pasient SET dateOfBirth = ?, address = ? WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedDOB);
            statement.setString(2, updatedAddress);
            statement.setString(3, originalName);  // Use the original name in the WHERE clause
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // 4. Delete (menghapus pasien)
    public void deletePatientFromDatabase(String name) {
        Connection connection = Koneksi.getKoneksi();
        String sql = "DELETE FROM Pasient WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

