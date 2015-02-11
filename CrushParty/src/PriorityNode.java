import java.util.Random;

/**
 * Created by Seth on 2/2/15.
 */
public class PriorityNode {
    private Random rand = new Random();
    private Student student;
    private double score;
    private PriorityNode next;
    private PriorityNode prev;

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

    public PriorityNode insert(PriorityNode nodeToAdd) {
        if (nodeToAdd.getScore() <= score) {
            nodeToAdd.setNext(this);
            nodeToAdd.setPrev(this.getPrev());
            this.getPrev().setNext(nodeToAdd);
            this.setPrev(nodeToAdd);
            return nodeToAdd;
        } else {
            setNext(getNext().insert(nodeToAdd));
            return this;
        }
    }

    public PriorityNode remove() {
        if (hasNext()) {
            this.next.setPrev(prev);
        }
        if (hasPrev()) {
            this.prev.setNext(next);
        }
        return this;
    }

    public boolean hasPrev() {
        return !(prev == null);
    }

    public PriorityNode getNext() {
        return next;
    }

    public PriorityNode getPrev() {
        return prev;
    }

    public void setPrev(PriorityNode prev) {
        this.prev = prev;
    }

    public PriorityNode randomStudent(int i) {

        if (i <= 0) {
            return this;
        } else {
            return next.randomStudent(--i);
        }
    }
}
