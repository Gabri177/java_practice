public class MyList {

    Node head;
    Node tail;

    public MyList() {
        head = null;
        tail = null;
    }

    public void addFront(Object obj) {
        Node item = new Node(obj);
        if (head == null && tail == null) {
            head = tail = item;
        } else {
            head.prev = item;
            item.next = head;
            head = item;
        }
    }

    public void addBack (Object obj) {

        Node item = new Node(obj);
        if (head == null && tail == null) {
            head = tail = item;
        } else {
            tail.next = item;
            item.prev = tail;
            tail = item;
        }
    }

    public Object shift(){
        if (head != null) {
            Object temp = head.data;
            head = head.next;
            if (!(head.next != null))
                head.prev = null;
            return temp;
        }
        return null;
    }

    public Object pop() {
        if (tail != null) {
            Object temp = tail.data;
            tail = tail.prev;
            if (!(head.next != null))
                tail.next = null;
            return temp;
        }
        return null;
    }
    public class Node {
        Object data;
        Node next;
        Node prev;

        {
            this.next = null;
            this.prev = null;
        }
        public Node(Object data) {
            this.data = data;
        }
    }
}
