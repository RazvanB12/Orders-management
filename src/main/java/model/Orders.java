package model;

/**
 * Defineste si creeaza obicetele de tip Orders
 */

public class Orders {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;
    private int totalPrice;

    public Orders(){
    }

    public Orders(int id, int clientId, int productId, int quantity){
        super();
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
