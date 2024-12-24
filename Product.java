public class Product {
    String title;
    String description;
    String price;
    String rating;
    String reviews;

    public Product(String title, String description, String price, String rating, String reviews) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public double getPriceValue() {
        try {
            // Split the price by space and take the first price before '$'
            String[] prices = price.split(" ");
            return Double.parseDouble(prices[0].replace("$", ""));
        } catch (NumberFormatException e) {
            return Double.MAX_VALUE; // Default for unavailable price
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Price: " + price + ", Rating: " + rating + ", Reviews: " + reviews;
    }
}
