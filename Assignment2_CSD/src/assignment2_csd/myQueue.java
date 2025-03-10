
public class myQueue {
    private queueNode front;
    private queueNode rear;
    private int size;

    public myQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(courseNode course) {
        queueNode newNode = new queueNode(course);
        if (isEmpty()) {
            this.front = newNode;
            this.rear = newNode;
        }
        else {
            rear.next = newNode;
            rear = newNode;

        }
        size++;

    }

    public courseNode dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        courseNode temp = front.course;
        front = front.next;
        size--;
        if (front == null) {
            rear = null;
        }
        return temp;
    }
}
