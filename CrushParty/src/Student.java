import java.util.Arrays;
import java.util.Map;

/**
 * Created by Seth on 1/24/15.
 *
 */
public class Student {

    // private Name
    private int idx;
    private int[] answerScores;
    private String[] studentInfo;
    private String[] infoCategories;

    /**
     *
     * @param studentInfo An string array representing a student's input into the Google form.
     * @param infoCategories Equal length list representing categories of input
     * @param numQuestions Number of questions answered by students
     */
    public Student(String[] studentInfo, String[] infoCategories, int numQuestions) {
        this.studentInfo = studentInfo;
        this.infoCategories = infoCategories;
        answerScores = new int[numQuestions];
        for (int i = infoCategories.length; i < studentInfo.length; i++) {
            answerScores[i - infoCategories.length] = CrushParty.answerScore(studentInfo[i]);
        }
    }


    // return the score between two students.

    public double score(Student other){
        double score = 0.0;


        // add to score based on distance from other

        return score;
    }

    public void setIndex(int i) {
        idx = i;
    }
}
