public class Main {
    public static void main(String[] args) {

        WebScraping webScraping = new WebScraping();

        // Scrape 20 products from Amazon
        LinkedList productList = webScraping.scrapeAmazon(20);

        // Add an example product manually to the linked list
        productList.add(new Product("Dell Inspiron Touchscreen Laptop",
                "15.6\" Business & Student Laptop Computer, Windows 11 Pro Laptop 32GB RAM 1TB SSD",
                "$1200", "4.5", "200 reviews"));

        // Print all products in the linked list
        System.out.println("\nAll products in LinkedList:");
        productList.printAll();

        // Search for a specific product by title
        System.out.println("\nSearching for 'Dell Inspiron 3520 15.6\":");
        Product foundProduct = productList.search("Dell Inspiron 3520 15.6\"");
        System.out.println(foundProduct != null ? "Found: " + foundProduct : "Product not found.");

        // Remove a product by title
        System.out.println("\nRemoving 'Dell Inspiron 3520 15.6\":");
        productList.remove("Dell Inspiron 3520 15.6\"");

        // Print the list after removal
        System.out.println("\nAll products after removal:");
        productList.printAll();

        // Sort products by price and print them
        System.out.println("\nSorting products by price:");
        SelectionSort.sort(productList);
        productList.printAll();
    }
}
