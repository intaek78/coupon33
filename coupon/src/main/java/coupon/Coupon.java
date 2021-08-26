package coupon;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Coupon_table")
public class Coupon {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String memId;
    private String startDate;
    private String endDate;
    private String status;
    private String couponId;
    private Long memNo;
    private String gubun;


    @PostPersist
    public void onPostPersist(){
        System.out.println("$$$$$$$$$$$$$$$$$$$ Coupon onPostPersist $$$$$$$$$$$$$$$$$$$$$$$$$");
        Published published = new Published();
        BeanUtils.copyProperties(this, published);
        published.publishAfterCommit();

    }

    @PostUpdate
    public void onPostCanceled(){
        System.out.println("$$$$$$$$$$$$$$$$$$$ Coupon onPostCanceled $$$$$$$$$$$$$$$$$$$$$$$$$");
        AfterPublished afterPublished = new AfterPublished();
        afterPublished.setMemNo(this.getMemNo());
        BeanUtils.copyProperties(this, afterPublished);
        afterPublished.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist(){
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
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
    public Long getMemNo() {     return memNo;   }
    public void setMemNo(Long memNo) {
        this.memNo = memNo;
    }
    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }





}