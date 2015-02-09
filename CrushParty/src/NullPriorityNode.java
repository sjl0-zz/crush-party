/**
 * Created by Seth on 2/2/15.
 */
public class NullPriorityNode extends PriorityNode {

    public NullPriorityNode() {
        super(new NullStudent(), Integer.MAX_VALUE);
    }

    public static PriorityNode initList() {
        PriorityNode start = new NullPriorityNode();
        start.setNext(start);
        start.setPrev(start);
        return start;
    }
}
