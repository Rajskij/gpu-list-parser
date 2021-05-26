package parser;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import entity.Gpu;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Scraper {
    private static final String baseUrl = "https://hotline.ua/computer/videokarty/?q=";

    public static List<Gpu> parseByTitle(String gpuTitle) {
        List<Gpu> gpuList = new LinkedList<>();

        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);

        try {
            HtmlPage page = client.getPage(baseUrl + gpuTitle);
            List<HtmlElement> items = page.getByXPath("//li[@class='product-item']");
            if (!items.isEmpty()) {
                for (HtmlElement htmlItem : items) {
                    HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//p[@class='h4']/a");
                    HtmlElement spanPrice = htmlItem.getFirstByXPath(".//span[@class='value']"); //div[@class='price-md']/
                    HtmlElement linkAnchor = htmlItem.getFirstByXPath(".//p[@class='h4']/a");

                    Gpu gpu = new Gpu();
                    gpu.setTitle(itemAnchor.asText());
                    int gpuPrice = 0;
                    if (!(spanPrice == null)) {
                        gpuPrice = Integer.parseInt(spanPrice.asText().trim().replaceAll("\\s", ""));
                    }
                    gpu.setPrice(gpuPrice);
                    gpu.setUrl("https://hotline.ua" + linkAnchor.getAttribute("href"));
                    gpuList.add(gpu);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gpuList;
    }
}