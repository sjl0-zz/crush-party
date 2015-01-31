import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Seth on 1/24/15.
 */
public class CrushParty {

    private Scanner surveyScanner;
    private StudentKeeper studentKeeper;

    public static void main(String args[]) {
        CrushParty crushParty = new CrushParty();
        crushParty.start(args[0]);
    }

    public void start(String surveyFilename) {
        studentKeeper = new StudentKeeper();
        try {
            surveyScanner = new Scanner(new File(surveyFilename));
        } catch (FileNotFoundException e) {
            new Exception("File could not be opened.");
        }
        readStudents();
        matchStudents();
        outputStudents();
    }

    public void readStudents() {
        while (surveyScanner.hasNextLine()) {
            String nextLine = surveyScanner.nextLine();
            String[] nextLineArray = nextLine.split(",");
            Student thisStudent = new Student(nextLineArray);
            studentKeeper.add(thisStudent);
        }
    }

    public void matchStudents() {

        while (studentKeeper.hasNext()) {
            Student currStudent = studentKeeper.next();
            Set<Student> allStudents = studentKeeper.allStudents();
            for (Student student : allStudents) {
                float matchScore = score(student, currStudent);
            }
        }

    }

    public float score(Student studentA, Student studentB) {

    }

    public void outputStudents() {

    }

}
