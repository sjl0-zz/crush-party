import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Seth on 1/24/15.
 * Data Structure which holds all students.
 */
public class StudentKeeper {

    private Double[][] scoreMatrix;
    private Map<Integer, Student> studentMap;
    private int nextStudentIndex = 0;

    public StudentKeeper() {
        studentMap = new HashMap<>();
    }

    /**
     * Adds a student to the student keeper.
     */
    public void add(Student studentToAdd) {
        studentMap.put(nextStudentIndex, studentToAdd);
        studentToAdd.setIndex(nextStudentIndex++);
    }

    //creates a matrix of doubles representing scores between students
    public void createMatrix() {
        int numStudents = studentMap.size();
        scoreMatrix = new Double[numStudents][numStudents];
        for(int i = 0; i < numStudents; i++){
            for(int j = 0; j <= i; j++){
                Student studentI = studentMap.get(i);
                Student studentJ = studentMap.get(j);
                double scoreIJ = studentI.score(studentJ);
                studentI.addMatch(studentJ, scoreIJ);
                studentI.addMatch(studentJ, scoreIJ);
                scoreMatrix[i][j] = scoreIJ;
                scoreMatrix[j][i] = scoreIJ;
            }
        }
        System.out.println();
    }

}
