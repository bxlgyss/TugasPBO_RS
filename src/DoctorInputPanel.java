import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorInputPanel {
    private Connection connection;
    private DefaultTableModel doctorTableModel;
    private JFrame frame;

    public DoctorInputPanel(JFrame frame) {
        this.frame = frame;
        connection = Koneksi.getKoneksi();

        // Inisialisasi model tabel
        String[] columnNames = {"Nama Dokter", "Spesialisasi", "Umur", "Jenis Kelamin", "Nomor Kontak"};
        doctorTableModel = new DefaultTableModel(columnNames, 0);

        openDoctorInputPanel();
    }

    private void openDoctorInputPanel() {
        JDialog doctorDialog = new JDialog(frame, "Tambah Data Dokter", true);
        doctorDialog.setSize(400, 300);
        JPanel doctorInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextField specializationField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JTextField genderField = new JTextField(20);
        JTextField contactNumberField = new JTextField(20);

        addLabelAndComponent(doctorInputPanel, constraints, "Nama Dokter:", nameField);
        constraints.gridy++;
        addLabelAndComponent(doctorInputPanel, constraints, "Spesialisasi:", specializationField);
        addLabelAndComponent(doctorInputPanel, constraints, "Umur:", ageField);
        addLabelAndComponent(doctorInputPanel, constraints, "Jenis Kelamin:", genderField);
        addLabelAndComponent(doctorInputPanel, constraints, "Nomor Kontak:", contactNumberField);

        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorName = nameField.getText();
                String specialization = specializationField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String contactNumber = contactNumberField.getText();

                if (!doctorName.isEmpty()) {
                    Doctor doctor = new Doctor(doctorName, specialization, age, gender, contactNumber);
                    saveDoctorToDatabase(doctor);

                    String[] rowData = {doctorName, specialization, String.valueOf(age), gender, contactNumber};
                    doctorTableModel.addRow(rowData);

                    clearFields(nameField, specializationField, ageField, genderField, contactNumberField);
                    doctorDialog.dispose();
                }
            }
        });

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        doctorInputPanel.add(saveButton, constraints);

        doctorDialog.add(doctorInputPanel);
        doctorDialog.setVisible(true);
    }

    private void saveDoctorToDatabase(Doctor doctor) {
        String query = "INSERT INTO nama_tabel_dokter (nama_dokter, spesialisasi, umur, jenis_kelamin, nomor_kontak) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getDoctorName());
            statement.setString(2, doctor.getSpecialization());
            statement.setInt(3, doctor.getAge());
            statement.setString(4, doctor.getGender());
            statement.setString(5, doctor.getContactNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addLabelAndComponent(JPanel panel, GridBagConstraints constraints, String labelName, Component component) {
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(labelName), constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(component, constraints);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);

        DoctorInputPanel inputPanel = new DoctorInputPanel(frame);
    }
}
