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
                processStudentGrades();
                System.out.println("Would you like to process another student? (yes/no)");
                String response = sc.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    tryAgain = false;
                }
            } while (tryAgain);

        } else {
            for (int i = 0; i < 10; i++) {
                processStudentGrades();
                System.out.println();
            }
        }
        
        
        sc.close();
    }
    
    private static void processStudentGrades() {
        String[] name = {"Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah", "Ian", "Jane"};
        int[] mathGrades = {85, 90, 78, 92, 88, 76, 95, 89, 84, 91};
        int[] sciGrades = {85, 90, 78, 92, 88, 76, 95, 89, 84, 91};
        int[] engGrades = {85, 90, 78, 92, 88, 76, 95, 89, 84, 91};
        
        int n = (int)(Math.random() * 10); // Random student index
        double average = (mathGrades[n] + sciGrades[n] + engGrades[n]) / 3.0;

        System.out.println("=== Student Grades Management System ===");
        System.out.println("Student Name: " + name[n]);
        System.out.println("Math Grade: " + mathGrades[n]);
        System.out.println("Science Grade: " + sciGrades[n]);
        System.out.println("English Grade: " + engGrades[n]);
        System.out.println("--- Results ---");
        System.out.println("Student Name: " + name[n]);
        System.out.println("Average Grade: " + average);
        System.out.println("Status: " + (average >= 75 ? "Pass" : "Fail"));
    }
}