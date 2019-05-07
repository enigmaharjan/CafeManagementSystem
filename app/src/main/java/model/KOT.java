package model;

public class KOT {
    private int kotId;
    private String kotItem;
    private String kotQuantity;
    private String table;

    public KOT(int kotId, String kotItem, String kotQuantity, String table) {
        this.kotId = kotId;
        this.kotItem = kotItem;
        this.kotQuantity = kotQuantity;
        this.table = table;
    }

    public int getKotId() {
        return kotId;
    }

    public void setKotId(int kotId) {
        this.kotId = kotId;
    }

    public String getKotItem() {
        return kotItem;
    }

    public void setKotItem(String kotItem) {
        this.kotItem = kotItem;
    }

    public String getKotQuantity() {
        return kotQuantity;
    }

    public void setKotQuantity(String kotQuantity) {
        this.kotQuantity = kotQuantity;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
