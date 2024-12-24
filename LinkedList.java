public class LinkedList {
    private Node head;

    public static class Node {
        Product product;
        Node next;

        Node(Product product) {
            this.product = product;
        }
    }

    // Method to add a product to the list
    public void add(Product product) {
        Node newNode = new Node(product);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Method to remove a product by title (partial match)
    public void remove(String title) {
        if (head == null) return;

        // If the head node's product matches the title, remove the head
        if (head.product.getTitle().contains(title)) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && !temp.next.product.getTitle().contains(title)) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Method to print all products in the list
    public void printAll() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.product);
            temp = temp.next;
        }
    }

    // Method to search for a product by title
    public Product search(String title) {
        Node temp = head;
        while (temp != null) {
            if (temp.product.getTitle().contains(title)) { // Partial match for title
                return temp.product;
            }
            temp = temp.next;
        }
        return null;
    }

    public Node getHead() {
        return head;
    }
}
