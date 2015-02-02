/**
 * Created by Seth on 2/2/15.
 */
public class PriorityNode {
    private Student student;
    private double score;
    private PriorityNode next;
    private PriorityNode prev;

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

    public PriorityNode addNext(PriorityNode nodeToAdd) {
        if (next == null) {
            next = nodeToAdd;
            return this;
        } else if (next.getScore() > nodeToAdd.score) {
            next = next.addNext(nodeToAdd);
            return this;
        } else {
            nodeToAdd.setNext(this);
            return nodeToAdd;
        }
    }

    public PriorityNode insert(PriorityNode nodeToAdd) {
        if (nodeToAdd.getScore() > score) {
            nodeToAdd.setNext(this);
            nodeToAdd.setPrev(this.getPrev());

            if (hasPrev()) {
                this.setPrev(nodeToAdd);
                prev.setNext(nodeToAdd);
            }
            return nodeToAdd;
        } else {
            if (hasNext()) {
                this.next.insert(nodeToAdd);
            } else {
                this.next = nodeToAdd;
                nodeToAdd.setPrev(this);
            }
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
}
