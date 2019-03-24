package Logic;

public class Order {

    private int orderID;
    private int width;
    private int length;
    private int height;
    private String bandType;
    private String email;
    private boolean isShipped;

    public Order(int width, int length, int height, String bandType, String email) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.bandType = bandType;
        this.email = email;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBandType() {
        return bandType;
    }

    public void setBandType(String bandType) {
        this.bandType = bandType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsShipped() {
        return isShipped;
    }

    public void setIsShipped(boolean isShipped) {
        this.isShipped = isShipped;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", width=" + width + ", length=" + length + ", height=" + height + ", bandType=" + bandType + ", email=" + email + ", isShipped=" + isShipped + '}';
    }

    


    

}
