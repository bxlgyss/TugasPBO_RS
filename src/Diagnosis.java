public class Diagnosis {
    private String patientName;
    private String disease;
    private String notes;

    public Diagnosis(String patientName, String disease, String notes) {
        this.patientName = patientName;
        this.disease = disease;
        this.notes = notes;
    }

    // Getter dan Setter untuk setiap atribut (patientName, disease, notes)
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
