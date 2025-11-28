import java.awt.*;
import java.util.ArrayList;     
import javax.swing.*;   

// ---------------- STUDENT CLASS ----------------
class Student {
    String name;
    int math, science, english;

    Student(String name, int math, int science, int english) {
        this.name = name;
        this.math = math;
        this.science = science;
        this.english = english;
    }

    double getAverage() {
    return Math.round((math + science + english) / 3.0 * 100.0) / 100.0;
}


    String getStatus() {
        return getAverage() >= 75 ? "Pass" : "Fail";
    }
}

// ---------------- MAIN GUI CLASS ----------------
public class StudentGradeFrame extends JFrame {

    ArrayList<Student> students = new ArrayList<>();

    public StudentGradeFrame() {
        setTitle("Student Grade Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ----------- UI LAYOUT -----------
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnAdd = new JButton("Add Student");
        JButton btnShow = new JButton("Show All Students");
        JButton btnSearch = new JButton("Search Student");
        JButton btnEdit = new JButton("Edit Student");
        JButton btnDelete = new JButton("Delete Student");
        JButton btnExit = new JButton("Exit");

        add(btnAdd);
        add(btnShow);
        add(btnSearch);
        add(btnEdit);
        add(btnDelete);
        add(btnExit);

        // ----------- BUTTON EVENTS -----------
        btnAdd.addActionListener(e -> addStudent());
        btnShow.addActionListener(e -> showStudents());
        btnSearch.addActionListener(e -> searchStudent());
        btnEdit.addActionListener(e -> editStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnExit.addActionListener(e -> System.exit(0));
    }

    // ---------------- ADD ----------------
    void addStudent() {
        JTextField nameField = new JTextField();
        JTextField mathField = new JTextField();
        JTextField sciField = new JTextField();
        JTextField engField = new JTextField();

        Object[] message = {
            "Name:", nameField,
            "Math:", mathField,
            "Science:", sciField,
            "English:", engField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                students.add(new Student(
                    nameField.getText(),
                    Integer.parseInt(mathField.getText()),
                    Integer.parseInt(sciField.getText()),
                    Integer.parseInt(engField.getText())
                ));

                JOptionPane.showMessageDialog(null, "Student added!");

                // small hidden Easter egg: open a fun link when a special name is
                // added (case-insensitive)
                EasterEgg(nameField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input.");
            }
        }
    }

    // ---------------- SHOW ----------------
    void showStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students available.");
            return;
        }

        String[] columns = { "Name", "Math", "Sci", "Eng", "Avg", "Status" };
        String[][] data = new String[students.size()][6];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            data[i][0] = s.name;
            data[i][1] = String.valueOf(s.math);
            data[i][2] = String.valueOf(s.science);
            data[i][3] = String.valueOf(s.english);
            data[i][4] = String.valueOf(s.getAverage());
            data[i][5] = s.getStatus();
        }

        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scroll, "Student List", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------------- SEARCH ----------------
    void searchStudent() {
        String name = JOptionPane.showInputDialog("Enter name:");
        Student s = findStudent(name);

        if (s == null) {
            JOptionPane.showMessageDialog(null, "Not found.");
            return;
        }

        // trigger Easter egg when the searched name matches
        EasterEgg(s.name);

        JOptionPane.showMessageDialog(null,
            "Name: " + s.name +
            "\nMath: " + s.math +
            "\nScience: " + s.science +
            "\nEnglish: " + s.english +
            "\nAverage: " + s.getAverage() +
            "\nStatus: " + s.getStatus()
        );
    }

    // ---------------- EDIT ----------------
    void editStudent() {
        String name = JOptionPane.showInputDialog("Enter name to edit:");
        Student s = findStudent(name);

        if (s == null) {
            JOptionPane.showMessageDialog(null, "Student not found.");
            return;
        }

        s.math = Integer.parseInt(JOptionPane.showInputDialog("New Math:", s.math));
        s.science = Integer.parseInt(JOptionPane.showInputDialog("New Science:", s.science));
        s.english = Integer.parseInt(JOptionPane.showInputDialog("New English:", s.english));

        JOptionPane.showMessageDialog(null, "Updated!");
    }

    // ---------------- DELETE ----------------
    void deleteStudent() {
        String name = JOptionPane.showInputDialog("Enter name to delete:");
        Student s = findStudent(name);

        if (s == null) {
            JOptionPane.showMessageDialog(null, "Not found.");
            return;
        }

        students.remove(s);
        JOptionPane.showMessageDialog(null, "Deleted!");
    }

    // ---------------- UTIL ----------------
    Student findStudent(String name) {
        for (Student s : students)
            if (s.name.equalsIgnoreCase(name))
                return s;
        return null;
    }

    // easter egg lol
    void EasterEgg(String name) {
        if (name == null) return;
        if (!"MajinBlueRat".equalsIgnoreCase(name.trim())) return;

        String url = "https://youtu.be/iupPG8x_Zfo?si=vpMChMWD_nLEOXBU"; 

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new java.net.URI(url));
            } else {
                JOptionPane.showMessageDialog(this, "Open the following link:\n" + url);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Open this link please.\n" + url + "\nError: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentGradeFrame().setVisible(true);
    }
}
