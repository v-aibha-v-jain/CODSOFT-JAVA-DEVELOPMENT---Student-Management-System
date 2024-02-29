package SMS;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        

        int choice;
        do {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter student details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    // Check if the student already exists
                    Student existingStudent = sms.searchStudent(rollNumber);
                    if (existingStudent != null) {
                        System.out.println("Student with the given roll number already exists.");
                        System.out.print("Do you want to update the student details? (yes/no): ");
                        String updateChoice = scanner.nextLine();
                        if ("yes".equalsIgnoreCase(updateChoice)) {
                            System.out.print("Class: ");
                            String className = scanner.nextLine();
                            System.out.print("Section: ");
                            String section = scanner.nextLine();
                            System.out.print("Mother's Name: ");
                            String motherName = scanner.nextLine();
                            System.out.print("Father's Name: ");
                            String fatherName = scanner.nextLine();
                            System.out.print("Parent's Phone Number: ");
                            String parentPhoneNumber = scanner.nextLine();
                            System.out.print("Grade: ");
                            int grade = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            // Update the existing student details
                            existingStudent.setClassName(className);
                            existingStudent.setSection(section);
                            existingStudent.setMotherName(motherName);
                            existingStudent.setFatherName(fatherName);
                            existingStudent.setParentPhoneNumber(parentPhoneNumber);
                            existingStudent.setGrade(grade);
                            sms.saveStudentsToCSV(); // Save updated student details to CSV
                            System.out.println("Student details updated successfully.");
                        }
                        break;
                    }
                    // If the student does not exist, add a new student
                    System.out.print("Class: ");
                    String className = scanner.nextLine();
                    System.out.print("Section: ");
                    String section = scanner.nextLine();
                    System.out.print("Mother's Name: ");
                    String motherName = scanner.nextLine();
                    System.out.print("Father's Name: ");
                    String fatherName = scanner.nextLine();
                    System.out.print("Parent's Phone Number: ");
                    String parentPhoneNumber = scanner.nextLine();
                    System.out.print("Grade: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    sms.addStudent(new Student(name, rollNumber, className, section, motherName, fatherName, parentPhoneNumber, grade));
                    break;

                case 2:
                    System.out.print("Enter roll number of student to remove: ");
                    int rollToRemove = scanner.nextInt();
                    sms.removeStudent(rollToRemove);
                    break;
                case 3:
                    System.out.print("Enter roll number of student to search: ");
                    int rollToSearch = scanner.nextInt();
                    Student foundStudent = sms.searchStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found:");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("All students:");
                    sms.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}