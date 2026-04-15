
    class AttendanceRecord {
        String date;
        char status;               // 'P' for Present, 'A' for Absent
        AttendanceRecord next;

        public AttendanceRecord(String date, char status) {
            this.date = date;
            this.status = status;

        }
    }

