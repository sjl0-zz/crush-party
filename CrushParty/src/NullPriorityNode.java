/**
 * Created by Seth on 2/2/15.
 */
public class NullPriorityNode extends PriorityNode {

    public NullPriorityNode() {
        super();
        setScore(0);
    }

    public static PriorityNode initList(int len) {
        PriorityNode start = new NullPriorityNode();
        start.setNext(start);
        start.setPrev(start);
        /**
        int i = len - 1;
        while (i-- > 0) {
            start = start.insert(new NullPriorityNode());
        }
        return start;
         **/
        return start;
    }
}
