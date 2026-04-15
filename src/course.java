class Course {
    int capacity;
    int size;
    String[] courses;
    int[] marks;

    // Constructor
    public Course() {
        capacity = 3;
        size = 0;
        courses = new String[capacity];
        marks = new int[capacity];
    }
//======================================= size of array =========================================

    public int size() {

        return size;
    }

    // -------------------------- -add course--------------------------------------
    public void addCourse(String course) {
        for (int i = 0; i < size; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                System.out.println(course + " is already added.");
                return;
            }
        }


        if (size == capacity) {
            resize();
        }


        courses[size] = course;
        marks[size] = 0;
        size++;
    }

    // -------------------------------------remove  course------------------------------------------------

    public void removeCourse(String course) {
        int index = -1;

            for (int i = 0; i < size; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Course not found: " + course);
            return;
        }


        for (int i = index; i < size - 1; i++) {
            courses[i] = courses[i + 1];
            marks[i] = marks[i + 1];
        }

        // Clear the last entry
        courses[size - 1] = null;
        marks[size - 1] = 0;
        size--;
    }

    //------------------------------ Set marks------------------------------------------------

    public void setMarks(String course, int mark) {
        for (int i = 0; i < size; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                marks[i] = mark;
                return;
            }
        }
        System.out.println("Course \"" + course + "\" is not enrolled.");
    }

    // ---------------------------------resize arrays-----------------------------------------------

    private void resize() {
        capacity *= 2;
        String[] newCourses = new String[capacity];
        int[] newMarks = new int[capacity];

        for (int i = 0; i < size; i++) {
            newCourses[i] = courses[i];
            newMarks[i] = marks[i];
        }

        courses = newCourses;
        marks = newMarks;
    }

//------------------------------display course------------------------------------------------

    public void displayCourses() {
        if (size == 0) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("Courses:");
        for (int i = 0; i < size; i++) {
            System.out.println("- " + courses[i]);
        }
    }

    // --------------------------clear course---------------------------
    public void clearCourses() {
        for (int i = 0; i < size; i++) {
            courses[i] = null;
            marks[i] = 0;
        }
        size = 0;
        System.out.println("All courses and marks cleared.");
    }

    // --------------------------Calculate Total, Percentage, Grade---------------------------

     public int totalMarks() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += marks[i];
        }
        return total;
    }

// ================================================= max mark ================================

    public int totalMaxMarks() {
        int totalMax = 0;
        for (int i = 0; i < size; i++) {
            totalMax += 100;
        }
        return totalMax;
    }


    public double calculatePercentage() {
        int totalObtained = totalMarks();
        int totalMax = totalMaxMarks();
        return ((double) totalObtained / totalMax) * 100;
    }


    public String calculateGrade() {
        if (seconderEligible()) {
            double percentage = calculatePercentage();

            if (percentage >= 90) {
                return "A+";
            } else if (percentage >= 80) {
                return "A";
            } else if (percentage >= 70) {
                return "B";
            } else if (percentage >= 60) {
                return "C";
            } else if (percentage >= 50) {
                return "D";
            }

        }
            return "F";
        }
        //-------------------check eligible
    public boolean seconderEligible() {

        for (int i = 0; i < size; i++) {
            if (marks[i] < 40) {
                return false;
            }
        }

        return true;
    }




}