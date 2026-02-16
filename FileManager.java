import java.io.*;
import java.util.*;

public class FileManager {
    public static void saveStudents(List<Student> students) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(students);
        } catch(Exception e) { System.out.println("Error saving students!"); }
    }

    public static List<Student> loadStudents() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            return (List<Student>) ois.readObject();
        } catch(Exception e) { return new ArrayList<>(); }
    }

    public static void saveFaculty(List<Faculty> faculties) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("faculty.dat"))) {
            oos.writeObject(faculties);
        } catch(Exception e) { System.out.println("Error saving faculty!"); }
    }

    public static List<Faculty> loadFaculty() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("faculty.dat"))) {
            return (List<Faculty>) ois.readObject();
        } catch(Exception e) { return new ArrayList<>(); }
    }
}
