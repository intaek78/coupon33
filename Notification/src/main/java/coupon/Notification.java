package coupon;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Notification_table")
public class Notification {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String memId;
    private String phoneNum;
    private String email;

    @PrePersist
    public void onPrePersist(){
        Notified notified = new Notified();
        BeanUtils.copyProperties(this, notified);
        notified.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}