import java.util.*;

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
    private Map<Gender, Set<String>> interests;
    private List<Set<Gender>> outputGenderPreferences;
    private List<Set<String>> outputActivityPreferences;
    private String email;
    private String netId;

    /**
     * Array of priority queues representing matches.
     */
    private PriorityNode bestMatches;
    private PriorityNode bestMatchesTail;


    /**
     * List of length 6 representing the 6 output lists (3 best, 3 worst)
     * Each inner list is of len 10 representing matches. If not 10, then
     * have a "null" student?
     */
    private List<List<Student>> matches;
    private List<List<Double>> percentages;
    private List<String> listDescriptions;


    private int[] questionLocations = new int[]{5,6,7,8,9,14,16,17,18,19,20,21,23,22};

    private int[] answerScores;
    private String[] studentInfo;

    public Student() {
    }

    /**
     *
     * @param studentInfo An string array representing a student's input into the Google form.
     */
    public Student(String[] studentInfo) {
        this.matches = new ArrayList<>();
        this.percentages = new ArrayList<>();
        this.listDescriptions = new ArrayList<>();

        this.outputGenderPreferences = new ArrayList<>();
        this.outputActivityPreferences = new ArrayList<>();
        this.studentInfo = studentInfo;
        this.answerScores = new int[questionLocations.length];

//        System.out.println("creating new student...");

//        System.out.println("studentInfo =");
//        for (int i = 0; i < studentInfo.length; i++) {
//            System.out.printf("\t%2d: %s\n", i, studentInfo[i]);
//        }

        name = studentInfo[16];
        gender = processGender(studentInfo[1]);
        year = studentInfo[32];
        college = studentInfo[5];
        major = studentInfo[4];
        email = studentInfo[31];
        netId = email.split("@")[0];

//        System.out.println("    name = " + name);
//        System.out.println("    gender = " + gender);
//        System.out.println("    year = " + year);
//        System.out.println("    college = " + college);
//        System.out.println("    major = " + major);

        for (int i = 0; i < questionLocations.length; i++) {
            answerScores[i] = CrushParty.answerScore(studentInfo[questionLocations[i] + 1]);
        }
        bestMatches = NullPriorityNode.initList();
        bestMatchesTail = bestMatches;
        this.interests = new HashMap<>();
        interests.put(Gender.FEMALE, processInterests(studentInfo[26]));
        interests.put(Gender.MALE, processInterests(studentInfo[25]));
        interests.put(Gender.NONBINARY, processInterests(studentInfo[2]));
        createOutputLists(new String[]{studentInfo[3], studentInfo[11], studentInfo[12]}, new String[]{studentInfo[27], studentInfo[28], studentInfo[29]}, new Boolean[]{checkBool(studentInfo[13]), checkBool(studentInfo[14]), false});
        System.out.println(this);
    }

    // should be called 6 times (3 best 3 worst, in that order)
    public void addList (ArrayList<Student> matchesIn, String listDescriptionIn) {

        matches.add(matchesIn);
        listDescriptions.add(listDescriptionIn);

    }

    public void setIndex(int i) {
        idx = i;
    }

    public List<Student> getMatchesForList (int index) {
        return matches.get(index);
    }

    public List<Double> getPercentagesForList (int index) {
        return percentages.get(index);
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

        Student testPerson = new Student(null, null, 100);
        testPerson.name = "Nicholas Hanson-Holtry";
        testPerson.gender = Gender.MALE;
        testPerson.year = "2016";
        testPerson.college = "Sid Rich";
        testPerson.major = "Comp Sci";

        testPerson.matches = new ArrayList<>();
        testPerson.percentages = new ArrayList<>();
        testPerson.listDescriptions = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            testPerson.matches.add(new ArrayList<>());
            testPerson.percentages.add(new ArrayList<>());
            for (int i = 0; i < 10; i++) {
                testPerson.matches.get(j).add(testPerson);
                testPerson.percentages.get(j).add(0.5);
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

    public void addMatch(Student match, double score) {
        bestMatches = bestMatches.insert(new PriorityNode(match, score));
    }

    public List<List<Student>> getTop10(Set<Gender> acceptableGenders, Set<String> requiredInterests) {
        List<Student> topStudents = new ArrayList<>();
        List<Student> bottomStudents = new ArrayList<>();
        List<Double> topPercentage = new ArrayList<>();
        List<Double> bottomPercentage = new ArrayList<>();
        PriorityNode head = bestMatches;
        int headCount = 0;
        int tailCount = 0;
        PriorityNode tail = bestMatchesTail.getPrev();
        double highScore = tail.getScore();

        while (!(head instanceof NullPriorityNode) && headCount < 10) {
            Student possible = head.getStudent();
            if (possible.isFeasible(acceptableGenders, requiredInterests, getGender())) {
                topStudents.add(possible);
                topPercentage.add(calcPercentage(head.getScore(), highScore));
                headCount++;
            }
            head = head.next();
        }
        while (!(tail instanceof NullPriorityNode) && tailCount < 10) {
            Student possible = tail.getStudent();
            if (possible.isFeasible(acceptableGenders, requiredInterests, getGender())) {
                bottomStudents.add(possible);
                bottomPercentage.add(calcPercentage(tail.getScore(), highScore));
                tailCount++;
            }
            tail = tail.getPrev();
        }

        while (headCount < 10) {
            topStudents.add(new NullStudent());
            topPercentage.add(0.0);
            headCount++;
        }

        while (tailCount < 10) {
            bottomStudents.add(new NullStudent());
            bottomPercentage.add(0.0);
            tailCount++;
        }

        String currListDescriptor = formatListDescriptor(acceptableGenders, requiredInterests);

        listDescriptions.add("Best" + currListDescriptor);
        listDescriptions.add("Worst" + currListDescriptor);
        percentages.add(topPercentage);
        percentages.add(bottomPercentage);

        List<List<Student>> retList = new ArrayList<>();
        retList.add(topStudents);
        retList.add(bottomStudents);
        return retList;

    }

    /**
     * Checks feasibility of a match based on indicated interests.
     * @param acceptableGenders Set of genders that potential match is interested in
     * @param requiredInterests Set of interests that potential match is interested in
     * @param potentialGender Gender Identity of potential match
     * @return true if current student identifies as an acceptable gender and is interested in doing
     *          all of the required interests with the
     */
    public boolean isFeasible(Set<Gender> acceptableGenders, Set<String> requiredInterests, Gender potentialGender) {
        return acceptableGenders.contains(this.gender) && this.interests.get(potentialGender).contains(requiredInterests);
    }

    public Set<String> processInterests(String interests) {
        Set<String> interestSet = new HashSet<>();
        String[] interestString = interests.split(",");
        for (String inter : interestString) {
            interestSet.add(inter.trim());
        }
//        System.out.println(interestSet);
        return interestSet;
    }

    public void createOutputLists(String[] genders, String[] activities, Boolean[] nextList) {
        for (int i = 0; i < genders.length; i++) {
            outputGenderPreferences.add(i, processGenders(genders[i]));
            outputActivityPreferences.add(i, processInterests(activities[i]));

            if (nextList[i] != null && !(nextList[i])) {
                break;
            }
        }
    }

    public Set<Gender> processGenders(String genders) {
        Set<Gender> ret = new HashSet<>();
        String[] genderStrings = genders.split(",");
        for (String str : genderStrings) {
            ret.add(processGender(str));
        }
        return ret;
    }

    public Gender processGender(String genderIn) {
        if (genderIn.equals("")) {
            return Gender.NONBINARY;
        }
        Gender res;
        try {
            res = Gender.valueOf(genderIn.trim().toUpperCase().replaceAll("-", ""));
        } catch (Exception e) {
            System.out.println("Supplied Gender: " + genderIn);
            res = Gender.NONBINARY;
        }
        return res;
    }

    public Boolean checkBool(String nextList) {
        if (nextList.trim().compareTo("Yes") == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void prepareForPrinting() {
        System.out.println();
        for (int i = 0; i < outputGenderPreferences.size(); i++) {
            Set<Gender> currGenderPref = outputGenderPreferences.get(i);
            Set<String> currActivityPref = outputActivityPreferences.get(i);
            matches.addAll(getTop10(currGenderPref, currActivityPref));
        }

    }

    public String formatListDescriptor(Set<Gender> acceptableGenders, Set<String> acceptableActivities) {
        String genderString = setToString(acceptableGenders);
        String activityString = setToString(acceptableActivities);
        return genderString + " matches who would like to " + activityString + ".";
    }

    public String setToString(Set inSet) {
        String outputStr = "";
        int setSize = inSet.size();
        if (setSize > 1) {
            Iterator iter = inSet.iterator();
            int i = 0;
            while (iter.hasNext() && i < setSize - 1) {
                outputStr += iter.next().toString().toLowerCase() + ", ";
                i++;
            }
            outputStr = outputStr.substring(0, outputStr.length() - 2);
            outputStr += " and " + iter.next().toString().toLowerCase();
        } else {
            outputStr = inSet.iterator().next().toString().toLowerCase();
        }
        return outputStr;
    }

    private double calcPercentage(double currScore, double highScore) {
        if (currScore > highScore) {
            return 0.0;
        }
        return ((highScore - currScore) * 100.0) / highScore;
    }

}
