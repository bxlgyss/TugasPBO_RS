public class Doctor {
        private String name;
        private String specialization;
        private int age;
        private String gender;
        private String contactNumber;

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


    }
