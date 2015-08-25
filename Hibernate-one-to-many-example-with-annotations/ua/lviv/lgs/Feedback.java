package ua.lviv.lgs;

import javax.persistence.*;

/**
 * Created by appko on 7/9/2015.
 */
@Entity
@Table(name = "feedbacks")
public class Feedback {


    private int id=0;
    private String authorNickName;
    private String authorEmail;
    private String description;
    private int feedbackMark;
    private Goods goods;
    public Feedback() {

    }

    public Feedback(String authorNickName, String authorEmail, String description, int feedbackMark) {
        //super();
        this.authorNickName = authorNickName;
        this.authorEmail = authorEmail;
        this.description = description;
        this.feedbackMark = feedbackMark;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column
    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }
    @Column
    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column
    public int getFeedbackMark() {
        return feedbackMark;
    }

    public void setFeedbackMark(int feedbackMark) {
        this.feedbackMark = feedbackMark;
    }

    @ManyToOne
    @JoinColumn(name = "goodId")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "authorNickName='" + authorNickName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", description='" + description + '\'' +
                ", feedbackMark=" + feedbackMark +
                ", id=" + id +
                ", goods=" + goods +
                '}';
    }
}
