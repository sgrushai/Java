package ua.lviv.lgs;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by appko on 7/9/2015.
 */
@Entity
@Table(name="goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private String subCategoryId;
    @Column
    private String model;
    @Column
    private int quantity;
    @Column
    private double weight;
    @Column
    private String pictureUrl;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "goods")
    private Set<Feedback> feedbacks = new HashSet<Feedback>();



    public Goods(String name, String description, double price, String subCategoryId, String model, int quantity, double weight, String pictureUrl) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.subCategoryId = subCategoryId;
        this.model = model;
        this.quantity = quantity;
        this.weight = weight;
        this.pictureUrl = pictureUrl;
    }

    public Goods() {

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
        this.price = price;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", subCategoryId='" + subCategoryId + '\'' +
                ", model='" + model + '\'' +
                ", quantity=" + quantity +
                ", weight=" + weight +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
