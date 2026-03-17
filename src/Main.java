import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        students.put("S001", new Student("Samat", 3.0, 20));
        students.put("S002", new Student("Bekbolsum", 3.8, 21));
        students.put("S003", new Student("Aktan", 3.5, 19));
        students.put("S004", new Student("Alaman", 3.9, 22));
        students.put("S005", new Student("Azamm", 3.2, 20));
        students.put("S006", new Student("Pico", 3.8, 21));

        System.out.println("=== Task 1: All Students ===");
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        String searchId = "S003";
        Student foundStudent = students.get(searchId);
        System.out.println("\nFind by ID " + searchId + ": " + foundStudent);

        String removeId = "S005";
        Student removedStudent = students.remove(removeId);
        System.out.println("Removed by ID " + removeId + ": " + removedStudent);

        String updateId = "S001";
        Student studentToUpdate = students.get(updateId);
        if (studentToUpdate != null) {
            studentToUpdate.setGpa(3.5);
            System.out.println("Updated GPA for " + updateId + ": " + studentToUpdate);
        }

        // ====================== SORTING (IMPORTANT) ======================
        List<Student> sortedByGpa = new ArrayList<>(students.values());

        System.out.println("\n=== Sorting: By GPA (natural) ===");
        sortedByGpa.sort(null);
        for (Student student : sortedByGpa) {
            System.out.println(student);
        }

        System.out.println("\n=== Sorting: By Name ===");
        List<Student> sortedByName = new ArrayList<>(students.values());
        sortedByName.sort(new NameComparator());
        for (Student student : sortedByName) {
            System.out.println(student);
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        List<Student> topByGpa = new ArrayList<>(students.values());
        topByGpa.sort(new GpaDescendingComparator());
        for (int i = 0; i < Math.min(3, topByGpa.size()); i++) {
            System.out.println((i + 1) + ". " + topByGpa.get(i));
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        HashMap<Double, List<String>> gpaGroups = new HashMap<>();
        for (Student student : students.values()) {
            gpaGroups.computeIfAbsent(student.getGpa(), key -> new ArrayList<>()).add(student.getName());
        }
        for (Map.Entry<Double, List<String>> entry : gpaGroups.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("GPA " + entry.getKey() + " -> " + String.join(", ", entry.getValue()));
            }
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        Course math = new Course("Mathematics");
        Course programming = new Course("Programming");
        Course physics = new Course("Physics");

        courseMap.put(math, new ArrayList<>());
        courseMap.put(programming, new ArrayList<>());
        courseMap.put(physics, new ArrayList<>());

        addStudentToCourse(courseMap, math, students.get("S001"));
        addStudentToCourse(courseMap, math, students.get("S004"));
        addStudentToCourse(courseMap, programming, students.get("S002"));
        addStudentToCourse(courseMap, programming, students.get("S003"));
        addStudentToCourse(courseMap, physics, students.get("S004"));
        addStudentToCourse(courseMap, physics, students.get("S006"));

        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            System.out.println(entry.getKey());
            for (Student student : entry.getValue()) {
                System.out.println("  - " + student);
            }
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        Comparator<Student> byGpaDescThenName = new GpaDescThenNameComparator();

        List<Student> challengeSorted = new ArrayList<>(students.values());
        challengeSorted.sort(byGpaDescThenName);
        for (Student student : challengeSorted) {
            System.out.println(student);
        }
    }

    private static void addStudentToCourse(HashMap<Course, List<Student>> courseMap, Course course, Student student) {
        if (student != null) {
            courseMap.get(course).add(student);
        }
    }

    private static class NameComparator implements Comparator<Student> {
        @Override
        public int compare(Student first, Student second) {
            return first.getName().compareTo(second.getName());
        }
    }

    private static class GpaDescendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student first, Student second) {
            return Double.compare(second.getGpa(), first.getGpa());
        }
    }

    private static class GpaDescThenNameComparator implements Comparator<Student> {
        @Override
        public int compare(Student first, Student second) {
            int gpaComparison = Double.compare(second.getGpa(), first.getGpa());
            if (gpaComparison != 0) {
                return gpaComparison;
            }
            return first.getName().compareTo(second.getName());
        }
    }
}



