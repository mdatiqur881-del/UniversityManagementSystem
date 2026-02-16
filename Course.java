import java.io.Serializable;

public class Course implements Serializable {
    private String code;
    private String name;
    private int credit;
    private double gradePoint;

    public Course(String code, String name, int credit, double gradePoint) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.gradePoint = gradePoint;
    }

    public int getCredit() { return credit; }
    public double getGradePoint() { return gradePoint; }

    @Override
    public String toString() {
        return code + " | " + name + " | Credit: " + credit + " | Grade: " + gradePoint;
    }
}
