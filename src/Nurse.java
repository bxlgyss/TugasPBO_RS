public class Nurse {
    private String name;
    private String specialization;
    private int age;
    private String gender;
    private String contactNumber;

    public Nurse(String name, String specialization, int age, String gender, String contactNumber) {
        this.name = name;
        this.specialization = specialization;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
    }

    // Getter dan Setter untuk name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter dan Setter untuk specialization
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter dan Setter untuk age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter dan Setter untuk gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter dan Setter untuk contactNumber
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
