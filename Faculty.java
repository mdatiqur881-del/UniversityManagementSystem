import java.io.Serializable;

public class Faculty implements Serializable {
    private String id;
    private String name;
    private String designation;

    public Faculty(String id, String name, String designation) {
        this.id = id;
        this.name = name;
        this.designation = designation;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDesignation() { return designation; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + name + " | " + designation;
    }
}
