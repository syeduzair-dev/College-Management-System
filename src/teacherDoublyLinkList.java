import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

class teacherDoublyLinkList {
    Teacher head;
    Teacher tail;
    int id;
    Scanner sc = new Scanner(System.in);

    teacherDoublyLinkList() {
        head = null;
        tail = null;
        id = 1;
    }

    //-------------------------------------isEmpty----------------------------------------


    public boolean isEmpty() {
        return head == null;
    }

    String Filename = "t.txt";


    //--------------------------add teacher-----------------------------
    public void addTeacher(String name, String gender, String dateOfBirth, String maritalStatus, String email,
                           String specialization, String highestQualification, String contact, String address,
                           String dateOfJoining, double salary, String skills, String experience) {
        Teacher newTeacher = new Teacher();
        newTeacher.teacherID = id;
        newTeacher.name = name;
        newTeacher.gender = gender;
        newTeacher.dateOfBirth = dateOfBirth;
        newTeacher.maritalStatus = maritalStatus;
        newTeacher.email = email;
        newTeacher.specialization = specialization;
        newTeacher.highestQualification = highestQualification;
        newTeacher.contact = contact;
        newTeacher.address = address;
        newTeacher.dateOfJoining = dateOfJoining;
        newTeacher.salary = salary;
        newTeacher.skills = skills;
        newTeacher.experience = experience;
        String password = name+"$123";
        newTeacher.pass=password;
        if (isEmpty()) {
            head = newTeacher;
            tail = newTeacher;
            newTeacher.next = head;
            newTeacher.prev = tail;
        } else {
            tail.next = newTeacher;
            newTeacher.prev = tail;
            tail = newTeacher;
            tail.next = head;
            head.prev = tail;}

        System.out.println("Admission successful. username " + newTeacher.name + "password"+password +"!");
        id++;}

//---------------------------remove teacher--------------------------------

    public void removeTeacher(int Id) {
        if (isEmpty()) {
            System.out.println("The system contains no Teacher to remove.");
            return;}  Teacher current = head;
        do {
            if (current.teacherID == Id) {
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
                    saveToFile();}
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    saveToFile();}
                System.out.println("Teacher with ID " + Id + " removed.");
                return;} current = current.next;
            if (current == head) break;
        }while (current!=head);
        System.out.println("Teacher with ID " + Id + " not found.");}


    // ----------------------------update teacher----------------------------------------

    public void updateTeacher(int TeacherID) {
        if (isEmpty()) {
            System.out.println("No teacher available.");
            return;
        }

        Teacher temp = head;
        while (temp != null) {
            if (temp.teacherID == TeacherID) break;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid ID");
            return;
        }

        while (true) {
            System.out.println("\nCurrent Details:");
            System.out.println("Name: " + temp.name);
            System.out.println("Gender: " + temp.gender);
            System.out.println("Contact: " + temp.contact);
            System.out.println("Email: " + temp.email);
            System.out.println("DOB: " + temp.dateOfBirth);
            System.out.println("Address: " + temp.address);
            System.out.println("Marital Status: " + temp.maritalStatus + "\n");

            System.out.println("1. Name");
            System.out.println("2. Gender");
            System.out.println("3. Contact");
            System.out.println("4. Email");
            System.out.println("5. DOB");
            System.out.println("6. Address");
            System.out.println("7. Marital Status");
            System.out.println("0. Save");
            System.out.print("Select option to update: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear buffer
                continue;
            }

            switch (choice) {
                case 1:
                    // Update Name
                    while (true) {
                        try {
                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine().trim();
                            if (!newName.isEmpty()) {
                                temp.name = newName;
                                System.out.println("Name updated successfully.");
                                saveToFile();

                                break;
                            } else {
                                System.out.println("Name cannot be empty. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;

                case 2:

                    while (true) {
                        try {
                            System.out.println("Enter new gender:");
                            System.out.println("1. Male");
                            System.out.println("2. Female");
                            System.out.println("3. Other");
                            int genderChoice = sc.nextInt();
                            sc.nextLine();

                            if (genderChoice == 1) {
                                temp.gender = "Male";
                                System.out.println("Gender updated successfully.");
                                saveToFile();
                                break;
                            } else if (genderChoice == 2) {
                                temp.gender = "Female";
                                System.out.println("Gender updated successfully.");
                                saveToFile();
                                break;
                            } else if (genderChoice == 3) {
                                temp.gender = "Other";
                                System.out.println("Gender updated successfully.");
                                saveToFile();
                                break;
                            } else {
                                System.out.println("Invalid choice! Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            sc.nextLine(); // Clear buffer
                        }
                    }
                    break;

                case 3:
                    // Update Contact
                    while (true) {
                        try {
                            System.out.print("Enter new contact: ");
                            String newContact = sc.nextLine().trim();
                            if (!newContact.isEmpty() && newContact.matches("\\d{10,12}")) {
                                temp.contact = newContact;
                                System.out.println("Contact updated successfully.");
                                saveToFile();
                                break;
                            } else {
                                System.out.println("Invalid contact number. Please enter a valid number (10-12 digits).");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;

                case 4:
                    // Update Email
                    while (true) {
                        try {
                            System.out.print("Enter new email: ");
                            String newEmail = sc.nextLine().trim();
                            if (!newEmail.isEmpty() && newEmail.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                                temp.email = newEmail;
                                System.out.println("Email updated successfully.");
                                saveToFile();
                                break;
                            } else {
                                System.out.println("Invalid email format. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;

                case 5:
                    // Update DOB
                    while (true) {
                        try {
                            System.out.print("Enter new DOB (dd-MM-yyyy): ");
                            String dobInput = sc.nextLine().trim();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            LocalDate.parse(dobInput, formatter);
                            temp.dateOfBirth = dobInput;
                            System.out.println("DOB updated successfully.");
                            saveToFile();
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please enter again in 'dd-MM-yyyy' format.");
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;

                case 6:
                    // Update Address
                    while (true) {
                        try {
                            System.out.print("Enter new address: ");
                            String newAddress = sc.nextLine().trim();
                            if (!newAddress.isEmpty()) {
                                temp.address = newAddress;
                                System.out.println("Address updated successfully.");
                                saveToFile();
                                break;
                            } else {
                                System.out.println("Address cannot be empty. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;

                case 7:
                    // Update Marital Status
                    while (true) {
                        try {
                            System.out.println("Enter new marital status:");
                            System.out.println("1. Married");
                            System.out.println("2. Unmarried");
                            int statusChoice = sc.nextInt();
                            sc.nextLine();

                            if (statusChoice == 1) {
                                temp.maritalStatus = "Married";
                                System.out.println("Marital status updated successfully.");
                                saveToFile();
                                break;
                            } else if (statusChoice == 2) {
                                temp.maritalStatus = "Unmarried";
                                System.out.println("Marital status updated successfully.");
                                break;
                            }
                            else {
                                System.out.println("Invalid choice! Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Teacher updated successfully.");
                    saveToFile();
                    return;

                default:
                    System.out.println("Invalid choice, please choose again.");
            }
        }
    }

    public void displayAllTeachers() {
        Teacher temp = head;
        System.out.println("                                                 * * * TEACHER DETAILS * * *                               ");
        System.out.println("|==========|====================|===========|=============================|===================|=============|========================================|=============================|=============================|");
        System.out.println("|    ID    |        NAME        |   GENDER  |        EXPERIENCE           |      CONTACT      |    SALARY   |                   EMAIL                |    HIGHEST QUALIFICATION    |      SPECIALIZATION         |");
        System.out.println("|==========|====================|===========|=============================|===================|=============|========================================|=============================|=============================|");
        if (isEmpty()) {
            System.out.println("|  - - - - |       - - - -      |  - - - -  |           - - - -           |      - - - -      |   - - - -   |               - - - -                  |            - - - -          |          - - - -            |");
            System.out.println("|----------|--------------------|-----------|-----------------------------|-------------------|-------------|----------------------------------------|-----------------------------|-----------------------------|");
            return;
        }
        while (true) {
            System.out.printf("|%-10s|%-20s|%-11s|%-29s|%-19s|%-13s|%-40s|%-29s|%-29s|\n",
                    temp.teacherID, temp.name, temp.gender, temp.experience, temp.contact, temp.salary, temp.email, temp.highestQualification, temp.specialization);
            System.out.println("|----------|--------------------|-----------|-----------------------------|-------------------|-------------|----------------------------------------|-----------------------------|----------------------------|");
            temp = temp.next;
            if (temp==head){
                break;
            }
        }
    }

    public void searchTeacher(int TeacherID) {
        if (isEmpty()) {
            System.out.println("no staff available ..");
            return;
        }
        Teacher temp = head;
        while (temp != null) {
            if (temp.teacherID == TeacherID)
                break;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Invalid ID");return;
        } else {
            System.out.println("                                                 * * * TEACHER DETAILS * * *                               \n");
            System.out.println("\uF0D8\tPersonal details : ");
            System.out.println("|====================|============|===================|=============|================|=========================================|");
            System.out.println("|        NAME        |   GENDER   |       DOB         |   CONTACT   | MARITAL STATUS |                  EMAIL                  |");
            System.out.println("|====================|============|===================|=============|================|=========================================|");
            System.out.printf("|%-20s|%-12s|%-19s|%-13s|%-16s|%-41s|\n", temp.name, temp.gender, temp.dateOfBirth, temp.contact, temp.maritalStatus, temp.email);
            System.out.println("|--------------------|------------|-------------------|-------------|----------------|-----------------------------------------|\n");
            System.out.println("\uF0D8\tEmployment Details: ");
            System.out.println("|====================|===================|===================|=============|");
            System.out.println("|    TEACHER ID      |  SPECIALIZATION   |  DATE OF JOINING  |    SALARY   |");
            System.out.println("|====================|===================|===================|=============|");
            System.out.printf("|%-20s|%-19s|%-19s|%-13s|\n", temp.teacherID, temp.specialization, temp.dateOfJoining, temp.salary);
            System.out.println("|--------------------|-------------------|-------------------|-------------|\n");
            System.out.println("\uF0D8\tSkills and Experience: ");
            System.out.println("|===================================|===================|=====================================|");
            System.out.println("|              SKILLS               |    EXPERIENCE     |     HIGHEST QUALIFICATION      |");
            System.out.println("|===================================|===================|=====================================|");
            System.out.printf("|%-35s|%-19s|%-37s|\n", temp.skills, temp.experience, temp.highestQualification);
            System.out.println("|-----------------------------------|-------------------|--------------------------------|\n");}}


    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            Teacher temp = head;
            writer.write("\t\t\t\t\t* * * * TEACHER DETAILS * * * *\n\n");
           do{
                writer.write("Teacher ID: " + temp.teacherID + "\n");
                writer.write("Name: " + temp.name + "\n");
                writer.write("Password: "+temp.pass +"\n");
                writer.write("Gender: " + temp.gender + "\n");
                writer.write("DOB: " + temp.dateOfBirth + "\n");
                writer.write("Marital Status:"+temp.maritalStatus +"\n");
                writer.write("Email :" +temp.email+"\n");
                writer.write("Date of Joining: " + temp.dateOfBirth + "\n");
                writer.write("Contact: " + temp.contact + "\n");
                writer.write("Address: " + temp.address + "\n");
                writer.write("Salary: " + temp.salary + "\n");
                writer.write("Skills: " + temp.skills + "\n");
                writer.write("Experience: " + temp.experience + "\n");
                writer.write("Highest Qualification: " + temp.highestQualification + "\n");
                writer.write("Specialization: " + temp.specialization + "\n");
                writer.write("\n\n");
                temp = temp.next;

            }while (temp!=head);
        } catch (IOException e) {
            System.out.println("Error saving teacher details to file.");
        }
    }
    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
            String line;
            int teacherID = 0;
            String name = "", gender = "", dateOfBirth = "", contact = "", address = "";
            String specialization = "", skills = "", experience = "", dateOfJoining = "";
            String email = "", maritalStatus = "", highestQualification = "";
            double salary = 0.0;
            int TotalNumberOfTeachers = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Teacher ID:")) {
                    if (teacherID != 0) {
                        TotalNumberOfTeachers++; // Increment total teacher count
                        addTeacher(name, gender,dateOfBirth, maritalStatus, email,
                                specialization,highestQualification, contact, address,
                                dateOfJoining, salary, skills, experience);}
                    teacherID = Integer.parseInt(line.split(":")[1].trim());
                    name = gender = dateOfBirth = contact = address = specialization = skills =
                            experience = dateOfJoining = email = maritalStatus = highestQualification = "";salary = 0.0;
                } else if (line.startsWith("Name:")) {
                    name = line.split(":")[1].trim();
                }

                else if (line.startsWith("Email :")) {
                    email = line.split(":")[1].trim();
                }

                else if (line.startsWith("Gender:")) {
                    gender = line.split(":")[1].trim();
                } else if (line.startsWith("DOB:")) {
                    dateOfBirth = line.split(":")[1].trim();}
                else if (line.startsWith("Date of Joining:")) {
                    dateOfJoining = line.split(":")[1].trim();
                } else if (line.startsWith("Contact:")) {
                    contact = line.split(":")[1].trim();
                } else if (line.startsWith("Address:")) {
                    address = line.split(":")[1].trim();
                } else if (line.startsWith("Salary:")) {
                    try {
                        salary = Double.parseDouble(line.split(":")[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary format for teacher ID " + teacherID + ". Setting salary to 0.");
                        salary = 0.0;
                    }

                } else if (line.startsWith("Skills:")) {
                    skills = line.split(":")[1].trim();
              } else if (line.startsWith("Experience:")) {
                    experience = line.split(":")[1].trim();
                } else if (line.startsWith("Specialization:")) {
                    specialization = line.split(":")[1].trim();
                }  else if (line.startsWith("Marital Status:")) {
                    maritalStatus = line.split(":")[1].trim();
                } else if (line.startsWith("Highest Qualification:")) {
                    highestQualification = line.split(":")[1].trim();
                }}
            if (teacherID != 0) {
                TotalNumberOfTeachers++; // Increment total teacher count
                addTeacher(name, gender,dateOfBirth, maritalStatus, email,
                    specialization,highestQualification, contact, address,
                    dateOfJoining, salary, skills, experience);}
            System.out.println("Total number of teachers added: " + TotalNumberOfTeachers);
        } catch (IOException e) {
            System.out.println("Error reading teacher details from file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while reading teacher details: " + e.getMessage());}}

}