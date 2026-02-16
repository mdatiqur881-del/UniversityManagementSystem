import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Student> students = FileManager.loadStudents();
    static List<Faculty> faculties = FileManager.loadFaculty();
    static Admin admin = new Admin();

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n===== UNIVERSITY SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if(choice == 1) adminLogin();
            else {
                FileManager.saveStudents(students);
                FileManager.saveFaculty(faculties);
                System.out.println("System Closed.");
                System.exit(0);
            }
        }
    }

    public static void adminLogin() {
        sc.nextLine();
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        if(admin.login(u, p)) adminMenu();
        else System.out.println("Invalid Login!");
    }

    public static void adminMenu() {
        while(true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Manage Student");
            System.out.println("3. Manage Faculty");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            if(ch == 1) {
                sc.nextLine();
                System.out.print("Student ID: "); String id = sc.nextLine();
                System.out.print("Student Name: "); String name = sc.nextLine();
                students.add(new Student(id,name));
                System.out.println("Student Added!");
            } else if(ch == 2) manageStudent();
            else if(ch == 3) manageFaculty();
            else break;
        }
    }

    public static void manageStudent() {
        sc.nextLine();
        System.out.print("Enter Student ID: "); String id = sc.nextLine();
        Student s = students.stream().filter(st -> st.getId().equals(id)).findFirst().orElse(null);
        if(s == null){ System.out.println("Student not found!"); return; }
        studentMenu(s);
    }

    public static void studentMenu(Student s) {
        while(true) {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Create Semester");
            System.out.println("2. Add Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Complete Semester");
            System.out.println("5. View Transcript");
            System.out.println("6. Save Transcript");
            System.out.println("7. Back");
            System.out.print("Choose: "); int ch = sc.nextInt();
            try {
                if(ch==1){ sc.nextLine(); System.out.print("Semester Name: "); String name=sc.nextLine(); s.createSemester(name); }
                else if(ch==2){ sc.nextLine(); System.out.print("Course Code: "); String code=sc.nextLine();
                    System.out.print("Course Name: "); String name=sc.nextLine();
                    System.out.print("Credit: "); int cr=sc.nextInt();
                    System.out.print("Grade Point: "); double gp=sc.nextDouble();
                    s.addCourseToCurrent(new Course(code,name,cr,gp));
                }
                else if(ch==3){ System.out.print("Course index to drop: "); int idx=sc.nextInt(); s.dropCourseFromCurrent(idx);}
                else if(ch==4){ s.completeCurrentSemester(); }
                else if(ch==5){ s.printFullTranscript(); }
                else if(ch==6){ s.saveTranscriptToFile(); System.out.println("Transcript saved!");}
                else break;
            } catch(Exception e){ System.out.println("Error: "+e.getMessage()); }
        }
    }

    public static void manageFaculty() {
        while(true) {
            System.out.println("\n===== FACULTY MENU =====");
            System.out.println("1. Add Faculty");
            System.out.println("2. Delete Faculty");
            System.out.println("3. View Faculty List");
            System.out.println("4. Assign Advisor to Student");
            System.out.println("5. Back");
            System.out.print("Choose: "); int ch=sc.nextInt(); sc.nextLine();
            if(ch==1){ System.out.print("Faculty ID: "); String fid=sc.nextLine();
                System.out.print("Faculty Name: "); String fname=sc.nextLine();
                System.out.print("Designation: "); String des=sc.nextLine();
                faculties.add(new Faculty(fid,fname,des)); System.out.println("Faculty added!"); }
            else if(ch==2){ System.out.print("Faculty ID to delete: "); String fid=sc.nextLine();
                faculties.removeIf(f->f.getId().equals(fid)); System.out.println("Deleted!"); }
            else if(ch==3){ System.out.println("Faculty List:"); for(Faculty f: faculties) System.out.println(f);}
            else if(ch==4){
                System.out.print("Student ID: "); String sid=sc.nextLine();
                Student s = students.stream().filter(st->st.getId().equals(sid)).findFirst().orElse(null);
                if(s==null){ System.out.println("Student not found!"); continue; }
                System.out.print("Faculty ID: "); String fid=sc.nextLine();
                Faculty f = faculties.stream().filter(fac->fac.getId().equals(fid)).findFirst().orElse(null);
                if(f==null) System.out.println("Faculty not found!");
                else { s.assignAdvisor(f); System.out.println("Advisor assigned!"); }
            } else break;
        }
    }
}
