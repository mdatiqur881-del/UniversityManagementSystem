import java.io.*;
import java.util.*;

public class Student extends User implements Serializable {
    private List<Semester> semesters = new ArrayList<>();
    private Semester currentSemester;
    private Faculty advisor;

    public Student(String id, String name) { super(id, name); }

    // Semester methods
    public void createSemester(String semName) { currentSemester = new Semester(semName); }
    public void addCourseToCurrent(Course c) {
        if(currentSemester != null) currentSemester.addCourse(c);
    }
    public void dropCourseFromCurrent(int index) throws Exception {
        if(currentSemester != null) currentSemester.dropCourse(index);
        else throw new Exception("No current semester!");
    }
    public void completeCurrentSemester() {
        if(currentSemester != null) {
            semesters.add(currentSemester);
            currentSemester = null;
        }
    }

    // Advisor
    public void assignAdvisor(Faculty f) { this.advisor = f; }
    public Faculty getAdvisor() { return advisor; }

    // CGPA
    public double calculateCGPA() {
        int totalCredit = 0;
        double totalPoints = 0;
        for(Semester s : semesters) {
            for(Course c : s.getCourses()) {
                totalCredit += c.getCredit();
                totalPoints += c.getCredit() * c.getGradePoint();
            }
        }
        if(totalCredit == 0) return 0;
        return totalPoints / totalCredit;
    }

    // Transcript
    public void printFullTranscript() {
        System.out.println("\n===== Transcript =====");
        System.out.println("Student: " + name);
        if(advisor != null) System.out.println("Advisor: " + advisor.getName() + " (" + advisor.getDesignation() + ")");
        for(Semester s : semesters) {
            System.out.println("Semester: " + s.getName());
            for(Course c : s.getCourses()) System.out.println(c);
            System.out.println("GPA: " + s.calculateGPA() + "\n");
        }
        System.out.println("CGPA: " + calculateCGPA());
        System.out.println(calculateCGPA() < 2.0 ? "Status: Probation" : "Status: Good Standing");
    }

    public void saveTranscriptToFile() {
        try(PrintWriter pw = new PrintWriter(new FileWriter(id + "_transcript.txt"))) {
            pw.println("Student: " + name);
            if(advisor != null) pw.println("Advisor: " + advisor.getName() + " (" + advisor.getDesignation() + ")");
            for(Semester s : semesters) {
                pw.println("Semester: " + s.getName());
                for(Course c : s.getCourses()) pw.println(c);
                pw.println("GPA: " + s.calculateGPA() + "\n");
            }
            pw.println("CGPA: " + calculateCGPA());
        } catch(IOException e) { System.out.println("Error saving transcript!"); }
    }
}
