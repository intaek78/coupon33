package coupon;

import javax.persistence.*;

import coupon.config.kafka.KafkaProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Cancellation_table")
public class Cancellation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String memId;
    private String withdrawDate;
    private String signupDate;
    private String status;
    private Long memNo;
    private String memNm;


    @PostPersist
    public void onPostPersist(){
        System.out.println("$$$$$$$$$$$$$$$$$$$ Cancellation PolicyHandler onPostPersist11 $$$$$$$$$$$$$$$$$$$$$$$$$");
        AfterCancelled afterCancelled  = new AfterCancelled();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        afterCancelled.setWithdrawDate(df.format(cal.getTime()));
        afterCancelled.setEndDate(df.format(cal.getTime()));
        System.out.println("##### setEndDate ====>" + afterCancelled.getWithdrawDate());
        System.out.println("##### setEndDate(df) ====>" + df.format(cal.getTime()));
        BeanUtils.copyProperties(this, afterCancelled);
        afterCancelled.publishAfterCommit();
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
    public String getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        this.withdrawDate = withdrawDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(String signupDate) {
        this.signupDate = signupDate;
    }
    public Long getMemNo() {     return memNo;   }
    public void setMemNo(Long memNo) {
        this.memNo = memNo;
    }
    public String getMemNm() {
        return memNm;
    }

    public void setMemNm(String memNm) {
        this.memNm = memNm;
    }



}