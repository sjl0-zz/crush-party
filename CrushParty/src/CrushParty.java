import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Seth on 1/24/15.
 */
public class CrushParty {

    private Scanner surveyScanner;
    private Scanner answerScanner;
    private static Map<String, Integer> answerMap;
    private StudentKeeper studentKeeper;
    private int n;

    /**
     * Usage:
     * crushParty studentData questionData q
     *     studentData    unqualified name of file which holds matches. Matches should be in the form such that
     *                    reading a row in from the file, data can be mapped to student fields.
     *     questionData   unqualified name of file which holds scoring information for options for questions.
     *                    lists the options and points associated with it
     *     q              number of questions
     * @param args
     */
    public static void main(String args[]) {
        CrushParty crushParty = new CrushParty();
        crushParty.start(args[0], args[1], args[2]);
    }

    public void start(String surveyFilename, String answersFilename, String numQuestions) {
        studentKeeper = new StudentKeeper();
        answerMap = new HashMap<>();
        n = Integer.valueOf(numQuestions);
        try {
            surveyScanner = new Scanner(new File(surveyFilename));
            answerScanner = new Scanner(new File(answersFilename));
        } catch (FileNotFoundException e) {
            new Exception("File could not be opened.");
        }
        processAnswers();
        readStudents();
        matchStudents();
        outputStudents();
    }

    public void processAnswers() {
        while (answerScanner.hasNextLine()) {
            String[] answerString = answerScanner.nextLine().split(",");
            answerMap.put(answerString[0], Integer.valueOf(answerString[1]));
        }
    }

    public void readStudents() {
        while (surveyScanner.hasNextLine()) {
            String nextLine = surveyScanner.nextLine();
            String[] nextLineArray = nextLine.split(",");
            Student thisStudent = new Student(nextLineArray, new String[]{"FN", "..."}, n);
            studentKeeper.add(thisStudent);
        }
    }

    public void matchStudents() {
        studentKeeper.createMatrix();
    }

    public void outputStudents() {

    }

    public static int answerScore(String answer) {
        return answerMap.get(answer);
    }

}
