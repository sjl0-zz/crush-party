import java.util.ArrayList;

/**
 * Created by Seth on 1/24/15.
 *
 */
public class Student {

    private int idx;

    private String name;
    private Gender gender;
    private String year;
    private String college;
    private String major;

    private ArrayList<ArrayList<Student>> matches;
    private ArrayList<String> listDescriptions;

    private int[] questionLocations = new int[]{5,6,7,8,9,14,16,17,18,19,20,21,23,22};

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
        this.answerScores = new int[questionLocations.length];

        name = studentInfo[16];
        gender = Gender.valueOf(studentInfo[1].toUpperCase().replaceAll("-", ""));
        major = studentInfo[4];
        college = studentInfo[5];

        for (int i = 0; i < questionLocations.length; i++) {
            answerScores[i] = CrushParty.answerScore(studentInfo[questionLocations[i] + 1]);
        }
    }

    // should be called 6 times (3 best 3 worst, in that order)
    public void addList (ArrayList<Student> matchesIn, String listDescriptionIn) {

        matches.add(matchesIn);
        listDescriptions.add(listDescriptionIn);

    }

    public void setIndex(int i) {
        idx = i;
    }

    public ArrayList<Student> getMatchesForList (int index) {
        return matches.get(index);
    }

    public String getDescriptionForList (int index) {
        return listDescriptions.get(index);
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getYear() {
        return year;
    }

    public String getCollege() {
        return college;
    }

    public String getMajor() {
        return major;
    }

    /*
    public static void main (String[] args) {

        Student testPerson = new Student(null, null, 0);
        testPerson.name = "Nicholas Hanson-Holtry";
        testPerson.gender = Gender.MALE;
        testPerson.year = "2016";
        testPerson.college = "Sid Rich";
        testPerson.major = "Comp sci";

        testPerson.matches = new ArrayList<>();
        testPerson.listDescriptions = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            testPerson.matches.add(new ArrayList<>());
            for (int i = 0; i < 10; i++) {
                testPerson.matches.get(j).add(testPerson);
            }
            testPerson.listDescriptions.add("description " + j);
        }

        ResultsPrinter.printResults(testPerson);

        System.out.println("done!");

    }
    */

    public double score(Student other) {
        double ans = 1.0;
        int[] otherAnswers = other.getAnswerScores();
        for (int i = 0; i < otherAnswers.length; i++) {
            ans += Math.pow((otherAnswers[i] - answerScores[i]), 2);
        }
        return Math.sqrt(ans);
    }

    public int[] getAnswerScores() {
        return answerScores;
    }

}
