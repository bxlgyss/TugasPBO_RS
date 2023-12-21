import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.mysql.jdbc.Connection;

import java.sql.SQLException;


public class HospitalManagementApp {
    private JFrame frame;
    private List<String> doctors;
    private List<String> nurses;
    private List<String> medicines;
    private List<String> diagnoses;
    private JTable registrationTable;
    
    private DefaultTableModel tableModel;
    private DefaultTableModel doctorTableModel;
    private DefaultTableModel nurseTableModel;
    private DefaultTableModel medicineTableModel;
    private DefaultTableModel diagnosisTableModel;
    private JTable adminTable;
    private DefaultTableModel adminTableModel;
    private DefaultTableModel inpatientTableModel;
    private JTable inpatientTable;
    private DefaultTableModel paymentTableModel;
    private JTable paymentTable;
    private JTable medicalRecordTable;

    public HospitalManagementApp() {
        frame = new JFrame("Aplikasi Manajemen Rumah Sakit");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Membuat panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());

        try {
            // Load gambar latar belakang
            BufferedImage backgroundImage = ImageIO.read(new File("C:\\Users\\BALGIS\\IdeaProjects\\untitled2\\c229140c547d792f2891111be747a2a7\\2png.png"));

            // Membuat panel latar belakang dengan gambar
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };

            // Menambahkan panel latar belakang ke panel utama
            mainPanel.add(backgroundPanel, BorderLayout.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setContentPane(mainPanel);

        JPanel dataMasterPanel = new JPanel();
        addDataMasterPanelComponents(dataMasterPanel);

        JPanel pendaftaranPanel = new JPanel();
        addPendaftaranPanelComponents(pendaftaranPanel);

        JPanel administrasiPanel = new JPanel();
        addAdministrasiPanelComponents(administrasiPanel);

        JPanel rawatInapPanel = new JPanel();
        addRawatInapPanelComponents(rawatInapPanel);

        JPanel pembayaranPanel = new JPanel();
        addPembayaranPanelComponents(pembayaranPanel);

        JPanel rekapMedisPanel = new JPanel();
        addRekapMedisPanelComponents(rekapMedisPanel);

        JMenuBar menuBar = new JMenuBar();

        // Membuat menu Data Master
        JMenu dataMasterMenu = new JMenu("Data Master");
        menuBar.add(dataMasterMenu);
        JMenuItem dataMasterMenuItem = new JMenuItem("Input Data Master");
        JMenuItem dataMasterLaporan = new JMenuItem("Laporan Data Master");
        dataMasterMenu.add(dataMasterMenuItem);
        dataMasterMenu.add(dataMasterLaporan);

        // Membuat menu Pendaftaran
        JMenu pendaftaranMenu = new JMenu("Pendaftaran");
        menuBar.add(pendaftaranMenu);
        JMenuItem pendaftaranMenuItem = new JMenuItem("Input Data Pendaftaran");
        JMenuItem pendaftaranLaporan = new JMenuItem("Laporan Pendaftaran");
        pendaftaranMenu.add(pendaftaranMenuItem);
        pendaftaranMenu.add(pendaftaranLaporan);

        // Membuat menu Administrasi
        JMenu administrasiMenu = new JMenu("Administrasi");
        menuBar.add(administrasiMenu);
        JMenuItem administrasiMenuItem = new JMenuItem("Input Data Administrasi");
        JMenuItem administrasiLaporan = new JMenuItem("Laporan Administrasi");
        administrasiMenu.add(administrasiMenuItem);
        administrasiMenu.add(administrasiLaporan);


        // Membuat menu Rawat Inap
        JMenu rawatInapMenu = new JMenu("Rawat Inap");
        menuBar.add(rawatInapMenu);
        JMenuItem rawatInapMenuItem = new JMenuItem("Input Data Rawat Inap");
        JMenuItem rawatInapLaporan = new JMenuItem("Laporan Rawat Inap");
        rawatInapMenu.add(rawatInapMenuItem);
        rawatInapMenu.add(rawatInapLaporan);

        // Membuat menu Pembayaran
        JMenu pembayaranMenu = new JMenu("Pembayaran");
        menuBar.add(pembayaranMenu);
        JMenuItem pembayaranMenuItem = new JMenuItem("Input Data Pembayaran");
        JMenuItem pembayaranLaporan = new JMenuItem("Laporan Pembayaran");
        pembayaranMenu.add(pembayaranMenuItem);
        pembayaranMenu.add(pembayaranLaporan);

        // Membuat menu Rekap Medis
        JMenu rekapMedisMenu = new JMenu("Rekap Medis");
        menuBar.add(rekapMedisMenu);
        JMenuItem rekapMedisMenuItem = new JMenuItem("Input Data Rekap Medis");
        JMenuItem rekapMedisLaporan = new JMenuItem("Laporan Rekap Medis");
        rekapMedisMenu.add(rekapMedisMenuItem);
        rekapMedisMenu.add(rekapMedisLaporan);

        frame.setJMenuBar(menuBar);

        dataMasterMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(dataMasterPanel, BorderLayout.CENTER);
                frame.repaint();
                frame.revalidate();
            }
        });
        dataMasterLaporan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle aksi untuk laporan Data Master
                generateDataMasterReport();
            }
        });

        pendaftaranMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(pendaftaranPanel, BorderLayout.CENTER);
                frame.repaint();
                frame.revalidate();
            }
        });
        pendaftaranLaporan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle aksi untuk laporan Pendaftaran
                generatePendaftaranReport();
            }
        });

        administrasiMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(administrasiPanel, BorderLayout.CENTER);
                frame.repaint();
                frame.revalidate();
            }
        });
        administrasiLaporan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle aksi untuk laporan Administrasi
                generateAdministrasiReport();
            }
        });

        rawatInapMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(rawatInapPanel, BorderLayout.CENTER);
                frame.repaint();
                frame.revalidate();
            }
        });
        rawatInapLaporan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle aksi untuk laporan Rawat Inap
                generateRawatInapReport();
            }
        });

        pembayaranMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(pembayaranPanel, BorderLayout.CENTER);
                frame.repaint();
                frame.revalidate();
            }
        });
        pembayaranLaporan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle aksi untuk laporan Pembayaran
                generatePembayaranReport();
            }
        });

        rekapMedisMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(rekapMedisPanel, BorderLayout.CENTER);
                frame.repaint();
                frame.revalidate();
            }
        });
        rekapMedisLaporan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle aksi untuk laporan Rekap Medis
                generateRekapMedisReport();
            }
        });
        dataMasterPanel.setBackground(new Color(109, 165, 192));
        pendaftaranPanel.setBackground(new Color(109, 165, 192));
        administrasiPanel.setBackground(new Color(109, 165, 192));
        rawatInapPanel.setBackground(new Color(109, 165, 192));
        pembayaranPanel.setBackground(new Color(109, 165, 192));
        rekapMedisPanel.setBackground(new Color(109, 165, 192));

        doctors = new ArrayList<>();
        nurses = new ArrayList<>();
        medicines = new ArrayList<>();
        diagnoses = new ArrayList();

        frame.setVisible(true);
    }
    private List<Patient> registeredPatients = new ArrayList<>();
    private List<AdministrativeRecord> administrativeRecords = new ArrayList<>();
    private List<InpatientRecord> inpatientRecords = new ArrayList<>();
    private List<PaymentRecord> paymentRecords = new ArrayList<>();
    private List<MedicalSummary> medicalSummaries = new ArrayList<>();
    private void addDataMasterPanelComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Input Data Master");
        titleLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 30));
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);
        constraints.gridy++;

        // Tombol untuk menambah data dokter
        JButton addDoctorButton = new JButton("Input Data Dokter");
        addDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Munculkan panel untuk memasukkan data dokter
                openDoctorInputPanel();
            }
        });

        addDoctorButton.setPreferredSize(new Dimension(200, 40));  // Mengubah ukuran tombol
        addDoctorButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 16));
        addDoctorButton.setBackground(Color.white);
        constraints.gridy++;
        addComponent(panel, addDoctorButton, constraints);

        // Tombol untuk menambah data perawat
        JButton addNurseButton = new JButton("Input Data Perawat");
        addNurseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Munculkan panel untuk memasukkan data perawat
                openNurseInputPanel();
            }
        });
        addNurseButton.setPreferredSize(new Dimension(200, 40));  // Mengubah ukuran tombol
        addNurseButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 16));
        addNurseButton.setBackground(Color.white);
        constraints.gridy++;
        addComponent(panel, addNurseButton, constraints);

        // Tombol untuk menambah data obat
        JButton addMedicineButton = new JButton("Input Data Obat");
        addMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Munculkan panel untuk memasukkan data obat
                openMedicineInputPanel();
            }
        });
        addMedicineButton.setPreferredSize(new Dimension(200, 40));  // Mengubah ukuran tombol
        addMedicineButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 16));
        addMedicineButton.setBackground(Color.white);
        constraints.gridy++;
        addComponent(panel, addMedicineButton, constraints);

        // Tombol untuk menambah data diagnosis
        JButton addDiagnosisButton = new JButton("Input Data Diagnosis");
        addDiagnosisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Munculkan panel untuk memasukkan data diagnosis
                openDiagnosisInputPanel();
            }
        });
        addDiagnosisButton.setPreferredSize(new Dimension(200, 40));  // Mengubah ukuran tombol
        addDiagnosisButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 16));
        addDiagnosisButton.setBackground(Color.white);
        constraints.gridy++;
        addComponent(panel, addDiagnosisButton, constraints);
    }

    private void addComponent(JPanel panel, JComponent component, GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(component, constraints);
    }

    private void openDoctorInputPanel() {
        // Membuat dialog untuk memasukkan data dokter
        JDialog doctorDialog = new JDialog(frame, "Tambah Data Dokter", true);
        doctorDialog.setSize(400, 300);

        // Panel utama menggunakan GridBagLayout
        JPanel doctorInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextField specializationField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JTextField genderField = new JTextField(20);
        JTextField contactNumberField = new JTextField(20);

        // Menambahkan label dan komponen untuk Nama Dokter
        addLabelAndComponent(doctorInputPanel, constraints, "Nama Dokter:", nameField);

        // Mengatur ulang constraints.gridy sebelum menambahkan Spesialisasi
        constraints.gridy++;
        addLabelAndComponent(doctorInputPanel, constraints, "Spesialisasi:", specializationField);

        // Melanjutkan dengan menambahkan komponen untuk umur, jenis kelamin, dan nomor kontak
        addLabelAndComponent(doctorInputPanel, constraints, "Umur:", ageField);
        addLabelAndComponent(doctorInputPanel, constraints, "Jenis Kelamin:", genderField);
        addLabelAndComponent(doctorInputPanel, constraints, "Nomor Kontak:", contactNumberField);

        ArrayList<Doctor> doctors = new ArrayList<Doctor>();

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
                    // Buat objek dokter dan tambahkan ke ArrayList dokter
                    Doctor doctor = new Doctor(doctorName, specialization, age, gender, contactNumber);
                    doctors.add(doctor);

                    // Tambahkan dokter ke model tabel
                    String[] rowData = { doctorName, specialization, String.valueOf(age), gender, contactNumber };
                    doctorTableModel.addRow(rowData);

                    // Kosongkan input fields
                    nameField.setText("");
                    specializationField.setText("");
                    ageField.setText("");
                    genderField.setText("");
                    contactNumberField.setText("");

                    // Tutup dialog
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

    // Metode untuk membuka panel input data perawat
    private void openNurseInputPanel() {
        // Membuat dialog untuk memasukkan data perawat
        JDialog nurseDialog = new JDialog(frame, "Tambah Data Perawat", true);
        nurseDialog.setSize(400, 250);

        // Panel utama menggunakan GridBagLayout
        JPanel nurseInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(1, 5, 5, 5);

        // Membuat input fields
        JTextField nameField = new JTextField(20);
        JTextField specializationField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JTextField genderField = new JTextField(20);
        JTextField contactNumberField = new JTextField(20);

        // Menambahkan label dan input fields ke panel dengan GridBagLayout
        addLabelAndComponent(nurseInputPanel, constraints, "Nama Perawat:", nameField);
        constraints.gridy++;
        addLabelAndComponent(nurseInputPanel, constraints, "Spesialisasi:", specializationField);
        addLabelAndComponent(nurseInputPanel, constraints, "Umur:", ageField);
        addLabelAndComponent(nurseInputPanel, constraints, "Jenis Kelamin:", genderField);
        addLabelAndComponent(nurseInputPanel, constraints, "Nomor Kontak:", contactNumberField);

        // ArrayList untuk menyimpan data perawat
        ArrayList<Nurse> nurses = new ArrayList<Nurse>();

        // Tombol Simpan
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nurseName = nameField.getText();
                String specialization = specializationField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String contactNumber = contactNumberField.getText();

                if (!nurseName.isEmpty()) {
                    // Buat objek perawat dan tambahkan ke ArrayList perawat
                    Nurse nurse = new Nurse(nurseName, specialization, age, gender, contactNumber);
                    nurses.add(nurse);

                    // Tambahkan perawat ke model tabel perawat
                    String[] rowData = { nurseName, specialization, String.valueOf(age), gender, contactNumber };
                    nurseTableModel.addRow(rowData);

                    // Kosongkan input fields
                    nameField.setText("");
                    specializationField.setText("");
                    ageField.setText("");
                    genderField.setText("");
                    contactNumberField.setText("");

                    // Tutup dialog
                    nurseDialog.dispose();
                }
            }
        });

        // Menambahkan tombol Simpan ke panel dengan layout GridBagLayout
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        nurseInputPanel.add(saveButton, constraints);
        constraints.gridy++;

        // Menambahkan panel ke dialog dan menampilkannya
        nurseDialog.add(nurseInputPanel);
        nurseDialog.setVisible(true);
    }


    private void openMedicineInputPanel() {
        // Membuat dialog untuk memasukkan data obat
        JDialog medicineDialog = new JDialog(frame, "Tambah Data Obat", true);
        medicineDialog.setSize(400, 250);

        // Panel utama menggunakan GridBagLayout
        JPanel medicineInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Membuat input fields
        JTextField nameField = new JTextField(20);
        JTextField typeField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(4, 20);

        // Menambahkan label dan input fields ke panel dengan GridBagLayout
        addLabelAndComponent(medicineInputPanel, constraints, "Nama Obat:", nameField);
        constraints.gridy++;
        addLabelAndComponent(medicineInputPanel, constraints, "Tipe Obat:", typeField);
        addLabelAndComponent(medicineInputPanel, constraints, "Deskripsi:", new JScrollPane(descriptionArea));

        ArrayList<Medicine> medicines = new ArrayList<>();

        // Tombol Simpan
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String medicineName = nameField.getText();
                String type = typeField.getText();
                String description = descriptionArea.getText();

                if (!medicineName.isEmpty()) {
                    // Buat objek obat dan tambahkan ke ArrayList obat
                    Medicine medicine = new Medicine(medicineName, type, description);
                    medicines.add(medicine);

                    // Tambahkan obat ke model tabel obat
                    String[] rowData = { medicineName, type, description };
                    // Gantilah 'doctorTableModel' dengan model tabel yang sesuai untuk obat.
                    // medicineTableModel.addRow(rowData);

                    // Kosongkan input fields
                    nameField.setText("");
                    typeField.setText("");
                    descriptionArea.setText("");

                    // Tutup dialog
                    medicineDialog.dispose();
                }
            }
        });

        // Menambahkan tombol Simpan ke panel dengan layout GridBagLayout
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        medicineInputPanel.add(saveButton, constraints);

        // Menambahkan panel ke dialog dan menampilkannya
        medicineDialog.add(medicineInputPanel);
        medicineDialog.setVisible(true);
    }

    private void addLabelAndComponent(JPanel panel, GridBagConstraints constraints, String label, JComponent component) {
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(label), constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        panel.add(component, constraints);

        constraints.gridy++;
    }

    private void openDiagnosisInputPanel() {
        // Membuat dialog untuk memasukkan data diagnosis
        JDialog diagnosisDialog = new JDialog(frame, "Tambah Data Diagnosis", true);
        diagnosisDialog.setSize(400, 300);

        // Panel utama menggunakan GridBagLayout
        JPanel diagnosisInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JTextField patientNameField = new JTextField(20);
        JTextField diseaseField = new JTextField(20);
        JTextArea notesArea = new JTextArea(4, 20);

        addLabelAndComponent(diagnosisInputPanel, constraints, "Nama Pasien:", patientNameField);
        constraints.gridy++;
        addLabelAndComponent(diagnosisInputPanel, constraints, "Penyakit:", diseaseField);
        addLabelAndComponent(diagnosisInputPanel, constraints, "Catatan:", notesArea);

        ArrayList<Diagnosis> diagnoses = new ArrayList<>();

        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                String disease = diseaseField.getText();
                String notes = notesArea.getText();

                if (!patientName.isEmpty()) {
                    // Buat objek diagnosis dan tambahkan ke ArrayList diagnosis
                    Diagnosis diagnosis = new Diagnosis(patientName, disease, notes);
                    diagnoses.add(diagnosis);

                    // Tambahkan diagnosis ke model tabel diagnosis
                    String[] rowData = { patientName, disease, notes };
                    // Gantilah 'doctorTableModel' dengan model tabel yang sesuai untuk diagnosis.
                    // diagnosisTableModel.addRow(rowData);

                    // Kosongkan input fields
                    patientNameField.setText("");
                    diseaseField.setText("");
                    notesArea.setText("");

                    // Tutup dialog
                    diagnosisDialog.dispose();
                }
            }
        });

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        diagnosisInputPanel.add(saveButton, constraints);

        diagnosisDialog.add(diagnosisInputPanel);
        diagnosisDialog.setVisible(true);
    }

    private void addPendaftaranPanelComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Meningkatkan inset
        constraints.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Input Data Pendaftaran");
        titleLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 35)); // Meningkatkan ukuran font
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(new JLabel("Nama Pasien:"), constraints);

        JTextField nameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(nameField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(new JLabel("Tanggal Lahir:"), constraints);

        JTextField dobField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(dobField, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(new JLabel("Alamat:"), constraints);

        JTextField addressField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(addressField, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 4;
        constraints.gridx = 0;
        JButton daftarButton = new JButton("Daftar Pasien Baru");
        panel.add(daftarButton, constraints);

        daftarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String dob = dobField.getText();
                String address = addressField.getText();

                // Buat objek Patient
                Patient newPatient = new Patient(name, dob, address);

                // Tambahkan pasien ke database
                newPatient.addPatientToDatabase(newPatient); // Ini akan menyimpan data ke database

                JOptionPane.showMessageDialog(frame, "Pasien " + name + " berhasil didaftarkan!");

                // Tambahkan data pasien baru ke tabel
                String[] rowData = {name, dob, address};
                tableModel.addRow(rowData);

                nameField.setText("");
                dobField.setText("");
                addressField.setText("");
            }
        });

        String[] columnNames = { "Nama Pasien", "Tanggal Lahir", "Alamat" };
        tableModel = new DefaultTableModel(columnNames, 0);

        // Get all patients from the database
        Patient patientManager = new Patient(); // Use the default constructor
        List<Patient> patients = patientManager.getAllPatientsFromDatabase();

        // Add patient data to the table model
        for (Patient patient : patients) {
            Object[] rowData = {patient.getName(), patient.getDateOfBirth(), patient.getAddress()};
            tableModel.addRow(rowData);
        }

        registrationTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(registrationTable);

        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(tableScrollPane, constraints);

        JButton editButton = new JButton("Edit");
        constraints.gridy = 6;
        panel.add(editButton, constraints);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = registrationTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String name = (String) registrationTable.getValueAt(selectedRow, 0);
                    String dob = (String) registrationTable.getValueAt(selectedRow, 1);
                    String address = (String) registrationTable.getValueAt(selectedRow, 2);

                    EditPatientDialog editDialog = new EditPatientDialog(frame, name, dob, address);
                    editDialog.setVisible(true);
                }
            }
        });
    }
    class EditPatientDialog extends JDialog {
        private JTextField nameField;
        private JTextField dobField;
        private JTextField addressField;

        EditPatientDialog(JFrame parent, String name, String dob, String address) {
            super(parent, "Edit Data Pasien", true);

            // Buat komponen dialog
            nameField = new JTextField(name, 20);
            dobField = new JTextField(dob, 20);
            addressField = new JTextField(address, 20);

            JButton saveButton = new JButton("Simpan");
            JButton deleteButton = new JButton("Hapus"); // Tombol Hapus

            // Tambahkan komponen ke dialog dengan GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(new JLabel("Nama Pasien:"), constraints);
            constraints.gridx = 1;
            panel.add(nameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel("Tanggal Lahir:"), constraints);
            constraints.gridx = 1;
            panel.add(dobField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(new JLabel("Alamat:"), constraints);
            constraints.gridx = 1;
            panel.add(addressField, constraints);

            constraints.gridwidth = 2;
            constraints.gridy = 3;
            panel.add(saveButton, constraints);

            // Tambahkan komponen tombol Hapus
            constraints.gridy = 4;
            panel.add(deleteButton, constraints);

            // Tambahkan aksi untuk tombol Simpan
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = registrationTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String originalName = (String) tableModel.getValueAt(selectedRow, 0); // Get the original name from the table
                        String updatedName = nameField.getText();
                        String updatedDOB = dobField.getText();
                        String updatedAddress = addressField.getText();

                        // Create an object of Patient with existing data
                        Patient existingPatient = new Patient(originalName, "", "");

                        // Call the method to update patient data in the database
                        existingPatient.updatePatientInDatabase(updatedName, updatedDOB, updatedAddress);

                        // Update data in the table model
                        tableModel.setValueAt(updatedName, selectedRow, 0);
                        tableModel.setValueAt(updatedDOB, selectedRow, 1);
                        tableModel.setValueAt(updatedAddress, selectedRow, 2);
                    }
                    dispose();
                }
            });

            // Tambahkan aksi untuk tombol Hapus
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = registrationTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String patientName = (String) registrationTable.getValueAt(selectedRow, 0); // Mengambil nama pasien dari tabel

                        // Buat objek Patient
                        Patient existingPatient = new Patient(patientName, "", "");

                        // Panggil metode untuk menghapus data pasien
                        existingPatient.deletePatientFromDatabase(patientName);

                        // Hapus data pasien dari tabel dan ArrayList registeredPatients
                        tableModel.removeRow(selectedRow);
                        if (selectedRow < registeredPatients.size()) {
                            registeredPatients.remove(selectedRow);
                        }
                    }
                    dispose(); // Tutup dialog pengeditan
                }
            });

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(parent);
        }
    }

    private void addAdministrasiPanelComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Meningkatkan inset
        constraints.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Pencatatan Pembayaran Administrasi");
        titleLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 35));
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(new JLabel("Nama Pasien:"), constraints);

        JTextField patientNameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(patientNameField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(new JLabel("Pembayaran Administrasi (Rp):"), constraints);

        JTextField adminPaymentField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(adminPaymentField, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 3;
        constraints.gridx = 0;
        JButton catatAdminButton = new JButton("Catat Pembayaran Administrasi");
        panel.add(catatAdminButton, constraints);

        catatAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                String adminPayment = adminPaymentField.getText();

                // Handle pencatatan pembayaran administrasi pasien
                JOptionPane.showMessageDialog(frame, "Pembayaran administrasi pasien " + patientName + " sebesar " + adminPayment + " berhasil dicatat.");

                // Buat objek AdministrativeRecord dan tambahkan ke ArrayList administrativeRecords
                AdministrativeRecord adminRecord = new AdministrativeRecord(patientName, Double.parseDouble(adminPayment));

                // Simpan data di database
                adminRecord.addAdministrativeRecordToDatabase(adminRecord);

                // Update the table model
                Object[] rowData = { adminRecord.getPatientName(), adminRecord.getPaymentAmount() };
                adminTableModel.addRow(rowData);

                // Refresh the JTable
                adminTable.setModel(adminTableModel);

                patientNameField.setText("");
                adminPaymentField.setText("");
            }
        });

        // Your existing code for addAdministrasiPanelComponents method
        String[] columnNames = { "Nama Pasien", "Pembayaran Administrasi (Rp)" };
        adminTableModel = new DefaultTableModel(columnNames, 0);

        // Get all administrative records from the database
        AdministrativeRecord adminManager = new AdministrativeRecord(); // Use the default constructor
        List<AdministrativeRecord> adminRecords = adminManager.getAllAdministrativeRecordsFromDatabase();

        // Add administrative record data to the table model
        for (AdministrativeRecord adminRecord : adminRecords) {
            Object[] rowData = { adminRecord.getPatientName(), adminRecord.getPaymentAmount() };
            adminTableModel.addRow(rowData);
        }

        adminTable = new JTable(adminTableModel);
        JScrollPane adminTableScrollPane = new JScrollPane(adminTable);

        constraints = new GridBagConstraints();
        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(adminTableScrollPane, constraints);

        JButton editAdminButton = new JButton("Edit");
        constraints.gridy = 5;
        panel.add(editAdminButton, constraints);

        editAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = adminTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String patientName = (String) adminTable.getValueAt(selectedRow, 0);
                    double adminPayment = (double) adminTable.getValueAt(selectedRow, 1);

                    EditAdminRecordDialog editAdminDialog = new EditAdminRecordDialog(frame, patientName, adminPayment);
                    editAdminDialog.setVisible(true);
                }
            }
        });
    }

    class EditAdminRecordDialog extends JDialog {
        private JTextField patientNameField;
        private JTextField adminPaymentField;

        EditAdminRecordDialog(JFrame parent, String patientName, double adminPayment) {
            super(parent, "Edit Data Pembayaran Administrasi", true);

            // Create dialog components
            patientNameField = new JTextField(patientName, 20);
            adminPaymentField = new JTextField(String.valueOf(adminPayment), 20);

            JButton saveButton = new JButton("Simpan");
            JButton deleteButton = new JButton("Hapus");

            // Add components to the dialog using GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(new JLabel("Nama Pasien:"), constraints);
            constraints.gridx = 1;
            panel.add(patientNameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel("Pembayaran Administrasi (Rp):"), constraints);
            constraints.gridx = 1;
            panel.add(adminPaymentField, constraints);

            constraints.gridwidth = 2;
            constraints.gridy = 2;
            panel.add(saveButton, constraints);

            // Add delete button
            constraints.gridy = 3;
            panel.add(deleteButton, constraints);

            // Add action for save button
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = adminTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String originalName = (String) adminTableModel.getValueAt(selectedRow, 0); // Get the original name from the table
                        double originalAdminPayment = (double) adminTableModel.getValueAt(selectedRow, 1);

                        // Assuming you have a method in AdministrativeRecord to update data
                        AdministrativeRecord existingAdminRecord = new AdministrativeRecord(originalName, originalAdminPayment);

                        // Call the method to update administrative record data in the database
                        existingAdminRecord.updateAdministrativeRecordInDatabase(patientNameField.getText(),
                                Double.parseDouble(adminPaymentField.getText()));

                        // Update data in the table model
                        adminTableModel.setValueAt(patientNameField.getText(), selectedRow, 0);
                        adminTableModel.setValueAt(Double.parseDouble(adminPaymentField.getText()), selectedRow, 1);
                    }
                    dispose();
                }
            });

            // Add action for delete button
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = adminTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String patientName = (String) adminTable.getValueAt(selectedRow, 0);

                        // Assuming you have a method in AdministrativeRecord to delete data
                        AdministrativeRecord.deleteAdministrativeRecordFromDatabase(patientName);

                        // Remove data from the table model
                        adminTableModel.removeRow(selectedRow);
                    }
                    dispose();
                }
            });

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(parent);
        }
    }


    private void addRawatInapPanelComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Pencatatan Rawat Inap Pasien");
        titleLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 35));
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(new JLabel("Nama Pasien:"), constraints);

        JTextField patientNameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(patientNameField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(new JLabel("Jenis Kamar:"), constraints);

        JTextField roomTypeField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(roomTypeField, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(new JLabel("Diagnosis:"), constraints);

        JTextField diagnosisField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(diagnosisField, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        panel.add(new JLabel("Perawat Bertanggung Jawab:"), constraints);

        JTextField nurseField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(nurseField, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 5 ;
        constraints.gridx = 0;
        JButton catatRawatInapButton = new JButton("Catat Rawat Inap Pasien");
        panel.add(catatRawatInapButton, constraints);

        catatRawatInapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                String roomType = roomTypeField.getText();
                String diagnosis = diagnosisField.getText();
                String nurse = nurseField.getText();

                // Handle pencatatan rawat inap pasien
                InpatientRecord inpatientRecord = new InpatientRecord(patientName, roomType, diagnosis, nurse);
                inpatientRecords.add(inpatientRecord);

                // Simpan data di database
                inpatientRecord.addInpatientRecordToDatabase(inpatientRecord);

                JOptionPane.showMessageDialog(frame, "Rawat inap pasien " + patientName + " dengan diagnosis " + diagnosis + " di kamar " + roomType + " yang ditemani oleh perawat " + nurse + " berhasil dicatat.");
                patientNameField.setText("");
                roomTypeField.setText("");
                diagnosisField.setText("");
                nurseField.setText("");
            }
        });
        // Your existing code for addRawatInapPanelComponents method
        String[] inpatientColumnNames = { "Nama Pasien", "Jenis Kamar", "Diagnosis", "Perawat Bertanggung Jawab" };
        inpatientTableModel = new DefaultTableModel(inpatientColumnNames, 0);

        // Get all inpatient records from the database
        InpatientRecord inpatientManager; // Use the default constructor
        inpatientManager = new InpatientRecord();
        List<InpatientRecord> inpatientRecords = inpatientManager.getAllInpatientRecordsFromDatabase();

        // Add inpatient record data to the table model
        for (InpatientRecord inpatientRecord : inpatientRecords) {
            Object[] rowData = { inpatientRecord.getPatientName(), inpatientRecord.getRoomType(),
                    inpatientRecord.getDiagnosis(), inpatientRecord.getResponsibleNurse() };
            inpatientTableModel.addRow(rowData);
        }

        inpatientTable = new JTable(inpatientTableModel);
        JScrollPane inpatientTableScrollPane = new JScrollPane(inpatientTable);

        constraints = new GridBagConstraints();
        constraints.gridy = 5;  // Adjust the gridy value based on your layout
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(inpatientTableScrollPane, constraints);

        JButton editInpatientButton = new JButton("Edit");
        constraints.gridy = 6;  // Adjust the gridy value based on your layout
        panel.add(editInpatientButton, constraints);

        editInpatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inpatientTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String patientName = (String) inpatientTableModel.getValueAt(selectedRow, 0);
                    String roomType = (String) inpatientTableModel.getValueAt(selectedRow, 1);
                    String diagnosis = (String) inpatientTableModel.getValueAt(selectedRow, 2);
                    String nurse = (String) inpatientTableModel.getValueAt(selectedRow, 3);

                    EditInpatientRecordDialog editInpatientDialog = new EditInpatientRecordDialog(frame, patientName, roomType, diagnosis, nurse);
                    editInpatientDialog.setVisible(true);
                }
            }
        });

    }

    class EditInpatientRecordDialog extends JDialog {
        private JTextField patientNameField;
        private JTextField roomTypeField;
        private JTextField diagnosisField;
        private JTextField nurseField;

        EditInpatientRecordDialog(JFrame parent, String patientName, String roomType, String diagnosis, String nurse) {
            super(parent, "Edit Data Rawat Inap Pasien", true);

            // Create dialog components
            patientNameField = new JTextField(patientName, 20);
            roomTypeField = new JTextField(roomType, 20);
            diagnosisField = new JTextField(diagnosis, 20);
            nurseField = new JTextField(nurse, 20);

            JButton saveButton = new JButton("Simpan");
            JButton deleteButton = new JButton("Hapus");

            // Add components to the dialog using GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(new JLabel("Nama Pasien:"), constraints);
            constraints.gridx = 1;
            panel.add(patientNameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel("Jenis Kamar:"), constraints);
            constraints.gridx = 1;
            panel.add(roomTypeField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(new JLabel("Diagnosis:"), constraints);
            constraints.gridx = 1;
            panel.add(diagnosisField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 3;
            panel.add(new JLabel("Perawat Bertanggung Jawab:"), constraints);
            constraints.gridx = 1;
            panel.add(nurseField, constraints);

            constraints.gridwidth = 2;
            constraints.gridy = 4;
            panel.add(saveButton, constraints);

            // Add delete button
            constraints.gridy = 5;
            panel.add(deleteButton, constraints);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = inpatientTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String originalName = (String) inpatientTableModel.getValueAt(selectedRow, 0); // Get the original name from the table
                        String originalRoomType = (String) inpatientTableModel.getValueAt(selectedRow, 1);
                        String originalDiagnosis = (String) inpatientTableModel.getValueAt(selectedRow, 2);
                        String originalNurse = (String) inpatientTableModel.getValueAt(selectedRow, 3);

                        // Assuming you have a method in InpatientRecord to update data
                        InpatientRecord existingInpatientRecord = new InpatientRecord(originalName, originalRoomType, originalDiagnosis, originalNurse);

                        // Call the method to update inpatient record data in the database
                        existingInpatientRecord.updateInpatientRecordInDatabase(patientNameField.getText(),
                                roomTypeField.getText(), diagnosisField.getText(), nurseField.getText());

                        // Update data in the table model
                        inpatientTableModel.setValueAt(patientNameField.getText(), selectedRow, 0);
                        inpatientTableModel.setValueAt(roomTypeField.getText(), selectedRow, 1);
                        inpatientTableModel.setValueAt(diagnosisField.getText(), selectedRow, 2);
                        inpatientTableModel.setValueAt(nurseField.getText(), selectedRow, 3);
                    }
                    dispose();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Assuming you have a method in InpatientRecord to delete data
                    InpatientRecord.deleteInpatientRecordFromDatabase(patientNameField.getText());

                    // Remove data from the table model
                    int selectedRow = inpatientTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        inpatientTableModel.removeRow(selectedRow);
                    }
                    dispose();
                }
            });
            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(parent);
        }
    }

        private void addPembayaranPanelComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Pencatatan Pembayaran Rawat Inap");
        titleLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 35));
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(new JLabel("Nama Pasien:"), constraints);

        JTextField patientNameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(patientNameField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(new JLabel("Biaya Perawatan (Rp):"), constraints);

        JTextField treatmentCostField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(treatmentCostField, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(new JLabel("Biaya Obat-obatan (Rp):"), constraints);

        JTextField medicineCostField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(medicineCostField, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 4;
        constraints.gridx = 0;
        JButton catatPembayaranButton = new JButton("Catat Pembayaran Rawat Inap");
        panel.add(catatPembayaranButton, constraints);

        catatPembayaranButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                String treatmentCost = treatmentCostField.getText();
                String medicineCost = medicineCostField.getText();

                // Handle pencatatan pembayaran rawat inap pasien
                double totalCost = Double.parseDouble(treatmentCost) + Double.parseDouble(medicineCost);
                JOptionPane.showMessageDialog(frame, "Pembayaran rawat inap pasien " + patientName + " sebesar " + totalCost + " berhasil dicatat.");

                // Tambahkan data pembayaran ke ArrayList paymentRecords
                PaymentRecord paymentRecord = new PaymentRecord(patientName, Double.parseDouble(treatmentCost), Double.parseDouble(medicineCost));
                paymentRecords.add(paymentRecord);

                // Simpan data di database
                paymentRecord.addPaymentRecordToDatabase(paymentRecord);

                // Update data in the payment table
                String[] rowData = {patientName, treatmentCost, medicineCost, String.valueOf(totalCost)};
                paymentTableModel.addRow(rowData);

                patientNameField.setText("");
                treatmentCostField.setText("");
                medicineCostField.setText("");
            }
        });
        // Code for displaying and editing payment record data
        String[] paymentColumnNames = { "Nama Pasien", "Biaya Perawatan (Rp)", "Biaya Obat-obatan (Rp)", "Total Biaya (Rp)" };
        paymentTableModel = new DefaultTableModel(paymentColumnNames, 0);

        // Get all payment records from the database
        PaymentRecord paymentManager = new PaymentRecord(); // Use the default constructor
        List<PaymentRecord> paymentRecords = paymentManager.getAllPaymentRecordsFromDatabase();

        // Add payment record data to the table model
        for (PaymentRecord paymentRecord : paymentRecords) {
            Object[] rowData = {paymentRecord.getPatientName(), paymentRecord.getTreatmentCost(), paymentRecord.getMedicineCost(), paymentRecord.getTotalCost()};
            paymentTableModel.addRow(rowData);
        }

        paymentTable = new JTable(paymentTableModel);
        JScrollPane paymentTableScrollPane = new JScrollPane(paymentTable);

        constraints.gridy = 7; // Adjust the index according to your layout
        panel.add(paymentTableScrollPane, constraints);

        JButton editPaymentButton = new JButton("Edit Pembayaran");
        constraints.gridy = 8; // Adjust the index according to your layout
        panel.add(editPaymentButton, constraints);

        editPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = paymentTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String patientName = (String) paymentTable.getValueAt(selectedRow, 0);
                    Double treatmentCost = (Double) paymentTable.getValueAt(selectedRow, 1);
                    Double medicineCost = (Double) paymentTable.getValueAt(selectedRow, 2);

                    EditPaymentDialog editDialog = new EditPaymentDialog(frame, patientName, treatmentCost, medicineCost);
                    editDialog.setVisible(true);
                }
            }
        });
    }

    class EditPaymentDialog extends JDialog {
        private JTextField patientNameField;
        private JTextField treatmentCostField;
        private JTextField medicineCostField;

        EditPaymentDialog(JFrame parent, String patientName, Double treatmentCost, Double medicineCost) {
            super(parent, "Edit Pembayaran", true);

            // Create dialog components
            patientNameField = new JTextField(patientName, 20);// Assuming you're setting a JTextField with a Double value
            treatmentCostField = new JTextField(String.valueOf(treatmentCost), 20);
            medicineCostField = new JTextField(String.valueOf (medicineCost),20);

            JButton saveButton = new JButton("Simpan");
            JButton deleteButton = new JButton("Hapus");

            // Add components to the dialog with GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(new JLabel("Nama Pasien:"), constraints);
            constraints.gridx = 1;
            panel.add(patientNameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel("Biaya Perawatan (Rp):"), constraints);
            constraints.gridx = 1;
            panel.add(treatmentCostField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(new JLabel("Biaya Obat-obatan (Rp):"), constraints);
            constraints.gridx = 1;
            panel.add(medicineCostField, constraints);

            constraints.gridwidth = 2;
            constraints.gridy = 3;
            panel.add(saveButton, constraints);

            // Add Delete button components
            constraints.gridy = 4;
            panel.add(deleteButton, constraints);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = paymentTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String originalPatientName = (String) paymentTableModel.getValueAt(selectedRow, 0);
                        String updatedTreatmentCost = treatmentCostField.getText();
                        String updatedMedicineCost = medicineCostField.getText();

                        // Create an object of PaymentRecord with existing data
                        PaymentRecord existingPaymentRecord = new PaymentRecord(originalPatientName, 0, 0);
                        existingPaymentRecord.setTreatmentCost(Double.parseDouble(updatedTreatmentCost));
                        existingPaymentRecord.setMedicineCost(Double.parseDouble(updatedMedicineCost));

                        // Call the method to update payment record data in the database
                        existingPaymentRecord.updatePaymentRecordInDatabase(Double.parseDouble(updatedTreatmentCost), Double.parseDouble(updatedMedicineCost));

                        // Update data in the payment table model
                        paymentTableModel.setValueAt(updatedTreatmentCost, selectedRow, 1);
                        paymentTableModel.setValueAt(updatedMedicineCost, selectedRow, 2);
                        double totalCost = Double.parseDouble(updatedTreatmentCost) + Double.parseDouble(updatedMedicineCost);
                        paymentTableModel.setValueAt(String.valueOf(totalCost), selectedRow, 3);
                    }
                    dispose();
                }
            });

            // Add action for the Delete button
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = paymentTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String patientName = (String) paymentTableModel.getValueAt(selectedRow, 0);

                        // Create an object of PaymentRecord
                        PaymentRecord existingPaymentRecord = new PaymentRecord(patientName, 0, 0);

                        // Call the method to delete payment record data
                        existingPaymentRecord.deletePaymentRecordFromDatabase(patientName);

                        // Remove data from the payment table model
                        paymentTableModel.removeRow(selectedRow);
                    }
                    dispose(); // Close the edit dialog
                }
            });

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(parent);
        }
    }

    private void addRekapMedisPanelComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Pencatatan Rekam Medis Pasien");
        titleLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 35));
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(new JLabel("Nama Pasien:"), constraints);

        JTextField patientNameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(patientNameField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(new JLabel("Pemeriksaan Medis:"), constraints);

        // Contoh perbaikan untuk medicalExaminationField
        JTextArea medicalExaminationField = new JTextArea(5, 20);
        medicalExaminationField.setLineWrap(true);
        medicalExaminationField.setWrapStyleWord(true);
        JScrollPane examinationScrollPane = new JScrollPane(medicalExaminationField);

        constraints.gridx = 1;
        panel.add(examinationScrollPane, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(new JLabel("Catatan Perkembangan:"), constraints);

        JTextArea progressNotesField = new JTextArea(5, 20);
        JScrollPane progressNotesScrollPane = new JScrollPane(progressNotesField);
        constraints.gridx = 1;
        panel.add(progressNotesScrollPane, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        panel.add(new JLabel("Resep Obat:"), constraints);

        JTextArea prescriptionField = new JTextArea(5, 20);
        JScrollPane prescriptionScrollPane = new JScrollPane(prescriptionField);
        constraints.gridx = 1;
        panel.add(prescriptionScrollPane, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 5;
        constraints.gridx = 0;
        JButton catatRekapMedisButton = new JButton("Catat Rekap Medis");
        panel.add(catatRekapMedisButton, constraints);

        catatRekapMedisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                String medicalExamination = medicalExaminationField.getText();
                String progressNotes = progressNotesField.getText();
                String prescription = prescriptionField.getText();

                MedicalSummary medicalSummary = new MedicalSummary(patientName, medicalExamination, progressNotes, prescription);
                medicalSummaries.add(medicalSummary);

                // Simpan data di database
                medicalSummary.addMedicalSummaryToDatabase(medicalSummary);

                // Update table model
                Object[] rowData = {medicalSummary.getPatientName(), medicalSummary.getMedicalExamination(), medicalSummary.getProgressNotes(), medicalSummary.getPrescription()};
                tableModel.addRow(rowData);
                tableModel.fireTableDataChanged();

                JOptionPane.showMessageDialog(frame, "Rekap medis pasien " + patientName + " berhasil dicatat.");
                patientNameField.setText("");
                medicalExaminationField.setText("");
                progressNotesField.setText("");
                prescriptionField.setText("");
            }
        });
// Display and edit medical record data
        String[] medicalRecordColumnNames = { "Nama Pasien", "Pemeriksaan Medis", "Catatan Perkembangan", "Resep Obat" };
        tableModel = new DefaultTableModel(medicalRecordColumnNames, 0);

// Get all medical records from the database
        MedicalSummary medicalSummaryManager = new MedicalSummary();
        List<MedicalSummary> medicalSummaries = medicalSummaryManager.getAllMedicalSummariesFromDatabase();

// Add medical record data to the table model
        for (MedicalSummary medicalSummary : medicalSummaries) {
            Object[] rowData = {medicalSummary.getPatientName(), medicalSummary.getMedicalExamination(), medicalSummary.getProgressNotes(), medicalSummary.getPrescription()};
            tableModel.addRow(rowData);
        }

// Buat JTable hanya sekali, dan gunakan selama aplikasi berjalan
        JTable medicalRecordTable = new JTable(tableModel);
        medicalRecordTable.setAutoCreateRowSorter(true);

// Set preferred size for better display
        medicalRecordTable.setPreferredScrollableViewportSize(new Dimension(500, 300));

        JScrollPane medicalRecordTableScrollPane = new JScrollPane(medicalRecordTable);

// Tambahkan medicalRecordTableScrollPane ke panel Anda
        constraints.gridy = 6;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH; // Fills the available space
        panel.add(medicalRecordTableScrollPane, constraints);


        JButton editMedicalRecordButton = new JButton("Edit Rekam Medis");
        constraints.gridy = 7;
        panel.add(editMedicalRecordButton, constraints);

        editMedicalRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = medicalRecordTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String patientName = (String) medicalRecordTable.getValueAt(selectedRow, 0);
                    String medicalExamination = (String) medicalRecordTable.getValueAt(selectedRow, 1);
                    String progressNotes = (String) medicalRecordTable.getValueAt(selectedRow, 2);
                    String prescription = (String) medicalRecordTable.getValueAt(selectedRow, 3);

                    // Teruskan referensi medicalRecordTable saat membuat EditMedicalSummaryDialog
                    EditMedicalSummaryDialog editMedicalSummaryDialog = new EditMedicalSummaryDialog(frame, patientName, medicalExamination, progressNotes, prescription, medicalRecordTable);
                    editMedicalSummaryDialog.setVisible(true);
                }
            }
        });

    }

    class EditMedicalSummaryDialog extends JDialog {
        private JTextField patientNameField;
        private JTextArea medicalExaminationField;
        private JTextArea progressNotesField;
        private JTextArea prescriptionField;

        EditMedicalSummaryDialog(JFrame parent, String patientName, String medicalExamination, String progressNotes, String prescription, JTable medicalRecordTable) {
            super(parent, "Edit Data Rekam Medis", true);

            // Initialize components
            patientNameField = new JTextField(patientName, 20);
            medicalExaminationField = new JTextArea(medicalExamination, 5, 20);
            progressNotesField = new JTextArea(progressNotes, 5, 20);
            prescriptionField = new JTextArea(prescription, 5, 20);

            JButton saveButton = new JButton("Simpan");
            JButton deleteButton = new JButton("Hapus");

            // Add components to the dialog using GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(new JLabel("Nama Pasien:"), constraints);
            constraints.gridx = 1;
            panel.add(patientNameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel("Pemeriksaan Medis:"), constraints);
            constraints.gridx = 1;
            panel.add(new JScrollPane(medicalExaminationField), constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(new JLabel("Catatan Perkembangan:"), constraints);
            constraints.gridx = 1;
            panel.add(new JScrollPane(progressNotesField), constraints);

            constraints.gridx = 0;
            constraints.gridy = 3;
            panel.add(new JLabel("Resep Obat:"), constraints);
            constraints.gridx = 1;
            panel.add(new JScrollPane(prescriptionField), constraints);

            constraints.gridwidth = 2;
            constraints.gridy = 4;
            constraints.gridx = 0;
            panel.add(saveButton, constraints);

            constraints.gridy = 5;
            panel.add(deleteButton, constraints);

            // Add action listeners for buttons
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Retrieve updated data from the fields
                    String updatedPatientName = patientNameField.getText();
                    String updatedMedicalExamination = medicalExaminationField.getText();
                    String updatedProgressNotes = progressNotesField.getText();
                    String updatedPrescription = prescriptionField.getText();

                    // Create an object with the original data
                    MedicalSummary originalMedicalSummary = new MedicalSummary(patientName, medicalExamination, progressNotes, prescription);

                    // Update the medical summary in the database
                    if (originalMedicalSummary.updateMedicalSummaryInDatabase(updatedPatientName, updatedMedicalExamination, updatedProgressNotes, updatedPrescription)) {
                        JOptionPane.showMessageDialog(EditMedicalSummaryDialog.this, "Data rekam medis berhasil diperbarui");

                        // Get the selected row index
                        int selectedRow = medicalRecordTable.getSelectedRow();

                        // Update the values in the table model
                        tableModel.setValueAt(updatedPatientName, selectedRow, 0);
                        tableModel.setValueAt(updatedMedicalExamination, selectedRow, 1);
                        tableModel.setValueAt(updatedProgressNotes, selectedRow, 2);
                        tableModel.setValueAt(updatedPrescription, selectedRow, 3);

                        tableModel.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(EditMedicalSummaryDialog.this, "Gagal memperbarui data rekam medis");
                    }
                    dispose(); // Close the dialog
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create an object with the original data
                    MedicalSummary medicalSummaryToDelete = new MedicalSummary(patientName, medicalExamination, progressNotes, prescription);

                    // Delete the medical summary from the database
                    if (medicalSummaryToDelete.deleteMedicalSummaryFromDatabase()) {
                        JOptionPane.showMessageDialog(EditMedicalSummaryDialog.this, "Data rekam medis berhasil dihapus");

                        // Get the selected row index
                        int selectedRow = medicalRecordTable.getSelectedRow();

                        // Remove the row from the table model
                        tableModel.removeRow(selectedRow);
                        tableModel.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(EditMedicalSummaryDialog.this, "Gagal menghapus data rekam medis");
                    }
                    dispose(); // Close the dialog
                }
            });

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(parent);
        }

    }
    private void generateDataMasterReport() {
        // Buat laporan Data Master dengan data dokter, perawat, obat-obatan, dan diagnosis
        StringBuilder report = new StringBuilder();
        report.append("<html><body><h1>Data Master</h1><ul>");
        for (String doctor : doctors) {
            report.append("<li>").append(doctor).append("</li>");
        }
        report.append("\nData Perawat:\n");
        for (String nurse : nurses) {
            report.append(nurse).append("\n");
        }
        report.append("\nData Obat-Obatan:\n");
        for (String medicine : medicines) {
            report.append(medicine).append("\n");
        }
        report.append("\nData Diagnosis:\n");
        for (String diagnosis : diagnoses) {
            report.append(diagnosis).append("\n");
        }
        report.append("</ul></body></html>");
        // Tampilkan laporan dalam jendela dialog atau simpan dalam berkas
        JOptionPane.showMessageDialog(frame, report.toString(), "Laporan Data Master", JOptionPane.PLAIN_MESSAGE);

    }
    private void generatePendaftaranReport() {
        Patient patient = new Patient();
        List<Patient> registeredPatients = patient.getAllPatientsFromDatabase();

        if (registeredPatients.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tidak ada data pendaftaran pasien.");
        } else {
            StringBuilder report = new StringBuilder("\tLaporan Pendaftaran Pasien\n\n");
            for (Patient registeredPatient : registeredPatients) {
                report.append("Nama          : ").append(registeredPatient.getName()).append("\n");
                report.append("Tanggal Lahir : ").append(registeredPatient.getDateOfBirth()).append("\n");
                report.append("Alamat        : ").append(registeredPatient.getAddress()).append("\n\n");
            }

            displayReport("Laporan Pendaftaran", report.toString());
        }
    }

    private void generateAdministrasiReport() {
        AdministrativeRecord adminRecord = new AdministrativeRecord();
        List<AdministrativeRecord> administrativeRecords = adminRecord.getAllAdministrativeRecordsFromDatabase();

        if (administrativeRecords.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tidak ada data administrasi pembayaran.");
        } else {
            StringBuilder report = new StringBuilder("\tLaporan Administrasi Pembayaran\n\n");
            for (AdministrativeRecord record : administrativeRecords) {
                report.append("Nama Pasien             : ").append(record.getPatientName()).append("\n");
                report.append("Pembayaran Administrasi : ").append(record.getPaymentAmount()).append("\n\n");
            }

            displayReport("Laporan Administrasi", report.toString());
        }
    }


    private void generateRawatInapReport() {
        InpatientRecord inpatientRecord = new InpatientRecord();
        List<InpatientRecord> inpatientRecords = inpatientRecord.getAllInpatientRecordsFromDatabase();

        if (inpatientRecords.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tidak ada data rawat inap pasien.");
        } else {
            StringBuilder report = new StringBuilder("\tLaporan Rawat Inap Pasien\n\n");
            for (InpatientRecord record : inpatientRecords) {
                report.append("Nama Pasien              : ").append(record.getPatientName()).append("\n");
                report.append("Jenis Kamar              : ").append(record.getRoomType()).append("\n");
                report.append("Diagnosis                : ").append(record.getDiagnosis()).append("\n");
                report.append("Perawat Bertanggung Jawab: ").append(record.getResponsibleNurse()).append("\n\n");
            }

            displayReport("Laporan Rawat Inap", report.toString());
        }
    }

    private void displayReport(String title, String content) {
        JTextArea textArea = new JTextArea(content);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, title, JOptionPane.PLAIN_MESSAGE);
    }
    private void generatePembayaranReport() {
        PaymentRecord paymentRecord = new PaymentRecord();
        List<PaymentRecord> paymentRecords = paymentRecord.getAllPaymentRecordsFromDatabase();

        if (paymentRecords.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tidak ada data pembayaran rawat inap pasien.");
        } else {
            StringBuilder report = new StringBuilder("\tLaporan Pembayaran Rawat Inap Pasien\n\n");
            for (PaymentRecord record : paymentRecords) {
                report.append("Nama Pasien       : ").append(record.getPatientName()).append("\n");
                report.append("Biaya Perawatan   : ").append(record.getTreatmentCost()).append("\n");
                report.append("Biaya Obat-obatan : ").append(record.getMedicineCost()).append("\n\n");
            }

            displayReport("Laporan Pembayaran", report.toString());
        }
    }


    private void generateRekapMedisReport() {
        MedicalSummary medicalSummaryManager = new MedicalSummary();
        List<MedicalSummary> medicalSummaries = medicalSummaryManager.getAllMedicalSummariesFromDatabase();

        if (medicalSummaries.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tidak ada data rekap medis pasien.");
        } else {
            StringBuilder report = new StringBuilder("\tLaporan Rekap Medis Pasien\n\n");
            for (MedicalSummary summary : medicalSummaries) {
                report.append("Nama Pasien           : ").append(summary.getPatientName()).append("\n");
                report.append("Pemeriksaan Medis     : ").append(summary.getMedicalExamination()).append("\n");
                report.append("Catatan Perkembangan  : ").append(summary.getProgressNotes()).append("\n");
                report.append("Resep Obat            : ").append(summary.getPrescription()).append("\n\n");
            }
            displayReport("Laporan Rekam Medis ", report.toString());
        }
    }


    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(() -> {
            // Mengatur tampilan yang lebih modern
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new HospitalManagementApp();
        });
        Connection connection = Koneksi.getKoneksi();
        if (connection != null) {
            // Koneksi sukses
            System.out.println("Koneksi ke database berhasil.");

            // Selanjutnya, Anda dapat menggunakan koneksi ini untuk berinteraksi dengan database MySQL.
            // Misalnya, Anda dapat membuat Statement dan ResultSet untuk mengambil data atau menambahkan data ke database.
        } else {
            // Koneksi gagal
            System.out.println("Koneksi ke database gagal.");
        }
    }
    }

