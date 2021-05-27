package ua.gpu.seeker.model.entity;

public class UserRequest {
    private int price;
    private int ethash;

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
}