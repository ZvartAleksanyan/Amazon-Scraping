class SelectionSort {
    public static void sort(LinkedList list) {
        LinkedList.Node head = list.getHead();
        for (LinkedList.Node i = head; i != null; i = i.next) {
            LinkedList.Node min = i;
            for (LinkedList.Node j = i.next; j != null; j = j.next) {
                if (j.product.getPriceValue() < min.product.getPriceValue()) {
                    min = j;
                }
            }
            if (min != i) {
                Product temp = i.product;
                i.product = min.product;
                min.product = temp;
            }
        }
    }
}
