package coupon;

public class MemberAfterRegisted extends AbstractEvent {

    private Long id;
    private String memId;
    private String status;
    private String regDate;
    private String couponYN;
    private Long memNo;
    private String gubun;

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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public String getCouponYN() {
        return couponYN;
    }

    public void setCouponYN(String couponYN) {
        this.couponYN = couponYN;
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