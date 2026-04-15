// teacher node class
class Teacher {
        String name, gender, dateOfBirth, maritalStatus, email, specialization, highestQualification;
        String contact, address, dateOfJoining;
        int teacherID;
        double salary;
        String skills, experience;
        String pass;
        Teacher next;
        Teacher prev;

        public Teacher() {
                this.teacherID = 0;
                this.name = "";
                this.gender = "";
                this.maritalStatus = "";
                this.email = "";
                this.specialization = "";
                this.highestQualification = "";
                this.contact = "";
                this.address = "";
                this.dateOfJoining = "";
                this.salary = 0.0;
                this.skills = "";
                this.experience = "";
                this.next = null;
                this.prev = null;
        }

}



