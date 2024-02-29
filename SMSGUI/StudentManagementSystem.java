package SMSGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StudentManagementSystem {
    private List<Student> students;
    static final String CSV_FILE = "students.csv";

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        loadStudentsFromCSV();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToCSV();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
        saveStudentsToCSV();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> displayAllStudents() {
		return students;
    }

    private void loadStudentsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6], Integer.parseInt(data[7]));
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveStudentsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Student student : students) {
                bw.write(student.getName() + "," + student.getRollNumber() + "," + student.getClassName() + "," + student.getSection() + "," + student.getMotherName() + "," + student.getFatherName() + "," + student.getParentPhoneNumber() + "," + student.getGrade());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}