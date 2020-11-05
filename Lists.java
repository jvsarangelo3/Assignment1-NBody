// Lists interface
public interface Lists<T> {
    // creating functions for each list type (arraylist or linked list)
    // both will have a get, add, and size methods
    T get(int index);
    boolean add(T value);
    int size();
}

// creating arraylist class that implements the Lists functions
class ArrayList<T> implements Lists<T> {
    // creating an array of type T
    T[] array;
    // variable for the size of array
    int size;

    // constructor
    public ArrayList() {
        // create array of type T with a size of 15 spaces
        array = (T[]) new Object[15];
        // initialize size to 0
        size = 0;
    }

    // function to return the size
    public int size() {
        return size;
    }

    // function to get the contents at a certain index
    public T get(int index) {
        // if index is less than 0 or greater than the size
        // the list is empty/invalid index
        if(index < 0 && index >= size) {
            System.out.println("Invalid index.");
        }
        // returns the contents at that index
        return array[index];
    }

    // adds values to arraylist
    public boolean add(T value) {
        // if the size is same as the length of the array
        // just copy the array
        if(size == array.length) {
            copyArray();
        }
        // set the array at that "size" (or index) to that value
        array[size] = value;
        // increment size
        size++;
        // return true
        return true;
    }

    // copy array function
    private void copyArray() {
        // create new_array to be twice the size of the original array
        T[] new_array = (T[]) new Object[array.length * 2];
        // looping thru array
        for(int i = 0; i < array.length; i ++) {
            // adds each value into new_array
            new_array[i] = array[i];
        }
        // setting array to the new_array
        array = new_array;
    }
}

// creating linked lists class that implements the list methods
class LinkedList<T> implements Lists<T> {
    // storing the head of type T
    Node<T> head;
    // variable for the size of list
    int size;
    // node type class
    private class Node<T> {
        // creating variable data to store the data of type T
        T data;
        // creating the next, left, right variables to keep track of
        Node<T> next, left, right;
        // node functions to set left, right, and data values
        public Node(T value, Node<T> l, Node<T> r) {
            left = l;
            right = r;
            this.data = value;
        }
        // setting this node to the value, left and right are null
        public Node(T value) {
            this(value, null, null);
        }
    }

    // linked list constructor
    public LinkedList() {
        // setting head to null and size to 0
        head = null;
        size = 0;
    }

    // function to return the size of linked list
    public int size() {
        return size;
    }

    // get function to return the data at the current node
    public T get(int index) {
        // if the index is less than 0 and the index is greater/equal to the size
        // print it's an invalid index
        if(index < 0 && index >= size) {
            System.out.println("Invalid index.");
        }
        // create a current type node T and set it to the head
        Node<T> curr = head;
        // loop until the index is reached and set that current to the next
        // index i is one behind,
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }
        // return the data at that current value
        return curr.data;
    }

    // adds values to linked list
    public boolean add(T value) {
        // if the head is null
        if(head == null) {
            // then set the head to a new node with that value
            head = new Node<>(value);
            // increment the size
            size++;
            // return true;
            return true;
        }
        // else, set the previous to the head
        // to keep track of the previous value
        Node<T> prev = head;
        // loop thru linked list, once it reaches less than one of the size
        for(int i = 0; i < size - 1; i++) {
            // set the previous to the next index
            prev = prev.next;
        }
        // create a temp node
        Node<T> temp = new Node<>(value);
        // set the next value of previous to the temp node
        prev.next = temp;
        // increment the size
        size++;
        // return true
        return true;
    }
}
