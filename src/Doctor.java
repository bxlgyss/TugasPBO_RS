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

public class Doctor {
        private String name;
        private String specialization;
        private int age;
        private String gender;
        private String contactNumber;

    private Connection connection;
    private DefaultTableModel doctorTableModel;
    private JFrame frame;
    private String doctorName;

    public Doctor(String name, String specialization, int age, String gender, String contactNumber) {
            this.name = name;
            this.specialization = specialization;
            this.age = age;
            this.gender = gender;
            this.contactNumber = contactNumber;
        }

        // Getter dan setter untuk Jenis Kelamin
        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        // Getter dan setter untuk Nomor Kontak
        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
