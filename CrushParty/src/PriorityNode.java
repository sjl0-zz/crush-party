/**
 * Created by Seth on 2/2/15.
 */
public class PriorityNode {
    private Student student;
    private double score;
    private PriorityNode next;

    public PriorityNode() {}

    public PriorityNode(Student student, double score) {
        this.student = student;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean hasNext() {
        return !(next == null);
    }

    public PriorityNode next() {
        return next;
    }

    public void setNext(PriorityNode next) {
        this.next = next;
    }

    public void addNext(PriorityNode nodeToAdd) {
        if (next == null) {
            next = nodeToAdd;
        } else if (nodeToAdd.next.getScore() > this.score) {
            next.addNext(nodeToAdd);
        } else {
            nodeToAdd.setNext(this);
        }
    }

}
