public class Product {
    private String productID, productName, manufacturerName, supplierName;
    private int quantity;
    private float price;

    public Product(String id, String name, String manufacturer, String supplier, int quant, float pri){
        productID = id;
        productName = name;
        manufacturerName = manufacturer;
        supplierName = supplier;
        quantity = quant;
        price = pri;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){

    }
    public String lineRepresentation(){
        return productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price ;
    }
    public String getSearchKey(){
        return productID;
    }
    
}
