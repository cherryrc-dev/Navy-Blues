import java.util.Scanner;


public class StudentGradeManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Management System!");

        System.out.println("Do you want to process student grades individually? (yes/no)");
        String initialResponse = sc.nextLine();

        if (initialResponse.equalsIgnoreCase("yes")) {
            boolean tryAgain = true;
        
            do {
                processStudentGrades(sc);
                System.out.println("Would you like to process another student? (yes/no)");
                String response = sc.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    tryAgain = false;
                }
            } while (tryAgain);

        } else {
            processStudentGrades(sc);
            System.out.println();
            System.exit(0);
        }
        
        sc.close();
    }
    
    private static void processStudentGrades(Scanner sc) {

        String[] name = new String[10];
        int[] mathGrades = new int[10];
        int[] sciGrades = new int[10];
        int[] engGrades = new int[10];

        System.out.println("Enter student names one by one: ");
        for (int i = 0; i < 10; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            name[i] = sc.nextLine();
        }
        System.out.println("Enter Math grades for each student: ");
        for (int i = 0; i < 10; i++) {
            System.out.print("Math grade for " + name[i] + ": ");
            mathGrades[i] = sc.nextInt();
        }
        System.out.println("Enter Science grades for each student: ");
        for (int i = 0; i < 10; i++) {
            System.out.print("Science grade for " + name[i] + ": ");
            sciGrades[i] = sc.nextInt();
        }
        System.out.println("Enter English grades for each student: ");
        for (int i = 0; i < 10; i++) {
            System.out.print("English grade for " + name[i] + ": ");
            engGrades[i] = sc.nextInt();
        }
        
        System.out.println("=== Student Grades Management System ===");
        for (int i = 0; i < 10; i++) {
            double average = (mathGrades[i] + sciGrades[i] + engGrades[i]) / 3.0;
            System.out.println("Student Name: " + name[i]);
            System.out.println("Math Grade: " + mathGrades[i]);
            System.out.println("Science Grade: " + sciGrades[i]);
            System.out.println("English Grade: " + engGrades[i]);
            System.out.println("--- Results ---");
            System.out.println("Average Grade: " + average);
            System.out.println("Status: " + (average >= 75 ? "Pass" : "Fail"));
            System.out.println();
        }
    }
}