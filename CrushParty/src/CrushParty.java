import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Seth on 1/24/15.
 */
public class CrushParty {

    private BufferedReader surveyScanner;
    private BufferedReader answerScanner;
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
        crushParty.start(args[0], args[1]);
    }

    public void start(String surveyFilename, String answersFilename) {
        studentKeeper = new StudentKeeper();
        answerMap = new HashMap<>();
        //n = Integer.valueOf(numQuestions);
        File surveyFile = new File(surveyFilename);
        File answerFile = new File(answersFilename);

        try {
            surveyScanner = new BufferedReader(new FileReader(surveyFilename));
            System.out.println("successfully made surveyScanner for " + surveyFilename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File '"+surveyFilename+"' could not be opened.");
        }

        try {
            answerScanner = new BufferedReader(new FileReader(answersFilename));
            System.out.println("successfully made answerScanner for " + answersFilename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File '"+answersFilename+"' could not be opened.");
        }

        processAnswers();
        readStudents();
        matchStudents();
        outputStudents();

    }

    public void processAnswers() {
        try {
            while (answerScanner.ready()) {
                String[] nextLine = answerScanner.readLine().split(",");
                String answerStringA = nextLine[0].trim();
                String answerStringB = nextLine[1];
                answerMap.put(answerStringA, Integer.valueOf(answerStringB));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readStudents() {
        try {
            while (surveyScanner.ready()) {
                String nextLine = surveyScanner.readLine();
                String[] nextLineArray = nextLine.split("\t");
                Student thisStudent = new Student(nextLineArray, new String[]{"FN", "..."}, n);
                studentKeeper.add(thisStudent);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
