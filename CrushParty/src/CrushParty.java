import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Seth on 1/24/15.
 */
public class CrushParty {

    private Scanner surveyScanner;
    //private StudentKeeper studentKeeper;

    public static void main(String args[]) {
        CrushParty crushParty = new CrushParty(args[0]);
        crushParty.start();
        crushParty.readStudents();
        crushParty.matchStudents();
    }

    public CrushParty(String surveyFilename) {
        try {
            surveyScanner = new Scanner(new File(surveyFilename));
        } catch (FileNotFoundException e) {
            new Exception("File could not be opened.");
        }
    }

    public void start() {
        //studentKeeper = new StudentKeeper();
    }

    public void readStudents() {
        while (surveyScanner.hasNextLine()) {
            String nextLine = surveyScanner.nextLine();
            String[] nextLineArray = nextLine.split(",");
            //Student thisStudent = new Student(nextLineArray);
            //studentKeeper.add(thisStudent);
        }
    }

    public void matchStudents() {
        /*
        while (studentKeeper.hasNext()) {
            Student currStudent = studentKeeper.next();
            Set<Student> allStudents = studentKeeper.allStudents();
            for (Student student : allStudents) {
                float matchScore = score(student, currStudent);
            }
        }
        */
    }

}
