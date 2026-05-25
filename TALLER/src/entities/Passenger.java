package entities;

    public class Passenger {
        private String id;
        private String firstName;
        private String lastName;
        private int age;
        private String email;
        private String phone;
        private String passportNumber;
        private String nationality;

        public Passenger(String id, String firstName, String lastName, int age,
                         String email, String phone, String passportNumber, String nationality) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.email = email;
            this.phone = phone;
            this.passportNumber = passportNumber;
            this.nationality = nationality;
        }

        public String getId() { return id; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public int getAge() { return age; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getPassportNumber() { return passportNumber; }
        public String getNationality() { return nationality; }

        public void setId(String id) { this.id = id; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setAge(int age) { this.age = age; }
        public void setEmail(String email) { this.email = email; }
        public void setPhone(String phone) { this.phone = phone; }
        public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
        public void setNationality(String nationality) { this.nationality = nationality; }

        @Override
        public String toString() {
            return "\n Passenger{" +
                    "id='" + id + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", passportNumber='" + passportNumber + '\'' +
                    ", nationality='" + nationality + '\'' +
                    '}';
        }
    }


