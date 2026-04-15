

import java.util.InputMismatchException;
import java.util.Scanner;

class CollegeManagementSystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        studentDoublyCircularLinkList studentList = new studentDoublyCircularLinkList();
        teacherDoublyLinkList teacherList = new teacherDoublyLinkList();
        studentList.readFromFile("s.txt");
        teacherList.readFromFile();
        Scanner sc = new Scanner(System.in);
        int mainChoice = -1;

        while (true) {

            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║         WELCOME TO COLLEGE MANAGEMENT SYSTEM     ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.println("\nPlease choose a module to proceed:");
            System.out.println("  1️⃣ Principal Module");
            System.out.println("  2️⃣️ Admin Module");
            System.out.println("  3️⃣ Teacher Module");
            System.out.println("  4️⃣ Student Module");
            System.out.println("  5️⃣ Attendance Module");
            System.out.println("  6️⃣ Fee Module");
            System.out.println("  0️⃣ Exit");
            System.out.println("\n════════════════════════════════════════════════════");
            System.out.print("👉 Enter your choice: ");

            try {

                mainChoice = sc.nextInt();


                switch (mainChoice) {
                    case 1:
                        principalModule(studentList, teacherList);

                        break;
                    case 2:
                        adminModule( studentList, teacherList);

                        break;
                    case 3:
                        teacherModule( studentList, teacherList);

                        break;
                    case 4:
                        studentModule(studentList);

                        break;
                    case 5:
                        attendanceModule( studentList,teacherList);

                        break;
                    case 6:
                        feeModule( studentList);

                        break;
                   case 0:
                        System.out.println("Exiting the system. Goodbye!");
                      return;
                    default:
                        System.out.println("Invalid choice. Please try again.");

                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a number.");

            }
        }


    }
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    //---------------------------------------------- Principal Module------------------------------------------

    public static void principalModule( studentDoublyCircularLinkList studentList, teacherDoublyLinkList teacherList)
    {

        String pass ="principal$123";
        System.out.print("Enter principal password: ");
        String enteredPassword = sc.nextLine();

        if (!pass.equals(enteredPassword)) {
            System.out.println("Incorrect Password");
            return;
        }


        int choice = -1;
        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║         PRINCIPAL MODULE             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Display All Teachers");
            System.out.println("2. Search Teacher by ID");
            System.out.println("3. Display All Students in Department");
            System.out.println("4. Display all College Student ");
            System.out.println("5. Display Student Attendance by ID");
            System.out.println("6. Display Attendance by Date");
            System.out.println("0. Back to Main Menu");
            System.out.println("═══════════════════════════════════════");
            System.out.print("👉 Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

//


            switch (choice) {
                case 1:
                    try {

                        teacherList.displayAllTeachers() ;
                    } catch (Exception e) {
                        System.out.println("Error displaying teachers: " + e.getMessage());
                    }
           
                    break;
                case 2:
                    try {
                        System.out.print("Enter Teacher ID to search: ");
                        int searchID = sc.nextInt();

                        teacherList.searchTeacher(searchID);
                    } catch (Exception e) {
                        System.out.println("Error searching teacher: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                case 3:
                    try {
                        // Display all students
                        System.out.print("Enter Department: ");

                        String dept = sc.nextLine();

                        System.out.print("Enter Class: ");
                        int classNumber = sc.nextInt();

                        studentList.display(dept, classNumber);
                    } catch (Exception e) {
                        System.out.println("Error displaying students: " + e.getMessage());
                        sc.nextLine();
                    }

                    break;


                case 4:
                    try {

                        System.out.println("sorting Student By Name");
                        studentList.heapSortLinkedList();
                        studentList.display();
                    } catch (Exception e) {
                        System.out.println("Error displaying students: " + e.getMessage());
                    }

                    break;


                case 5: // Display Student Attendance
                    try {


                        System.out.print("Enter Student ID: ");
                        String attendanceId = sc.nextLine();
                        studentList.displayStudentAttendance(attendanceId);
                    } catch (Exception e) {
                        System.out.println("Error displaying student attendance: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {

                        System.out.print("Enter Department: ");
                        String attendanceDept = sc.nextLine();
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        System.out.print("Enter Class: ");
                        int attendanceYear = sc.nextInt();
                        studentList.displayAttendanceByDate(attendanceDept, date, attendanceYear);
                    } catch (Exception e) {
                        System.out.println("Error displaying attendance: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Returning to Main Menu...");
                   return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }




    //--------------------------------------------------- Admin Module------------------------------------------------



    public static void adminModule( studentDoublyCircularLinkList studentList, teacherDoublyLinkList teacherList)
    {
        String pass ="admin$123";
        System.out.print("Enter Admin password: ");
        String enteredPassword = sc.nextLine();

        if (!pass.equalsIgnoreCase(enteredPassword)) {
            System.out.println("Incorrect Password");
            return;
        }
        int choice = -1;
        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║            ADMIN MODULE              ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Add Teacher");
            System.out.println("2. Add Student");
            System.out.println("3. Display Teacher");
            System.out.println("4. Display All Students in Department");
            System.out.println("5. Update Teacher");
            System.out.println("6. Remove Teacher");
            System.out.println("7. Remove Student");
            System.out.println("8. Update Student");
            System.out.println("9. Add Course");
            System.out.println("10. Remove Course");
            System.out.println("11. Add Marks for All Courses");
            System.out.println("12. Promote to Second Year with New IDs");
            System.out.println("0. Back to Main Menu");
            System.out.println("═══════════════════════════════════════");
            System.out.print("👉 Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                          // Consume newline
                        System.out.println("Enter Teacher Details:");

                        // Name
                        String name;
                        while (true) {
                            try {
                                System.out.print("Name: ");
                                name = sc.nextLine();
                                if (!isEmpty(name)) break;  // No need for isEmpty
                                System.out.println("Name cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Gender
                        String gender;
                        while (true) {
                            try {
                                System.out.print("Gender: ");
                                gender = sc.nextLine();
                                if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other")) break;
                                System.out.println("Please enter a valid gender (Male/Female/Other).");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Date of Birth
                        String dob;
                        while (true) {
                            try {
                                System.out.print("Date of Birth (dd-MM-yyyy): ");
                                dob = sc.nextLine();
                                if (dob.matches("\\d{2}-\\d{2}-\\d{4}")) break;
                                System.out.println("Invalid date format! Please use 'dd-MM-yyyy'.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Marital Status
                        String maritalStatus;
                        while (true) {
                            try {
                                System.out.print("Marital Status: ");
                                maritalStatus = sc.nextLine();
                                if (!isEmpty(maritalStatus)) break;  // Avoiding isEmpty
                                System.out.println("Marital Status cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Email
                        String email;
                        while (true) {
                            try {
                                System.out.print("Email: ");
                                email = sc.nextLine();
                                if (email.contains("@") && email.contains(".")) break;
                                System.out.println("Invalid email! Please try again.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Specialization
                        String specialization;
                        while (true) {
                            try {
                                System.out.print("Specialization: ");
                                specialization = sc.nextLine();
                                if (!isEmpty(specialization)) break;  // Avoiding isEmpty
                                System.out.println("Specialization cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Highest Qualification
                        String highestQualification;
                        while (true) {
                            try {
                                System.out.print("Highest Qualification: ");
                                highestQualification = sc.nextLine();
                                if (!highestQualification.isEmpty())
                                    break;
                                System.out.println("Highest Qualification cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Contact
                        String contact;
                        while (true) {
                            try {
                                System.out.print("Contact: ");
                                contact = sc.nextLine();
                                if (contact.matches("\\d+")) break;
                                System.out.println("Contact number must contain only digits.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Address
                        String address;
                        while (true) {
                            try {
                                System.out.print("Address: ");
                                address = sc.nextLine().trim();
                                if (!isEmpty(address)) break;  // Avoiding isEmpty
                                System.out.println("Address cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Date of Joining
                        String dateOfJoining;
                        while (true) {
                            try {
                                System.out.print("Date of Joining (dd-MM-yyyy): ");
                                dateOfJoining = sc.nextLine();
                                if (dateOfJoining.matches("\\d{2}-\\d{2}-\\d{4}")) break;
                                System.out.println("Invalid date format! Please use 'dd-MM-yyyy'.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Salary
                        double salary;
                        while (true) {
                            try {
                                System.out.print("Salary: ");
                                salary = sc.nextDouble();
                                if (salary > 0) break;
                                System.out.println("Salary must be a positive number.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a numeric value.");
                                sc.next();  // Clear invalid input
                            }
                        }

                        sc.nextLine();  // Consume newline after double input

                        // Skills
                        String skills;
                        while (true) {
                            try {
                                System.out.print("Skills: ");
                                skills = sc.nextLine().trim();
                                if (!isEmpty(skills)) break;  // Avoiding isEmpty
                                System.out.println("Skills cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Experience
                        String experience;
                        while (true) {
                            try {
                                System.out.print("Experience: ");
                                experience = sc.nextLine();
                                if (!isEmpty(experience)) break;  // Avoiding isEmpty
                                System.out.println("Experience cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Add teacher to the list and save to file
                        teacherList.addTeacher(name, gender, dob, maritalStatus, email, specialization, highestQualification,
                                contact, address, dateOfJoining, salary, skills, experience);
                        teacherList.saveToFile();

                        System.out.println("Teacher added successfully!");

                    } catch (Exception e) {
                        System.out.println("Error adding teacher: " + e.getMessage());
                    }
                    break;


                case 2:


                    try {
                        // Student Name
                        String studentName;
                        while (true) {
                            try {
                                System.out.print("Enter Student Name: ");
                                studentName = sc.nextLine();
                                if (!isEmpty(studentName)) break;
                                System.out.println("Student Name cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Father's Name
                        String father;
                        while (true) {
                            try {
                                System.out.print("Enter Father's Name: ");
                                father = sc.nextLine();
                                if (!isEmpty(father)) break;
                                System.out.println("Father's Name cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Gender
                        String studentGender;
                        while (true) {
                            try {
                                System.out.print("Enter Gender (Male/Female/Other): ");
                                studentGender = sc.nextLine().trim();
                                if (studentGender.equalsIgnoreCase("Male") || studentGender.equalsIgnoreCase("Female") || studentGender.equalsIgnoreCase("Other")) break;
                                System.out.println("Please enter a valid gender: Male, Female, or Other.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Address
                        String studentAddress;
                        while (true) {
                            try {
                                System.out.print("Enter Address: ");
                                studentAddress = sc.nextLine();
                                if (!isEmpty(studentAddress)) break;
                                System.out.println("Address cannot be empty!");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Contact Number
                        String studentContact;
                        while (true) {
                            try {
                                System.out.print("Enter Contact Number: ");
                                studentContact = sc.nextLine();
                                if (studentContact.matches("\\d+")) break;
                                System.out.println("Contact Number must contain only numbers.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // CNIC
                        String cnic;
                        while (true) {
                            try {
                                System.out.print("Enter new CNIC: ");
                                cnic = sc.nextLine().trim();
                                if (cnic.matches("\\d{5}-\\d{7}-\\d")) break;
                                System.out.println("Invalid CNIC format. Please enter in '12345-6789012-3' format.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        System.out.println("Eligibility Criteria:");
                        System.out.println("Pre-Engineering: 80% required");
                        System.out.println("Computer Science: 75% required");
                        System.out.println("Pre-Medical: 70% required");
                        System.out.println();
                        int matricPercentage;
                        while (true) {
                            try {
                                System.out.print("Enter Matric Percentage: ");
                                matricPercentage = sc.nextInt();
                                sc.nextLine();

                                if (matricPercentage >= 0 && matricPercentage <= 100) {
                                        break;
                                    } else {
                                        System.out.println("Matric Percentage must be between 0 and 100.");
                                    }



                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");

                            }
                        }


                        String matricStatus;
                        while (true) {
                            try {
                                System.out.print("Enter Matriculation Status (Science/Biology/Failed): ");
                                matricStatus = sc.nextLine().trim();
                                if (matricStatus.equalsIgnoreCase("Science") || matricStatus.equalsIgnoreCase("Biology") || matricStatus.equalsIgnoreCase("Failed"))
                                    break;
                                System.out.println("Please enter a valid Matriculation Status: Science, Biology, or Failed.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Department
                        String department;
                        while (true) {
                            try {
                                System.out.print("Enter Selected Department (Pre-Engineering/Pre-Medical/Computer Science): ");
                                department = sc.nextLine().trim();
                                if (department.equalsIgnoreCase("Pre-Engineering") || department.equalsIgnoreCase("Pre-Medical") || department.equalsIgnoreCase("Computer Science")) break;
                                System.out.println("Please enter a valid department.");
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // Add student to the list and save to file
                        studentList.addStudent(studentName, father, studentGender, studentAddress, studentContact, cnic, matricPercentage, matricStatus, department);
                        studentList.saveToFile();

                        System.out.println("Student added successfully!");

                    } catch (Exception e) {
                        // Catch any unexpected exceptions
                        System.out.println("Error adding student: " + e.getMessage());
                    }
                    break;

                case 3:

                    try {
                        teacherList.displayAllTeachers();
                    } catch (Exception e) {
                        System.out.println("Error displaying teachers: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        // Display all students
                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();
                        System.out.print("Enter Class: ");
                        int classNumber = sc.nextInt();
                        studentList.display(dept, classNumber);
                    } catch (Exception e) {
                        System.out.println("Error displaying students: " + e.getMessage());
                    }

                    break;

                case 5:
                    try {
                        System.out.print("Enter Teacher ID to update: ");
                        int updateID = sc.nextInt();
                        teacherList.updateTeacher(updateID);
                        teacherList.saveToFile();

                    } catch (Exception e) {
                        System.out.println("Error updating teacher: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        System.out.print("Enter Teacher ID to remove: ");
                        int removeID = sc.nextInt();
                        teacherList.removeTeacher(removeID); // Fixed the method call to removeTeacher
                        teacherList.saveToFile();
                    } catch (Exception e) {
                        System.out.println("Error removing teacher: " + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        // Remove student
                        System.out.print("Enter Student ID to Remove: ");
                        String removeId = sc.nextLine();
                        studentList.removeStudent(removeId);
                        studentList.saveToFile();
                    } catch (Exception e) {
                        System.out.println("Error removing student: " + e.getMessage());
                    }
                    break;

                case 8:
                    try {
                        // Update student
                        System.out.print("Enter Student ID to Update: ");
                        String studentId = sc.nextLine();
                        studentList.updateStudent(studentId);
                        studentList.saveToFile();
                    } catch (Exception e) {
                        System.out.println("Error updating student: " + e.getMessage());
                    }
                    break;
                case 9: // Add Course
                    try {
                        System.out.print("Enter Department: ");
                        String courseDept = sc.nextLine();
                        System.out.print("Enter Year: ");
                        int courseYear = sc.nextInt();
                        sc.nextLine();
                        studentList.addCourseForClass(courseYear, courseDept);
                        studentList.saveToFile();

                    } catch (Exception e) {
                        System.out.println("Error adding course: " + e.getMessage());
                    }
                    break;
                case 10: // Remove Course
                    try {
                        System.out.print("Enter Department: ");
                        String removeCourseDept = sc.nextLine();
                        System.out.print("Enter Year: ");
                        int removeCourseYear = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Course: ");
                        String removeCourseName = sc.nextLine();
                        studentList.removeCourseForClass( removeCourseDept, removeCourseYear,removeCourseName);
                        studentList.saveToFile();
                        System.out.println("Course removed successfully.");
                    } catch (Exception e) {
                        System.out.println("Error removing course: " + e.getMessage());
                    }
                    break;
                case 11: // Add Marks for All Courses
                    try {
                        System.out.print("Enter Department: ");
                        String marksDept = sc.nextLine();
                        System.out.print("Enter Year: ");
                        int marksYear = sc.nextInt();
                        sc.nextLine();
                        studentList.addMarksForAllCoursesInClass(marksDept, marksYear);
                        System.out.println("Marks added for all courses.");
                        studentList.saveToFile();
                    } catch (Exception e) {
                        System.out.println("Error adding marks: " + e.getMessage());
                    }
                    break;

                case 12: // Promote to Second Year
                    try {
                        studentList.promoteToSecondYearWithNewIds();
                        System.out.println("Students promoted to the second year.");
                        studentList.saveToFile();
                    } catch (Exception e) {
                        System.out.println("Error promoting to second year: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//--------------------------------------------------teacher node------------------------------------------------------

    public static void teacherModule(studentDoublyCircularLinkList studentList, teacherDoublyLinkList teacherList)  {
        int choice = -1;

        System.out.print("Enter teacher password: ");
        String enteredPassword = sc.nextLine();
        Teacher current = teacherList.head;

        while (true) {
            if (current.pass.equalsIgnoreCase(enteredPassword)) {
                System.out.println("Correct name and password! Welcome, " + current.name);
               break;
            }
            current = current.next;
            if(current==teacherList.head){
            System.out.println("Invalid credentials. Returning to Main Menu.");
            return;
        }
        }

        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║           TEACHER MODULE             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Search Teacher by ID");
            System.out.println("2  Display all student ");
            System.out.println("3. Display Student Attendance by ID");
            System.out.println("4. Display Attendance by Date");
            System.out.println("5  display mark sheet ");
            System.out.println("0. Back to Main Menu");
            System.out.println("═══════════════════════════════════════");
            System.out.print("👉 Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Teacher ID to search: ");
                        int searchID = sc.nextInt();
                        sc.nextLine();
                        teacherList.searchTeacher(searchID);
                    } catch (Exception e) {
                        System.out.println("Error searching teacher: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;

                case 2:
                    try {
                            // Display all students
                            System.out.print("Enter Department: ");
                            String dept = sc.nextLine();
                            System.out.print("Enter Class: ");
                            int classNumber = sc.nextInt();
                            studentList.display(dept, classNumber);
                        } catch (Exception e) {
                            System.out.println("Error displaying students: " + e.getMessage());
                            sc.nextLine();
                        }

                        break;



                case 3:
                    try {
                        System.out.print("Enter Student ID: ");
                        String attendanceId = sc.nextLine();
                        studentList.displayStudentAttendance(attendanceId);
                    } catch (Exception e) {
                        System.out.println("Error displaying student attendance: " + e.getMessage());
                    }
                    break;

                case 4: // Display Attendance by Date
                    try {
                        System.out.print("Enter Department: ");
                        String attendanceDept = sc.nextLine();
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        System.out.print("Enter Year: ");
                        int attendanceYear = sc.nextInt();
                        studentList.displayAttendanceByDate(attendanceDept, date, attendanceYear);

                    } catch (Exception e) {
                        System.out.println("Error displaying attendance: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                case 5:
                try {
                    System.out.print("Enter Year: ");
                    int marksSheetYear = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Student ID: ");
                    String marksSheetId = sc.nextLine();
                    studentList.displayMarkSheetById(marksSheetId, marksSheetYear);
                } catch (Exception e) {
                    System.out.println("Error displaying mark sheet: " + e.getMessage());
                    break;
                }
                break;
                case 6: // Display Student Attendance
                    try {
                        System.out.print("Enter Student ID: ");
                        String attendanceId = sc.nextLine();
                        studentList.displayStudentAttendance(attendanceId);
                    } catch (Exception e) {
                        System.out.println("Error displaying student attendance: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //----------------------------------------------Student Module---------------------------------------------------

    public static void studentModule(studentDoublyCircularLinkList studentList)  {
        int choice = -1;
        System.out.print("Enter student password: ");
        String enteredPassword = sc.nextLine();
        studentNode current = studentList.head;

        while (true) {  // Traverse the list
            // Check if both name and password match
            if (current.pass.equalsIgnoreCase(enteredPassword)) {
                System.out.println("Correct name and password! Welcome, " + current.name);
                break;
            }
            current = current.next;
            if(current==studentList.head){
                System.out.println("Invalid credentials. Returning to Main Menu.");
                return;

            }
        }
        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║           STUDENT MODULE             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Display Fee Voucher");
            System.out.println("2  student list in department and class wise");
            System.out.println("3. Display Courses for a Student");
            System.out.println("4  display Attendance by date");
            System.out.println("5. Display Student Attendance by ID");
            System.out.println("6. Display Mark Sheet for a Student");
            System.out.println("7. Intermediate Certificate");
            System.out.println("0. Back to Main Menu");
            System.out.println("═══════════════════════════════════════");
            System.out.print("👉 Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {



                case 1: // Display Fee Voucher
                    try {
                        System.out.print("Enter Student ID: ");
                        String feeId = sc.nextLine();
                        studentList.displayFeeVoucherByReferenceNumber(feeId);
                    } catch (Exception e) {
                        System.out.println("Error displaying fee voucher: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("sorting Student By Name");
                        studentList.heapSortLinkedList();
                        studentList.display();
                    } catch (Exception e) {
                        System.out.println("Error displaying students: " + e.getMessage());
                    }

                    break;

                case 3: // Display Courses for a Student
                    try {
                        System.out.print("Enter Student ID: ");
                        String studentCourseId = sc.nextLine();
                        studentList.displayCoursesByStudentId(studentCourseId);
                    } catch (Exception e) {
                        System.out.println("Error displaying courses: " + e.getMessage());
                    }
                    break;

                case 4: // Display Attendance by Date
                    try {
                        System.out.print("Enter Department: ");
                        String attendanceDept = sc.nextLine();
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        System.out.print("Enter Year: ");
                        int attendanceYear = sc.nextInt();
                        studentList.displayAttendanceByDate(attendanceDept, date, attendanceYear);
                    } catch (Exception e) {
                        System.out.println("Error displaying attendance: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;

                case 5: // Display Student Attendance
                    try {
                        System.out.print("Enter Student ID: ");
                        String attendanceId = sc.nextLine();
                        studentList.displayStudentAttendance(attendanceId);
                    } catch (Exception e) {
                        System.out.println("Error displaying student attendance: " + e.getMessage());
                    }
                    break;
                case 6: // Display Mark Sheet
                    try {

                        System.out.print("Enter Year: ");
                        int marksSheetYear = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Student ID: ");
                        String marksSheetId = sc.nextLine();
                        studentList.displayMarkSheetById(marksSheetId, marksSheetYear);
                    } catch (Exception e) {
                        System.out.println("Error displaying mark sheet: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.print("Enter Student ID: ");
                    String Id = sc.nextLine();
                    studentList.createIntermediateCertificate(Id);
                    studentList.saveToFile();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Attendance Module
    public static void  attendanceModule( studentDoublyCircularLinkList studentList,teacherDoublyLinkList teacherList)
   {
        int choice = -1;
       System.out.print("Enter teacher password: ");
       String enteredPassword = sc.nextLine();
       Teacher current = teacherList.head;
       while (true) {  // Traverse the list

           if (current.pass.equalsIgnoreCase(enteredPassword)) {
               System.out.println("Correct name and password! Welcome, " + current.name);
              break;
           }
           current = current.next;
           if(current==teacherList.head){
               System.out.println("Invalid credentials. Returning to Main Menu.");
              return;

           }
       }
        while (choice != 0) {

            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║        ATTENDANCE MODULE             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Mark Attendance for All Students");
            System.out.println("2. Display Student Attendance by ID");
            System.out.println("3. Display Attendance by Date");
            System.out.println("0. Back to Main Menu");
            System.out.println("═══════════════════════════════════════");
            System.out.print("👉 Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:

                    try {
                        // Department validation
                        String deptForAttendance;
                        while (true) {
                            System.out.print("Enter Department (Pre-Engineering/Pre-Medical/Computer Science): ");
                            deptForAttendance = sc.nextLine().trim();
                            if (deptForAttendance.equalsIgnoreCase("Pre-Engineering") ||
                                    deptForAttendance.equalsIgnoreCase("Pre-Medical") ||
                                    deptForAttendance.equalsIgnoreCase("Computer Science")) {
                                break;
                            } else {
                                System.out.println("Invalid department. Please try again.");
                            }
                        }

                        int yearForAttendance;
                        while (true) {
                            System.out.print("Enter Class (11/12): ");
                             yearForAttendance = sc.nextInt();
                                if (yearForAttendance == 11 || yearForAttendance == 12) {
                                    break;
                                } else {
                                    System.out.println("Invalid class year. Please enter 11 or 12.");
                                }

                            }


                        studentList.markAttendanceForAll(deptForAttendance, yearForAttendance);
                        System.out.println("Attendance marked for all.");
                        studentList.saveToFile();

                    } catch (Exception e) {
                        System.out.println("Error marking attendance: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Student ID: ");
                        String attendanceId = sc.nextLine();
                        studentList.displayStudentAttendance(attendanceId);
                        studentList.saveToFile();

                    } catch (Exception e) {
                        System.out.println("Error displaying student attendance: " + e.getMessage());
                    }
                    break;
                case 3: // Display Attendance by Date
                    try {
                        System.out.print("Enter Department (Pre-Engineering/Pre-Medical/Computer Science) : ");
                        String attendanceDept = sc.nextLine();
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        System.out.print("Enter Class: ");
                        int attendanceYear = sc.nextInt();
                        studentList.displayAttendanceByDate(attendanceDept, date, attendanceYear);

                    } catch (Exception e) {
                        System.out.println("Error displaying attendance: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }}

    //-------------------------------------------- Fee Module----------------------------------------------------

    public static void feeModule( studentDoublyCircularLinkList studentList)  {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║              FEE MODULE              ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Display Fee Voucher");
            System.out.println("2. Pay Fee");
            System.out.println("0. Back to Main Menu");
            System.out.println("═══════════════════════════════════════");
            System.out.print("👉 Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Display Fee Voucher
                    try {
                        System.out.print("Enter Student ID: ");
                        String feeId = sc.nextLine();
                        studentList.displayFeeVoucherByReferenceNumber(feeId);
                    } catch (Exception e) {
                        System.out.println("Error displaying fee voucher: " + e.getMessage());
                    }
                    break;
                case 2: // Pay Fee
                    try {
                        System.out.print("Enter Bill ID: ");
                        int billId = sc.nextInt();
                        studentList.payFeeWithConfirmation(billId);
                        studentList.saveToFile();
                    } catch (Exception e) {
                        System.out.println("Error paying fee: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public boolean checkEmpty(String input) {
        return !input.isEmpty();  // Returns true if the input is NOT empty
    }


}//===