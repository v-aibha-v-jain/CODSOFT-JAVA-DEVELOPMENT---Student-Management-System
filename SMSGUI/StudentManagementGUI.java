package SMSGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


class StudentManagementGUI extends JFrame {
    private StudentManagementSystem sms;
    private JPanel mainPanel, addPanel, displayPanel;
    private JTextField nameField, rollNumberField, classNameField, sectionField, motherNameField, fatherNameField, parentPhoneNumberField, gradeField;
    private JTextArea displayTextArea;
    private JButton addButton, searchButton, deleteButton, displayButton;

    public StudentManagementGUI(StudentManagementSystem sms) {
        this.sms = sms;
        setTitle("Student Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setLayout(new GridLayout(2,2,30,30));

        addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddPanel();
            }
        });

        searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rollNumber = getRollNumberFromUser("Enter roll number to search:");
                if (rollNumber != -1) {
                    Student student = sms.searchStudent(rollNumber);
                    if (student != null) {
//                        JOptionPane.showMessageDialog(StudentManagementGUI.this, "Student found:\n" + student);
                    } else {
                        JOptionPane.showMessageDialog(StudentManagementGUI.this, "Student not found.");
                    }
                }
            }
        });

        deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rollNumber = getRollNumberFromUser("Enter roll number to delete:");
                if (rollNumber != -1) {
                    sms.removeStudent(rollNumber);
                    JOptionPane.showMessageDialog(StudentManagementGUI.this, "Student deleted successfully.");
                }
            }
        });

        displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        mainPanel.add(addButton);
        mainPanel.add(searchButton);
        mainPanel.add(deleteButton);
        mainPanel.add(displayButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void showAddPanel() {
        if (addPanel == null) {
            addPanel = new JPanel();
            addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
            
            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField(20);

            JLabel rollNumberLabel = new JLabel("Roll Number:");
            rollNumberField = new JTextField(20);

            JLabel classNameLabel = new JLabel("Class:");
            classNameField = new JTextField(20);

            JLabel sectionLabel = new JLabel("Section:");
            sectionField = new JTextField(20);

            JLabel motherNameLabel = new JLabel("Mother's Name:");
            motherNameField = new JTextField(20);

            JLabel fatherNameLabel = new JLabel("Father's Name:");
            fatherNameField = new JTextField(20);

            JLabel parentPhoneNumberLabel = new JLabel("Parent's Phone Number:");
            parentPhoneNumberField = new JTextField(20);

            JLabel gradeLabel = new JLabel("Grade:");
            gradeField = new JTextField(20);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    int rollNumber = Integer.parseInt(rollNumberField.getText());
                    String className = classNameField.getText();
                    String section = sectionField.getText();
                    String motherName = motherNameField.getText();
                    String fatherName = fatherNameField.getText();
                    String parentPhoneNumber = parentPhoneNumberField.getText();
                    int grade = Integer.parseInt(gradeField.getText());

                    sms.addStudent(new Student(name, rollNumber, className, section, motherName, fatherName, parentPhoneNumber, grade));
                    JOptionPane.showMessageDialog(StudentManagementGUI.this, "Student added successfully.");
                    clearAddPanel();
                }
            });
            JButton backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Go back to the choose option screen
                    setContentPane(mainPanel);
                    revalidate();
                    repaint();
                }
            });

            addPanel.add(nameLabel);
            addPanel.add(nameField);
            addPanel.add(rollNumberLabel);
            addPanel.add(rollNumberField);
            addPanel.add(classNameLabel);
            addPanel.add(classNameField);
            addPanel.add(sectionLabel);
            addPanel.add(sectionField);
            addPanel.add(motherNameLabel);
            addPanel.add(motherNameField);
            addPanel.add(fatherNameLabel);
            addPanel.add(fatherNameField);
            addPanel.add(parentPhoneNumberLabel);
            addPanel.add(parentPhoneNumberField);
            addPanel.add(gradeLabel);
            addPanel.add(gradeField);
//            addPanel.add(Box.createVerticalGlue());
//            addPanel.add(saveButton);
//            addPanel.add(backButton);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.add(saveButton);
            buttonPanel.add(backButton);
            addPanel.add(Box.createVerticalGlue());
            addPanel.add(buttonPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(addPanel);
        setContentPane(scrollPane);
        revalidate();
        repaint();
    }

    private void clearAddPanel() {
        nameField.setText("");
        rollNumberField.setText("");
        classNameField.setText("");
        sectionField.setText("");
        motherNameField.setText("");
        fatherNameField.setText("");
        parentPhoneNumberField.setText("");
        gradeField.setText("");
    }

    
    private int getRollNumberFromUser(String message) {
        String input = JOptionPane.showInputDialog(StudentManagementGUI.this, message);
        if (input == null || input.isEmpty()) {
            return -1;
        }
        try {
            int rollNumber = Integer.parseInt(input);
            Student student = sms.searchStudent(rollNumber);
            if (student != null) {
                StringBuilder details = new StringBuilder();
                details.append("Student found:\n");
                details.append("Name: ").append(student.getName()).append("\n");
                details.append("Roll Number: ").append(student.getRollNumber()).append("\n");
                details.append("Class: ").append(student.getClassName()).append("\n");
                details.append("Section: ").append(student.getSection()).append("\n");
                details.append("Mother's Name: ").append(student.getMotherName()).append("\n");
                details.append("Father's Name: ").append(student.getFatherName()).append("\n");
                details.append("Parent's Phone Number: ").append(student.getParentPhoneNumber()).append("\n");
                details.append("Grade: ").append(student.getGrade()).append("\n");

                JOptionPane.showMessageDialog(StudentManagementGUI.this, details.toString(), "Student Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(StudentManagementGUI.this, "Student not found.");
            }
            return rollNumber;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(StudentManagementGUI.this, "Invalid input! Please enter a valid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }


    
    
    private void displayAllStudents() {
        if (displayPanel == null) {
            displayPanel = new JPanel();
            displayPanel.setLayout(new BorderLayout());

            String[] columnNames = {"Name", "Roll Number", "Class", "Section", "Mother's Name", "Father's Name", "Parent's Phone Number", "Grade"};

            List<Student> students = sms.displayAllStudents();
            Object[][] data = new Object[students.size()][8];
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                data[i][0] = student.getName();
                data[i][1] = student.getRollNumber();
                data[i][2] = student.getClassName();
                data[i][3] = student.getSection();
                data[i][4] = student.getMotherName();
                data[i][5] = student.getFatherName();
                data[i][6] = student.getParentPhoneNumber();
                data[i][7] = student.getGrade();
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            displayPanel.add(scrollPane, BorderLayout.CENTER);
        }

        setContentPane(displayPanel);
        revalidate();
        repaint();
    }

}
