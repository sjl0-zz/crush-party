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

    public Student(String[] studentInfo, Question[] questions) {
        // set name, gender, etc
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

    public static void main (String[] args) {

        Student testPerson = new Student(null, null);
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

}
