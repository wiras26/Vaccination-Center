//ref: https://www.youtube.com/watch?v=SMIq13-FZSE
public class LinkedList {
    //Variables
    Node head;//First node in the linked list

    //Method to insert elements at the Start of the linked list
    public void insertAtStart(String Data) {
        Node node = new Node();
        node.Data = Data;
        node.Next = null;
        node.Next = head;
        head = node;
    }

    //This method insert a new element at a specific index in the linked list
    public void insertAt(int Index, String Data) {

        Node node = new Node();//The node to be added
        node.Data = Data;
        node.Next = null;

        if (Index == 0) {
            insertAtStart(Data);
        } else {
            Node n = head;
            // From the beginning of the linked list traversing to the end or the index location
            for (int i = 0; i < Index - 1; i++) {
                n = n.Next;
            }
            node.Next = n.Next;//Set new node's ref to current node's next ref
            n.Next = node;
        }
    }

    //Displays all the elements present in the Linked List
    public void show() {
        Node node = head;
        while (node.Next != null) {
            System.out.println(node.Data);
            node = node.Next;
        }
        System.out.println(node.Data);
    }

    //Method returns the element at a specific index ref: https://github.com/jmartin103/LinkedList/blob/master/LinkedList/src/LinkedList.java
    public String get(int index) {
        if (index <= 0) { //Index must be 0 or higher
            return ("error");
        }
        Node current = head.Next;
        for (int i = 1; i < index; i++) {
            if (current.Next == null)
                throw new ArrayIndexOutOfBoundsException();
            current = current.Next;
        }
        return current.Data;
    }

    //Method deletes the node at the given index ref: https://github.com/dinesh-varyani/ds-algos/blob/master/src/com/hubberspot/dsalgo/list/SinglyLinkedList.java
    public void deleteNode(int index) {

        //Checks if the Linked List is Empty
        if (head == null) {
            System.out.println("Linked List Empty");
            return;
        }

        Node Current = head;

        //Checks if the Head needs to be Removed
        if (index ==0){
            head = Current.Next;
            return;
        }
        //Finds the previous node of the node to be deleted
        for (int i=0; Current!=null && i<index-1; i++)
            Current = Current.Next;

        // If position is more than number of nodes
        if (Current == null || Current.Next == null)
            return;

        // Store pointer to the next of node to be deleted
        Node next = Current.Next.Next;

        Current.Next = next;//Remove the link from the deleted Node

    }
}