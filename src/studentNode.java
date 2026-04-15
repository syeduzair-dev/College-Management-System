public class studentNode {
    String id;
    String name;
    String FatherName;
    String Gender;
    String Address;
    String contact;
    int Class;
    String cnic;
    int matricPercentage;
    String matriculationStatus;
    String selectedDepartment;
    String admissionStatus;
    boolean isFeePaid;
    int uniqueBill;
    boolean intermediateCertificate;
    String pass;

    Course courses11 = new Course();
    Course courses12 = new Course();

    AttendanceRecord attendanceHead;


    studentNode next;
    studentNode prev;

    studentNode() {
        this.name = "";
        this.cnic = "";
        this.matricPercentage = 0;
        this.matriculationStatus = "";
        this.selectedDepartment = "";
        this.admissionStatus = null;
        isFeePaid = false;
        intermediateCertificate = false;
        this.next = null;
        this.prev = null;
        this.uniqueBill = 0;
        attendanceHead = null;
    }




    // ----------------------- Add attendance for the student ----------------------------------------------------

    public void markAttendance(String date, char status) {
        try {
            AttendanceRecord newRecord = new AttendanceRecord(date, status);
            if (attendanceHead == null) {
                attendanceHead = newRecord;
            } else {
                AttendanceRecord temp = attendanceHead;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newRecord;
            }
        } catch (Exception e) {
            System.out.println("Error while marking attendance: " + e.getMessage());
        }
    }

    // ------------------------- Display all attendance records for this student ---------------------------------

    public void displayAttendance() {
        try {
            if (attendanceHead == null) {
                System.out.println("No attendance records for student: " + name);
            } else {
                System.out.printf("\nAttendance Records for %s (Roll No: %s):\n", name, id);
                System.out.println("+---------------------+--------+");
                System.out.println("|       Date          | Status |");
                System.out.println("+---------------------+--------+");

                AttendanceRecord temp = attendanceHead;
                while (temp != null) {
                    System.out.printf("| %-19s |   %c    |\n", temp.date, temp.status);
                    temp = temp.next;
                }

                System.out.println("+---------------------+--------+");
            }
        } catch (Exception e) {
            System.out.println("Error while displaying attendance: " + e.getMessage());
        }
    }


    // ------------------------------ Get attendance status for a specific date ----------------------------------

    public Character getAttendanceByDate(String date) {
        try {
            AttendanceRecord temp = attendanceHead;
            while (temp != null) {
                if (temp.date.equals(date)) {
                    return temp.status;
                }
                temp = temp.next;
            }
        } catch (Exception e) {
            System.out.println("Error while retrieving attendance by date: " + e.getMessage());
        }
        return null;
    }

    //-------------------------------------------check attendance already mark-------------------------------------

    public boolean isAttendanceMarked(String date) {
        AttendanceRecord temp = attendanceHead;
        while (temp != null) {
            if (temp.date.equals(date)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}
