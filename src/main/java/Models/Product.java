package Models;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Category category;
    private Supplier supplier;

    public Product(int id, String name, double price) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public Product(int id, String name, double price, int quantity) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty");
        }

        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }

        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity<0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}