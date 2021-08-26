package coupon.external;

public class Cancellation {

    private Long id;
    private String memId;
    private String withdrawlDate;
    private String status;
    private Long memNo;
    private String memNm;

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
    public String getWithdrawlDate() {
        return withdrawlDate;
    }

    public void setWithdrawlDate(String withdrawlDate) {
        this.withdrawlDate = withdrawlDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
