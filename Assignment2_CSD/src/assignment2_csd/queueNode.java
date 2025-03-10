
public class queueNode {
    public courseNode course;
    public queueNode next;

    public queueNode() {
    }

    public queueNode(courseNode course) {
        this.course = course;
        this.next = null;
    }
}
