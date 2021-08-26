package coupon;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Member_table")
public class Member {

    @Id
    @Column(name = "id", insertable=false, updatable=false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String memId;
    private String regDate;
    private String withdrawDate;
    private String status;
    private String couponYN;
    private Long memNo;
    private String memNm;
    private String gubun;

    public Member(){
        memNo = this.id;
        System.out.println("생성자 호출 성공");
        System.out.println("-----------memNo(생성자) : "+ memNo);
    }

    @PrePersist
    public void onPrePersist(){
        memNo = this.id;
        System.out.println("-----------memNo(추가함수) : "+ memNo);
        System.out.println("=============MEMBER onPrePersist===============");
        MemberRegisted memberRegisted = new MemberRegisted();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        memberRegisted.setRegDate(df.format(cal.getTime()));
        //System.out.println("regDate : "+memberRegisted.getRegDate());
        memberRegisted.setMemNm(memberRegisted.getMemNm());
        BeanUtils.copyProperties(this, memberRegisted);
        memberRegisted.publishAfterCommit();

    }

    @PostPersist
    public void onPostPersist(){
        memNo = this.id;
        System.out.println("-----------memNo(수정함수) : "+ memNo);
        MemberAfterRegisted memberAfterRegisted = new MemberAfterRegisted();
        memberAfterRegisted.setMemNo(memNo);
        memberAfterRegisted.setGubun("NO");
        memberAfterRegisted.setMemId(this.getMemId());
        System.out.println("-----------memNo(수정함수) memNo : "+ memberAfterRegisted.getMemNo());
        System.out.println("-----------memNo(수정함수) gubun : "+ memberAfterRegisted.getGubun());
        System.out.println("-----------memNo(수정함수) memId : "+ this.getMemId());
        BeanUtils.copyProperties(this, memberAfterRegisted);
        memberAfterRegisted.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        System.out.println("-----------memNo(삭제함수) : "+ memNo);
        System.out.println("=============MEMBER onPreRemove0===============");
        MemberCancelled memberCancelled = new MemberCancelled();
        BeanUtils.copyProperties(this, memberCancelled);
        memberCancelled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        System.out.println("=============MEMBER onPreRemove1===============");
        coupon.external.Cancellation cancellation = new coupon.external.Cancellation();
        System.out.println("=============MEMBER onPreRemove2===============");
        // mappings goes here
        cancellation.setMemId(this.getMemId()); //memId 추가
        System.out.println("=============MEMBER onPreRemove3===============");
        cancellation.setStatus("member canceled(MEMBER)!!!");
        cancellation.setMemNo(memNo);
        MemberApplication.applicationContext.getBean(coupon.external.CancellationService.class)
                .cancel(cancellation);

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
    public Long getMemNo() {     return memNo;   }
    public void setMemNo(Long memNo) {
        this.memNo = memNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
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
    public String getCouponYN() {
        return couponYN;
    }

    public void setCouponYN(String couponYN) {
        this.couponYN = couponYN;
    }
    public String getMemNm() {
        return memNm;
    }

    public void setMemNm(String memNm) {
        this.memNm = memNm;
    }
    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }




}