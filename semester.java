import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Semester implements Serializable {
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Semester(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<Course> getCourses() { return courses; }

    public void addCourse(Course c) { courses.add(c); }
    public void dropCourse(int index) throws Exception {
        if(index < 0 || index >= courses.size()) throw new Exception("Invalid course index!");
        courses.remove(index);
    }

    public double calculateGPA() {
        int totalCredit = 0;
        double totalPoints = 0;
        for(Course c : courses) {
            totalCredit += c.getCredit();
            totalPoints += c.getCredit() * c.getGradePoint();
        }
        if(totalCredit == 0) return 0;
        return totalPoints / totalCredit;
    }
}
