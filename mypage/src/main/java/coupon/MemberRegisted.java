package coupon;

public class MemberRegisted extends AbstractEvent {

    private Long id;
    private String memId;
    private String status;
    private String regDate;
    private String memNm;
    private Long memNo;

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