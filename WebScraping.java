import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WebScraping {

    public LinkedList scrapeAmazon(int limit) {
        String url = "https://www.amazon.com/s?k=laptops";
        String csvFile = "amazon_products.csv";
        LinkedList productList = new LinkedList();

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                    .timeout(6000)
                    .get();

            Elements products = doc.select(".s-main-slot .s-result-item");

            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write("Product Title; Description; Price; Rating; Reviews\n");

            int count = 0;

            for (Element product : products) {
                String fullTitle = product.select(".a-size-medium.a-color-base.a-text-normal").text();
                String description;
                String price = product.select(".a-price .a-offscreen").text();
                String rating = product.select(".a-icon-alt").text();
                String reviews = product.select(".a-size-base.s-underline-text").text();

                if (!fullTitle.isEmpty()) {
                    String[] parts = fullTitle.split(",", 2);
                    String title = parts[0].trim();
                    description = parts.length > 1 ? parts[1].trim() : "";

                    if (price.isEmpty() || rating.isEmpty()) {
                        continue;
                    }

                    Product newProduct = new Product(title, description, price, rating, reviews);
                    productList.add(newProduct);

                    writer.write(String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"\n",
                            title.replace("\"", "\"\""),
                            description.replace("\"", "\"\""),
                            price,
                            rating,
                            reviews
                    ));
                    count++;
                }

                if (count >= limit) break;
            }

            writer.close();
            System.out.println("Filtered data saved to " + csvFile);
            System.out.println("Total Products Scraped: " + count);

        } catch (IOException e) {
            System.err.println("An error occurred while scraping the Amazon page: " + e.getMessage());
        }

        return productList;
    }
}
