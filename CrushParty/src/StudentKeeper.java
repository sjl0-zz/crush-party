import java.util.HashSet;
import java.util.Set;

/**
 * Created by Seth on 1/24/15.
 * Data Structure which holds all students.
 */
public class StudentKeeper {

    /**
     * Adds a student to the student keeper.
     */
    public void add(Student studentToAdd) {

    }

    public boolean hasNext() {
        return true;
    }

    public Student next() {
        return new Student(new String[]{}, new Question[]{});
    }

    public Set<Student> allStudents() {
        return new HashSet<>();
    }

    public  Double[][] scores = new Double[studentMap.size()][studentMap.size()](){

    }
}
