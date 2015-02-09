import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seth on 1/24/15.
 * Data Structure which holds all students.
 */
public class StudentKeeper {

    private Double[][] scoreMatrix;
    private Map<Integer, Student> studentMap;
    private int nextStudentIndex = 0;
    private static int wants = 0;

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
                if (i != j) {
                    studentI.addMatch(studentJ, scoreIJ);
                    studentJ.addMatch(studentI, scoreIJ);
                }
                scoreMatrix[i][j] = scoreIJ;
                scoreMatrix[j][i] = scoreIJ;
            }
        }
    }

    public void output() {
        for (Student student : studentMap.values()) {
            student.prepareForPrinting();
            ResultsPrinter.printResults(student);
        }
        PriorityNode topTimesMatched = NullPriorityNode.initList();
        for (Student studentAfter : studentMap.values()) {
            topTimesMatched = topTimesMatched.insert(new PriorityNode(studentAfter, studentAfter.getTimesMatched()));
        }
        PriorityNode next = topTimesMatched;
        while (next != topTimesMatched.getPrev()) {
            System.out.println(next.getStudent().getName() + ": " + next.getScore());
            next = next.getNext();
        }
        System.out.println(wants);
    }

    public static void wantsThird() {
        wants++;
    }


}
