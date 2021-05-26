package entity;

public class Gpu {
    private String title;
    private int price;
    private int ethash;
    private String url;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEthash() {
        return ethash;
    }

    public void setEthash(int ethash) {
        this.ethash = ethash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}