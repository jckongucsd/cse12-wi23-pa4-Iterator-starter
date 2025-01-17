                                                                                    /*
  Name: Jason Kong
  Email: jckong@ucsd.edu
  PID: A17351738
  CSE 12 - PA 4
  Professor Cao
  File name: MyLinkedList.java
*/

import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

/**
* class MyLinkedList is a class for our LinkedList implementation
*
*/
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }



    //  Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */

    /** 
    * MyLinkedList() is the no-arg constructor for MyLinkedList
    */
    public MyLinkedList() {
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);

    }

    /** 
    * Accessor to get the size of MyLinkedList
    * @return the size integer 
    */
    @Override
    public int size() {
        return this.size; 
    }

    /** 
    * Accessor to get the a node of MyLinkedList by the index
    * @param  the integer of the index
    * @return the element of the targeted node 
    */
    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }
		// Node curr = this.head.next;
        Node curr = this.head.getNext();
		for(int i=0; i < index; i++) {
			curr = curr.getNext();
		}
		return curr.getElement();
    }

    /** 
    * method to add a node to the MyLinkedList by the index
    * @param the integer of the index
    * @param  the data of the added node
    */
    @Override 
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException("Null Pointer Exception..");
        }
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }

        Node node = new Node(data); //this node is to add.

        if (index == 0 && this.size == 0) {
            node.setNext(head.getNext());
            node.setPrev(head);
            head.getNext().setPrev(node);
            head.setNext(node);
            this.size+=1;
        } else if (index == this.size && this.size != 0) {
            this.add(data);
        } else {
            Node curr = this.getNth(index); //old node
            node.setNext(curr);
            node.setPrev(curr.getPrev());
            curr.setPrev(node);
            node.getPrev().setNext(node);
            this.size+=1;
        }
    }

    /** 
    * method to add a node to the MyLinkedList at the end
    * @param  the data of the added node
    * @return the method will always return true, unless 
    * an exception occurs
    */
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException("Null Pointer Exception..");
        }
        Node node = new Node(data);
        node.setNext(tail);
        node.setPrev(tail.getPrev());
        tail.getPrev().setNext(node);
        tail.setPrev(node);
        size++;
        return true; 
    }

    /** 
    * method to set a node with a value element by index
    * @param  the data the user wants to set
    * @param the integer of the index
    * @return the data of node before the data was changed 
    */
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException("Null Pointer Exception..");
        }
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }
		Node curr = this.getNth(index);
        E returnData = curr.getElement();
        curr.setElement(data);
        return returnData;

    }

    /** 
    * method to remove a node  by index
    * @param the integer of the index
    * @return the data of node before the node was removed 
    */
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }
		Node curr = this.getNth(index);
        curr.getPrev().setNext(curr.getNext());
        curr.getNext().setPrev(curr.getPrev());
        size--;
        return curr.getElement();
    }

    /** 
    * method to clear/empty MyLinkedList
    */
    public void clear() {
        /* Add your implementation here */
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        this.size = 0; 
    }

    /** 
    * method to see if MyLinkedList is empty or filled
    * @return boolean: true if empty, false if filled
    */
    public boolean isEmpty() {
        if (this.size > 0) {
            return false;
        }
        return true;
    }

    /** 
    * Accessor to get the a node of MyLinkedList by the index
    * @param  the integer of the index
    * @return the targeted node 
    */
    protected Node getNth(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }
		Node curr = head.getNext();
		for(int i=0; i < index; i++)
		{
			curr = curr.getNext();
		}
        return curr;
    }

        /**
        * class MyListIterator is a class for our LinkedList iterator implementation
        *
        */
        protected class MyListIterator implements ListIterator<E> {
            Node left;
            Node right;
            int idx;
            boolean forward;
            boolean canRemoveOrSet;

            /** 
            * MyListIterator() is the no-arg constructor for MyListIterator
            */
            public MyListIterator() {
                this.left = MyLinkedList.this.head;
                this.right = MyLinkedList.this.head.getNext();
                this.idx = 0;
                this.forward = true;
                this.canRemoveOrSet = false;
            }

            /** 
            * hasNext() is the method for seeing it there is a next
            * node from current position
            * @return boolean: true if there is next node. 
            * false if no
            */
            public boolean hasNext() {
                return this.right != MyLinkedList.this.tail;
            }

            /** 
            * next() is the method for moving the iterator
            * to the right
            * @return E: returns the left node of the iterator
            */
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                    }
                this.left = this.right;
                this.right = this.right.getNext(); 
                this.idx++;
                this.canRemoveOrSet = true;
                return this.left.getElement();
            }

            /** 
            * next() is the method for moving the iterator
            * to the left
            * @return E: returns the right node of the iterator
            */
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }

                this.right = this.left;
                this.left = this.left.getPrev();
                this.idx--;
                this.forward = false;
                this.canRemoveOrSet = true;
                return this.right.getElement();
            }

            /** 
            * hasPrevious() is the method for seeing it there is a previous
            * node from current position
            * @return boolean: true if there is previous node. 
            * false if no
            */
            public boolean hasPrevious() {
                return this.left != MyLinkedList.this.head;

            }

            /** 
            * nextIndex() is the method for getting the index 
            * after a next() is called
            * @return integer of the index
            */
            public int nextIndex() {
                return this.idx;
            }

            /** 
            * previousIndex() is the method for getting the index 
            * after a previous() is called
            * @return integer of the index
            */
            public int previousIndex() {
                return this.idx - 1;

            }

            /** 
            * add() is the method for adding a new node to the
            * linkedList
            * @param element of the new node
            */
            public void add(E element) {
                if (element == null) {
                    throw new NullPointerException("Null Pointer Exception..");
                }
                Node curr = this.right; 
                Node node = new Node(element);
                node.setNext(curr);
                node.setPrev(curr.getPrev());
                curr.setPrev(node);
                node.getPrev().setNext(node);
                this.left = node;
                this.idx++;
                this.canRemoveOrSet = false;
                MyLinkedList.this.size++;
                
            }

            /** 
            * set() is the method for setting a node to the
            * linkedList
            * @param element the user want to set
            */
            public void set(E element) {
                if (element == null) {
                    throw new NullPointerException("Null Pointer Exception..");
                }
                if (!this.canRemoveOrSet) {
                    throw new IllegalStateException();
                }
                    
                if (this.forward) {
                    this.left.setElement(element);
                } else {
                    this.right.setElement(element);
                }
            }

            /** 
            * remove() is the method for removing a node from the
            * linkedList
            */
            public void remove() {
            if (!this.canRemoveOrSet) {
                throw new IllegalStateException();
            }

            if (this.forward) {
                Node curr = this.left;
                curr.getPrev().setNext(curr.getNext());
                curr.getNext().setPrev(curr.getPrev());
                this.left = curr.getPrev();
                this.idx--;
            } else {
                Node curr = this.right;
                curr.getPrev().setNext(curr.getNext());
                curr.getNext().setPrev(curr.getPrev());
                this.right = curr.getNext();
            }
            this.canRemoveOrSet = false;
            MyLinkedList.this.size--;
        }

        }
}