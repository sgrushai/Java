package ua.lviv.lgs;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by appko on 7/9/2015.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id=0;
    @Column
    private String authorNickName;
    @Column
    private String authorEmail;
    @Column
    private String description;
    @Column
    private int feedbackMark;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "goods_orders", joinColumns = { @JoinColumn(name = "feedback_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "good_id", nullable = false, updatable = false) })
    private Set<Goods> goods = new HashSet<Goods>();
    public Order() {

    }

    public Order(String authorNickName, String authorEmail, String description, int feedbackMark) {
        //super();
        this.authorNickName = authorNickName;
        this.authorEmail = authorEmail;
        this.description = description;
        this.feedbackMark = feedbackMark;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }
    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getOrderMark() {
        return feedbackMark;
    }

    public void setOrderMark(int feedbackMark) {
        this.feedbackMark = feedbackMark;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "authorNickName='" + authorNickName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", description='" + description + '\'' +
                ", feedbackMark=" + feedbackMark +
                ", id=" + id +
                ", goods=" + goods +
                '}';
    }
}
