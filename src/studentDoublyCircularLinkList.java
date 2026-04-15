import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


class studentDoublyCircularLinkList {

    studentNode head;
    studentNode tail;
    int uniqueBill;
    int idEng;
    int idMed;
    int idCom;
    Scanner scanner = new Scanner(System.in);
    int Class;
    Scanner sc = new Scanner(System.in);

    studentDoublyCircularLinkList() {
        head = null;
        tail = null;
        idEng = 1;
        idMed = 1;
        idCom = 1;
        uniqueBill = 1001;
        Class = 11;

    }

    String Filename = "s.txt";

//-------------------------------------isEmpty----------------------------------------


    public boolean isEmpty() {
        return head == null;
    }

    //-------------------------------------genernate id ------------------------------ //correct




    public String generateStudentId(studentNode student) {

        switch (student.selectedDepartment.toLowerCase()) {
            case "pre-engineering":
                return "ENG" + student.Class + "-" + idEng++;
            case "pre-medical":
                return "MED" + student.Class + "-" + idMed++;
            case "computer science":
                return "CS" + student.Class + "-" + idCom++;
            default:
                System.out.println("Invalid department for student " + student.name + ". Unable to assign ID.");
                return null;
        }
    }

//---------------------------------------------------add student--------------------------------------------------

    public void addStudent(String name, String father, String gender, String address, String contact, String cnic, int matricPercentage,
                           String matriculationStatus, String selectedDepartment) {


        studentNode newStudent = new studentNode();
        newStudent.Class = Class;
        newStudent.name = name;
        newStudent.FatherName = father;
        newStudent.Gender = gender;
        newStudent.Address = address;
        newStudent.contact = contact;
        newStudent.cnic = cnic;
        newStudent.matricPercentage = matricPercentage;
        newStudent.matriculationStatus = matriculationStatus;
        newStudent.selectedDepartment = selectedDepartment;
        newStudent.id = generateStudentId(newStudent);
        newStudent.uniqueBill = uniqueBill++;
        checkEligibility(newStudent);
        String password = name+"$123";
        newStudent.pass=password;
        if (newStudent.admissionStatus.equalsIgnoreCase("Eligible")) {

            if (isEmpty()) {
                head = newStudent;
                tail = newStudent;
                head.prev = tail;
                head.next = tail;
            } else {
                tail.next = newStudent;
                newStudent.prev = tail;
                newStudent.next = head;
                head.prev = newStudent;
                tail = newStudent;
            }
            System.out.println("Admission successful. Welcome, " + newStudent.name + "!");
            System.out.println("Student Id: " + newStudent.id +"password " + password );}
        else {System.out.println("Sorry, " + newStudent.name + ", you are " + newStudent.admissionStatus + " for " + selectedDepartment + ".");}}

    //---------------------------------checkEligibility--------------------------------------//correct


    private void checkEligibility(studentNode student) {
        if (student.matriculationStatus.equalsIgnoreCase("Failed")) {
            student.admissionStatus = "Not Eligible";
            return;
        }

        if (student.matriculationStatus.equalsIgnoreCase("Science")) {

            if (student.selectedDepartment.equalsIgnoreCase("Pre-Engineering")) {
                student.admissionStatus = (student.matricPercentage >= 80) ? "Eligible" : "Not Eligible";
            } else if (student.selectedDepartment.equalsIgnoreCase("Computer Science")) {
                student.admissionStatus = (student.matricPercentage >= 75) ? "Eligible" : "Not Eligible";
            }
        }
        else if (student.matriculationStatus.equalsIgnoreCase("Biology")) {
            if (student.selectedDepartment.equalsIgnoreCase("Pre-Medical")) {
                student.admissionStatus = (student.matricPercentage >= 70) ? "Eligible" : "Not Eligible";
            }
        }
    }


    //-----------------------------------------removeStudent-----------------------------------// correct


    public void removeStudent(String Id) {
        if (isEmpty()) {
            System.out.println("The system contains no students to remove.");
            return;}
        studentNode current = head;
        do {
            if (current.id.equalsIgnoreCase(Id)) {

                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else if (current == head) {
                    head = head.next;
                    head.prev = tail;
                    tail.next = head;
                    saveToFile();
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = head;
                    head.prev = tail;
                    saveToFile();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    saveToFile();}
                System.out.println("Student with ID " + Id + " removed  ");
                return;}
            current = current.next;
        } while (current!=head);
        System.out.println("student  with ID " + Id + " not found  ");
    }



//----------------------------------------------------update student information -----------------------------------------------------

    //====================string is empty or null check===========================
public boolean isEmpty(String str) {
    return str == null || str.trim().isEmpty();
}


    public void updateStudent(String studentId) {
        if (isEmpty()) {
            System.out.println("No student is found.");
            return;
        }

        studentNode current = head;
        while (true) {

            if (current.id.equalsIgnoreCase(studentId))
                break;
            current = current.next;

            if(current==head){
                System.out.println("Invalid ID");
                return;
            }

        }



        System.out.println("Current Details:");
        System.out.println("Name: " + current.name);
        System.out.println("Father Name: " + current.FatherName);
        System.out.println("Contact: " + current.contact);
        System.out.println("CNIC: " + current.cnic);
        System.out.println("Address: " + current.Address + "\n");

        while (true) {
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Father Name");
            System.out.println("3. Contact");
            System.out.println("4. CNIC");
            System.out.println("5. Address");
            System.out.println("0. Save");
            System.out.print("Select an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");

                continue;
            }

            switch (choice) {

                case 1:
                    scanner.nextLine();
                    while (true) {
                        try {
                            System.out.print("Enter new name: ");
                            String newName = scanner.nextLine();
                            if (!isEmpty(newName)) {
                                current.name = newName;
                                System.out.println("Name updated successfully.");
                                break;
                            } else {
                                System.out.println("Name cannot be empty. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    scanner.nextLine();
                    while (true) {
                        try {
                            System.out.print("Enter new Father Name: ");
                            String newFatherName = scanner.nextLine().trim();

                            if (!isEmpty(newFatherName)) {
                                current.FatherName = newFatherName;
                                System.out.println("Father Name updated successfully.");
                                break;
                            } else {
                                System.out.println("Father Name cannot be empty. Please try again.");

                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    scanner.nextLine();
                    while (true) {
                        try {
                            System.out.print("Enter new contact: ");
                            String newContact = scanner.nextLine().trim();
                            if (!isEmpty(newContact)) {
                                current.contact = newContact;
                                System.out.println("Contact updated successfully.");
                                break;
                            } else {
                                System.out.println("Contact cannot be empty. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    scanner.nextLine();
                    while (true) {
                        try {
                            System.out.print("Enter new CNIC: ");
                            String newCnic = scanner.nextLine().trim();
                            if (newCnic.matches("\\d{5}-\\d{7}-\\d")) {
                                current.cnic = newCnic;
                                System.out.println("CNIC updated successfully.");
                                break;
                            } else {
                                System.out.println("Invalid CNIC format. Please enter in '12345-6789012-3' format.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    scanner.nextLine();
                    while (true) {
                        try {
                            System.out.print("Enter new address: ");
                            String newAddress = scanner.nextLine().trim();
                            if (!isEmpty(newAddress)) {
                                current.Address = newAddress;
                                System.out.println("Address updated successfully.");
                                break;
                            } else {
                                System.out.println("Address cannot be empty. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                    }
                    break;


                case 0:
                    System.out.println("Student updated successfully . . . ");
                    saveToFile();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    //-------------------------------------------display department wise ---------------------------------- //correct


    public void display(String dep, int Class) {
        if (isEmpty()) {
            System.out.println("The list is empty. No students to display.");
            return;
        }

        studentNode current = head;
        boolean found = false;

        System.out.println("\n===== Student List for Department: " + dep + " and Class: " + Class + " =====");
        System.out.println("+------------------+--------------------+---------------------+------------------+------------------+");
        System.out.println("|       ID         |        NAME        |       CONTACT       |     ADDRESS      |    FEE STATUS    |");
        System.out.println("+------------------+--------------------+---------------------+------------------+------------------+");

        do {

            if (current.selectedDepartment != null &&
                    current.selectedDepartment.equalsIgnoreCase(dep) &&
                    current.Class == Class) {

                found = true;


                String feeStatus = current.isFeePaid ? "Paid" : "Not paid ";


                System.out.printf("|%-18s|%-20s|%-21s|%-18s|%-18s|\n",
                        current.id, current.name, current.contact, current.Address, feeStatus);
                System.out.println("+------------------+--------------------+---------------------+------------------+------------------+");
            }


            current = current.next;
        } while (current != head);

        saveToFile();

        if (!found) {
            System.out.println("|       NONE       |        NONE        |         NONE        |       NONE       |       NONE       |");
            System.out.println("+------------------+--------------------+---------------------+------------------+------------------+");
            System.out.println("No students found in the " + dep + " department and Class " + Class + ".");
        }
    }


    // ---------------------------fee voucher department -------------------------------------------
    public void displayFeeVoucherByReferenceNumber(String id) {
        if (isEmpty()) {
            System.out.println("No students admitted to the College.");
            return;
        }
        studentNode current = head;
        boolean found = false;

        do {
            if (current.id.equalsIgnoreCase(id)) {

                double feeAmount = 50000.0;
                String paymentStatus = current.isFeePaid ? "Paid" : "Not Paid";

                System.out.println("====================================================");
                System.out.println("|                   FEE VOUCHER                    |");
                System.out.println("====================================================");
                System.out.printf("| %-15s : %-40s |\n", "Bill ID", current.uniqueBill);
                System.out.printf("| %-15s : %-40s |\n", "Student Name", current.name);
                System.out.printf("| %-15s : %-40s |\n", "Department", current.selectedDepartment);
                System.out.printf("| %-15s : %-40.2f PKR |\n", "Fee Amount", feeAmount);
                System.out.printf("| %-15s : %-40s |\n", "Payment Status", paymentStatus);
                System.out.println("====================================================");
                System.out.println("|                PLEASE PAY YOUR FEE               |");
                System.out.println("====================================================");
                found = true;
                break;}
            current = current.next;
            }while (current!=head);
        if (!found) {
            System.out.println("No fee voucher found for the given reference number.");
        }
    }


    //-------------------------------pay fee------------------------- // correct


    public void payFeeWithConfirmation(int billID) {
        if (isEmpty()) {
            System.out.println("No students admitted to the college.");
            return;}
        studentNode current = head;
        boolean billFound = false;
        do {
            if (current.uniqueBill == billID) {
                billFound = true;
                System.out.println("Student Name: " + current.name);
                if (current.isFeePaid) {
                    System.out.println("Fee has already been paid for Bill ID: " + billID);
                } else {
                    System.out.print("Do you want to pay the fee? (Y/N): ");
                    char choose = scanner.next().charAt(0);
                    if (choose == 'Y' || choose == 'y') {
                        current.isFeePaid = true;
                        System.out.println("Fee successfully paid for Bill ID: " + billID);
                        saveToFile();
                    } else if (choose == 'N' || choose == 'n') {
                        System.out.println("Fee payment canceled.");
                    } else {
                        System.out.println("Invalid input. Fee payment not processed.");
                    }}
                return;
            }

            current = current.next;

        }while (current!=head);

        // If the Bill ID was not found
        if (!billFound) {
            System.out.println("Invalid Bill ID: " + billID);
        }
    }

    //-------------------------------------markAttendanceForAll student department wise -----------------------//correct


    public void markAttendanceForAll(String dep, int C) {
        if (isEmpty()) {
            System.out.println("No students to mark attendance for.");
            return;
        }

        String date = LocalDate.now().toString();
        System.out.println("Marking attendance for today (" + date + ") in department: " + dep);
        studentNode current = head;
        boolean departmentFound = false;
        do {
            if (current.selectedDepartment.equalsIgnoreCase(dep) && current.Class == C) {
                departmentFound = true;
                if (current.isAttendanceMarked(date)) {
                    System.out.println("Attendance is already marked for " + current.name + " on " + date);
                    return;
                }
                System.out.print(current.name + " present? (P/A): ");
                char input = scanner.next().charAt(0);

                if (input == 'P' || input == 'p') {
                    current.markAttendance(date, 'P');
                } else if (input == 'A' || input == 'a') {
                    current.markAttendance(date, 'A');
                } else {
                    System.out.println("Invalid input. Marking as Absent.");
                    current.markAttendance(date, 'A');
                }}
            current = current.next;
        }while (current!=head);
        if (!departmentFound) {
            System.out.println("No students found in department: " + dep);
        } }


    //----------------------------display attendance by date--------------------------------------- //correct

    public void displayAttendanceByDate(String dep, String date, int YEAR) {
        if (isEmpty()) {
            System.out.println("No students to display attendance for.");
            return;}

        studentNode current = head;
        boolean departmentFound = false;
        System.out.println("\nAttendance for date: " + date + " in department: " + dep);
        System.out.println("+------------------+--------------------+------------------+");
        System.out.println("|     Roll No      |       Name         |     Status       |");
        System.out.println("+------------------+--------------------+------------------+");
        do {
            if (current.selectedDepartment.equalsIgnoreCase(dep) && current.Class == YEAR) {
                departmentFound = true;
                Character status = current.getAttendanceByDate(date);
                System.out.printf("|%-18s|%-20s|%-18s|\n",
                        current.id, current.name, (status != null ? status : "No Record"));
                System.out.println("+------------------+--------------------+------------------+");
            }
            current = current.next;
        } while (current != head);
        if (!departmentFound) {
            System.out.println("|       NONE       |        NONE        |       NONE       |");
            System.out.println("+------------------+--------------------+------------------+");
            System.out.println("No students found in department: " + dep);}}


    //--------------------------- Display attendance for a specific student-----------------------------------

    public void displayStudentAttendance(String id) {
        if (isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        studentNode current = head;
        boolean studentFound = false;

        do {
            if (current.id.equalsIgnoreCase(id)) {
                current.displayAttendance();
                studentFound = true;
                return;
            }
            current = current.next;


            }while (current!=head);


        if (!studentFound) {
            System.out.println("No student found with Roll No: " + id);
        }
    }


    // --------------------------add course first year and seconder ----------------------------

    public void addCourseForClass(int Class, String dep) {
        try {
            if (isEmpty()) {
                System.out.println("The system contains no students to add the course.");
                return;
            }
            boolean courseAdd = false;
            studentNode current = head;
            System.out.print("Enter number of courses to add: ");
            int numCourses = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < numCourses; i++) {
                System.out.print("Enter Course Name: ");
                String courseName = sc.nextLine();
                do {
                    if (current.Class == Class && current.selectedDepartment.equalsIgnoreCase(dep) && current.isFeePaid) {
                        if (Class == 11) {
                            current.courses11.addCourse(courseName);}
                        else if (Class == 12) {
                            current.courses12.addCourse(courseName);}
                        courseAdd = true;
                    }
                    current = current.next;
                } while (current != head);
            }
            if (courseAdd) {
                System.out.println("Courses have been successfully added for all matching students.");
            } else {
                System.out.println("No students found in class " + Class + " with the specified department.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());}}


//----------------------------------------removeCourse first year  and seconder year---------------------------------------------


    public void removeCourseForClass(String dep, int Class, String course) {
        if (isEmpty()) {
            System.out.println("The system contains no students to remove the course.");
            return;} boolean courseRemoved = false;
            studentNode current = head;
            do {
            if (current.Class == Class && current.selectedDepartment.equalsIgnoreCase(dep)) {
                if (Class == 11) {
                    current.courses11.removeCourse(course);
                } else if (Class == 12) {
                    current.courses12.removeCourse(course);
                }
                courseRemoved = true;
            }
            current = current.next;
            }while (current!=head);

        if (courseRemoved) {
            System.out.println("Course \"" + course + "\" has been removed for all students in class " + Class + ".");
        } else {
            System.out.println("No students found in class " + Class + ".");
        }
    }

    //----------------------------------display course-------------------------------
    public void displayCoursesByStudentId(String studentId) {
        if (isEmpty()) {
            System.out.println("No students to display courses for.");
            return;
        } studentNode current = head;
        boolean studentFound = false;
        do {
            if (current.id.equalsIgnoreCase(studentId)) {
                studentFound = true;
                System.out.println("Courses for Student ID: " + studentId);
                System.out.println("Name: " + current.name);
                System.out.println("Class: " + current.Class);
                System.out.println("Department: " + current.selectedDepartment);
                System.out.println("Courses:");
                if (current.Class == 11 && current.courses11 != null) {
                    current.courses11.displayCourses();
                } else if (current.Class == 12 && current.courses12 != null) {
                    current.courses12.displayCourses();
                } else {
                    System.out.println("No courses found.");
                }
                break; }
            current = current.next;
        } while (current != head);
        if (!studentFound) {
            System.out.println("Student with ID " + studentId + " not found.");
        }}


    //---------------------------------addMarksForAllCoursesInClass----------------------------------------
    public void addMarksForAllCoursesInClass(String dep, int Class) {
        if (isEmpty()) {
            System.out.println("The system contains no students.");
            return;
        }
        boolean studentFound = false;
        studentNode current = head;
        while (true) {

            if (current.selectedDepartment.equalsIgnoreCase(dep) && current.Class == Class) {
                studentFound = true;
                System.out.println("Enter marks for student: " + current.name);
                Course studentCourses = (current.Class == 11) ? current.courses11 : current.courses12;
                if (studentCourses.size() == 0) {
                    System.out.println("No courses available for this student.");
                } else {
                    for (int i = 0; i < studentCourses.size(); i++) {
                        String course = studentCourses.courses[i];
                        System.out.print("Enter marks for " + course + ": ");
                        int marksToAdd = scanner.nextInt();
                        studentCourses.setMarks(course, marksToAdd);  }
                    System.out.println("All marks have been added for student " + current.name + ".");
                }
            }
            current = current.next;
            if (current == head) {
                break;}}

        if (!studentFound) {
            System.out.println("No students found in class " + Class + " for department " + dep + ".");
        }}

//--------------------------mark sheet-----------------------------------

    public void displayMarkSheetById(String id, int studentClass) {
        if (isEmpty()) {
            System.out.println("No students are available in the system.");
            return;
        }

        studentNode current = head;
        boolean studentFound = false;


        do {
            if (current.id.equalsIgnoreCase(id) && current.Class == studentClass) {
                studentFound = true;
                System.out.println("\n===============================================");
                System.out.println("               Mark Sheet for Student ID: " + id);
                System.out.println("===============================================");
                System.out.println("Name                 : " + current.name);
                System.out.println("Father's Name        : " + current.FatherName);
                System.out.println("Gender               : " + current.Gender);
                System.out.println("CNIC                 : " + current.cnic);
                System.out.println("Address              : " + current.Address);
                System.out.println("Contact Number       : " + current.contact);
                System.out.println("Department           : " + current.selectedDepartment);
                System.out.println("Class                : " + current.Class);


                Course studentCourses = (current.Class == 11) ? current.courses11 : current.courses12;
                if (studentCourses.size() > 0) {
                    System.out.println("Courses and Marks:");
                    System.out.println("+---------------------+-----------------+----------------+");
                    System.out.printf("| %-19s | %-15s | %-14s |\n", "Course Name", "Max Marks", "Obtained Marks");
                    System.out.println("+---------------------+-----------------+----------------+");

                    int totalMaxMarks = 0;
                    int totalObtainedMarks = 0;
                    for (int i = 0; i < studentCourses.size(); i++) {
                        String courseName = studentCourses.courses[i];
                        int courseMarks = 100;
                        int obtainedMarks = studentCourses.marks[i];
                        totalMaxMarks = studentCourses.totalMaxMarks();
                        totalObtainedMarks = studentCourses.totalMarks();
                        System.out.printf("| %-19s | %-15d | %-14d |\n", courseName, courseMarks, obtainedMarks);
                    }
                    System.out.println("+---------------------+-----------------+----------------+");
                    System.out.printf("| %-19s | %-15d | %-14d |\n", "Total Marks", totalMaxMarks, totalObtainedMarks);
                    System.out.println("+---------------------+-----------------+----------------+");

                    double percentage = (double) totalObtainedMarks / totalMaxMarks * 100;
                    String grade = studentCourses.calculateGrade();
                    System.out.println("-----------------------------------------------");
                    System.out.printf("Percentage           : %.2f%%\n", percentage);
                    System.out.printf("Grade                : %s\n", grade);
                    System.out.println("-----------------------------------------------");
                } else {
                    System.out.println("No courses available for this student.");
                }


                break;
            }

            current = current.next;
        } while (current != head);

        if (!studentFound) {
            System.out.println("Student not found in the system.");
        }
    }



// ------------------------------------------------------promote Seconder---------------


    public void promoteToSecondYearWithNewIds() {
        if (isEmpty()) {
            System.out.println("The system contains no students to promote."); return;}
        studentNode current = head;
        boolean studentPromoted = false;
        do {if (current.Class == 11 && current.courses11.seconderEligible()&&current.courses11.size>0) {
                current.Class = 12;
                current.id = generateStudentId(current);
                current.uniqueBill = uniqueBill++;
                current.isFeePaid = false;
                studentPromoted = true;
            } else if (current.Class == 11 && !current.courses11.seconderEligible()) {
                current.isFeePaid = false;
                current.id = generateStudentId(current);
                current.uniqueBill = uniqueBill++;
                current.Class = 11;
                current.courses11.clearCourses();
            } else if (current.Class == 12 && current.courses12.seconderEligible()&&current.courses12.size>0) {
                current.intermediateCertificate = true;
                break;
            } else if (current.Class == 12 && !current.courses12.seconderEligible()) {
                current.Class = 12;
                current.isFeePaid = false;
                current.id = generateStudentId(current);
                current.uniqueBill = uniqueBill++;
                current.courses12.clearCourses();
            }
            current = current.next;
        } while (current != head);

        if (!studentPromoted) {
            System.out.println("No students eligible for promotion to Class 12.");
        }}

    public void createIntermediateCertificate(String id) {
        studentNode current = head;
        do {

            if (current.id.equalsIgnoreCase(id) && current.intermediateCertificate) {
                int totalMarks11And12 = current.courses11.totalMarks() + current.courses12.totalMarks();
                int totalMaxMarks = current.courses11.totalMaxMarks() + current.courses12.totalMaxMarks();
                float percentage = (totalMarks11And12 * 100.0f) / totalMaxMarks;

                String grade;
                if (percentage >= 90) {
                    grade = "A+";
                } else if (percentage >= 80) {
                    grade = "A";
                } else if (percentage >= 70) {
                    grade = "B";
                } else if (percentage >= 60) {
                    grade = "C";
                } else if (percentage >= 50) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                // Print the certificate in table format
                System.out.println("-----------------------------------------------------");
                System.out.printf("| %-20s | %-30s |\n", "Field", "Details");
                System.out.println("-----------------------------------------------------");
                System.out.printf("| %-20s | %-30s |\n", "Name", current.name);
                System.out.printf("| %-20s | %-30s |\n", "Father's Name", current.FatherName);
                System.out.printf("| %-20s | %-30s |\n", "CNIC", current.cnic);
                System.out.printf("| %-20s | %-30s |\n", "Address", current.Address);
                System.out.printf("| %-20s | %-30s |\n", "Total Marks", totalMarks11And12 + "/" + totalMaxMarks);
                System.out.printf("| %-20s | %-30.2f%% |\n", "Percentage", percentage);
                System.out.printf("| %-20s | %-30s |\n", "Grade", grade);
                System.out.println("-----------------------------------------------------");
                removeStudent(current.id);
                return;
            }

            current = current.next;
        } while (current != head);


        System.out.println("Student with ID " + id + " not found or no intermediate certificate issued.");
    }


    //--------------------------------------------heap sort----------------------------------------


    public void heapSortLinkedList() {

        int n = getCount();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swapNodes(0, i);
            heapify(i, 0);
        }
    }
    public void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;


        if (left < n && compareNodes(getNodeAt(left), getNodeAt(largest)) > 0) {
            largest = left;
        }

        if (right < n && compareNodes(getNodeAt(right), getNodeAt(largest)) > 0) {
            largest = right;
        }

        if (largest != i) {
            swapNodes(i, largest);
            heapify(n, largest);
        }
    }
    public void swapNodes(int i, int j) {
        if (i == j) return;

        studentNode nodeI = getNodeAt(i);
        studentNode nodeJ = getNodeAt(j);

        String tempName = nodeI.name;
        String tempFather = nodeI.FatherName;
        String tempGender = nodeI.Gender;
        String tempAddress = nodeI.Address;
        String tempContact = nodeI.contact;
        String tempCnic = nodeI.cnic;
        int tempPercentage = nodeI.matricPercentage;
        String tempMatricStatus = nodeI.matriculationStatus;
        String tempDepartment = nodeI.selectedDepartment;
        String tempId = nodeI.id;
        int tempBill = nodeI.uniqueBill;

        nodeI.name = nodeJ.name;
        nodeI.FatherName = nodeJ.FatherName;
        nodeI.Gender = nodeJ.Gender;
        nodeI.Address = nodeJ.Address;
        nodeI.contact = nodeJ.contact;
        nodeI.cnic = nodeJ.cnic;
        nodeI.matricPercentage = nodeJ.matricPercentage;
        nodeI.matriculationStatus = nodeJ.matriculationStatus;
        nodeI.selectedDepartment = nodeJ.selectedDepartment;
        nodeI.id = nodeJ.id;
        nodeI.uniqueBill = nodeJ.uniqueBill;


        nodeJ.name = tempName;
        nodeJ.FatherName = tempFather;
        nodeJ.Gender = tempGender;
        nodeJ.Address = tempAddress;
        nodeJ.contact = tempContact;
        nodeJ.cnic = tempCnic;
        nodeJ.matricPercentage = tempPercentage;
        nodeJ.matriculationStatus = tempMatricStatus;
        nodeJ.selectedDepartment = tempDepartment;
        nodeJ.id = tempId;
        nodeJ.uniqueBill = tempBill;
    }


    public int compareNodes(studentNode a, studentNode b) {
        String nameA = a.name.toLowerCase();
        String nameB = b.name.toLowerCase();

        int lengthA = nameA.length();
        int lengthB = nameB.length();


        for (int i = 0; i < lengthA && i < lengthB; i++) {
            char charA = nameA.charAt(i);
            char charB = nameB.charAt(i);


            if (charA != charB) {
                return charA - charB;
            }
        }


        return lengthA - lengthB;
    }


   public studentNode getNodeAt(int index) {
        studentNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }


    public int getCount() {
        if (isEmpty())
        {
            return 0;
        }

        int count = 1;
        studentNode current = head;
        while (current.next != head) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The list is empty. No students to display.");
            return;
        }

        studentNode current = head;
        boolean found = false;

        System.out.println("\n================== Student List ==================");
        System.out.println("+------------------+--------------------+---------------------+------------------+--------------------+");
        System.out.println("|       ID         |        NAME        |    FATHER'S NAME    |    DEPARTMENT    |       CLASS        |");
        System.out.println("+------------------+--------------------+---------------------+------------------+--------------------+");

        do {
            found = true;


            System.out.printf("|%-18s|%-20s|%-21s|%-18s|%-20s|\n",
                    current.id, current.name, current.FatherName, current.selectedDepartment, current.Class);
            System.out.println("+------------------+--------------------+---------------------+------------------+--------------------+");


            current = current.next;
        } while (current != head);


        if (!found) {
            System.out.println("|       NONE       |        NONE        |         NONE        |       NONE       |        NONE        |");
            System.out.println("+------------------+--------------------+---------------------+------------------+--------------------+");
            System.out.println("No students found in the list.");
        }
    }


    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            studentNode temp = head;

            writer.write("Voucher Number: " + uniqueBill + "\n\n");
            writer.write("Eng: " + idEng + "\n\n");
            writer.write("Med: " + idMed + "\n\n");
            writer.write("CS: " + idCom + "\n\n");
            writer.write("\t\t\t* * * * STUDENT DETAILS * * * *\n\n");

            do {
                writer.write("Student ID: " + temp.id + "\n");
                writer.write("Name: " + temp.name + "\n");
                writer.write("Password: "+temp.pass +"\n");
                writer.write("Father's Name: " + temp.FatherName + "\n");
                writer.write("Gender: " + temp.Gender + "\n");
                writer.write("Address: " + temp.Address + "\n");
                writer.write("Contact: " + temp.contact + "\n");
                writer.write("Class: " + temp.Class + "\n");
                writer.write("CNIC: " + temp.cnic + "\n");
                writer.write("Matric Percentage: " + temp.matricPercentage + "\n");
                writer.write("Matriculation Status: " + temp.matriculationStatus + "\n");
                writer.write("Selected Department: " + temp.selectedDepartment + "\n");
                writer.write("Admission Status: " + temp.admissionStatus + "\n");
                writer.write("Fee Status: " + (temp.isFeePaid ? "Paid" : "Not paid ") + "\n");
                writer.write("Unique Bill: " + temp.uniqueBill + "\n");
                writer.write("Intermediate Certificate: " + temp.intermediateCertificate + "\n");

                //--------------------- Attendance Details----------------------------------------
                writer.write("Attendance:\n");
                AttendanceRecord attendanceTemp = temp.attendanceHead;
                while (attendanceTemp != null) {
                    writer.write("\tDate: " + attendanceTemp.date + " | Status: " + attendanceTemp.status + "\n");
                    attendanceTemp = attendanceTemp.next;}
                // -----------------------Course 11 Details--------------------------------------
                if(temp.Class==11) {
                    writer.write("Course 11:\n");
                    if (temp.courses11.size() > 0) {
                        for (int i = 0; i < temp.courses11.size(); i++) {
                            writer.write("\t" + temp.courses11.courses[i] + " | Marks: " + temp.courses11.marks[i] + "\n");}
                    } else {
                        writer.write("\tNo courses available.\n");}}
                else {
                    // ---------------------------- Course 12 Details-------------------------------------------
                    writer.write("Course 12:\n");
                    if (temp.courses12.size() > 0) {
                        for (int i = 0; i < temp.courses12.size(); i++) {
                            writer.write("\t" + temp.courses12.courses[i] + " | Marks: " + temp.courses12.marks[i] + "\n");}
                    } else {
                        writer.write("\tNo courses available.\n");}}
                writer.write("\n\n");
                temp = temp.next;
            } while (temp != head);
        } catch (IOException e) {
            System.out.println("Error saving student details to file.");}}

//-----------------------------------------read from file----------------------------

    public void readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            studentNode current = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Voucher Number:")) {
                    this.uniqueBill = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Eng:")) {
                    this.idEng = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Med:")) {
                    this.idMed = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("CS:")) {
                    this.idCom = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Student ID:")) {
                    current = new studentNode();
                    current.id = line.split(":")[1].trim();
                } else if (line.startsWith("Name:")) {
                    current.name = line.split(":")[1].trim();
                } else if (line.startsWith("Password:")) {
                    current.pass = line.split(":")[1].trim();
                }
                else if (line.startsWith("Father's Name:")) {
                    current.FatherName = line.split(":")[1].trim();
                } else if (line.startsWith("Gender:")) {
                    current.Gender = line.split(":")[1].trim();
                } else if (line.startsWith("Address:")) {
                    current.Address = line.split(":")[1].trim();
                } else if (line.startsWith("Contact:")) {
                    current.contact = line.split(":")[1].trim();
                } else if (line.startsWith("Class:")) {
                    current.Class = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("CNIC:")) {
                    current.cnic = line.split(":")[1].trim();
                } else if (line.startsWith("Matric Percentage:")) {
                    current.matricPercentage = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Matriculation Status:")) {
                    current.matriculationStatus = line.split(":")[1].trim();
                } else if (line.startsWith("Selected Department:")) {
                    current.selectedDepartment = line.split(":")[1].trim();
                } else if (line.startsWith("Admission Status:")) {
                    current.admissionStatus = line.split(":")[1].trim();
                } else if (line.startsWith("Fee Status:")) {
                    current.isFeePaid = line.split(":")[1].trim().equalsIgnoreCase("Paid");
                } else if (line.startsWith("Unique Bill:")) {
                    current.uniqueBill = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Intermediate Certificate:")) {
                    current.intermediateCertificate = Boolean.parseBoolean(line.split(":")[1].trim());
                } else if (line.startsWith("Attendance:")) {
                    // Parse attendance records
                    while ((line = reader.readLine()) != null && line.startsWith("\t")) {
                        String[] attendanceDetails = line.trim().split(" \\| ");
                        String date = attendanceDetails[0].split(":")[1].trim();
                        char status = attendanceDetails[1].split(":")[1].trim().charAt(0);
                        current.markAttendance(date, status);
                    }
                }
                if (line.startsWith("Course 11:")) {
                    while ((line = reader.readLine()) != null && line.startsWith("\t")) {
                        if (line.trim().equalsIgnoreCase("No courses available.")) {
                            break;
                        }
                        String[] courseDetails = line.trim().split(" \\| ");
                        if (courseDetails.length == 2) {
                            String courseName = courseDetails[0].trim();
                            int mark = Integer.parseInt(courseDetails[1].split(":")[1].trim());
                            current.courses11.addCourse(courseName);
                            current.courses11.setMarks(courseName, mark);
                        }
                    }
                }


                if (line.startsWith("Course 12:")) {
                    while ((line = reader.readLine()) != null && line.startsWith("\t")) {
                        if (line.trim().equalsIgnoreCase("No courses available.")) {
                            break;
                        }
                        String[] courseDetails = line.trim().split(" \\| ");
                        if (courseDetails.length == 2) {
                            String courseName = courseDetails[0].trim();
                            int mark = Integer.parseInt(courseDetails[1].split(":")[1].trim());
                            current.courses12.addCourse(courseName);
                            current.courses12.setMarks(courseName, mark);
                        }
                    }
                }


                // ----------------------Add student --------------------------

                if (line.isEmpty() && current != null) {
                    addStudent(current);
                    current = null;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student details from file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    //------------------------add student file save method use-------------------------------

    public void addStudent(studentNode current) {
        if (current == null) {
            System.out.println("Error: Cannot add a null studentNode.");
            return;
        }

        if (head == null) {
            head = current;
            tail = current;
            head.next = head;
            head.prev = head;
        } else {
            tail.next = current;
            current.prev = tail;
            current.next = head;
            head.prev = current;
            tail = current;
        }


    }


}//uzair dr done