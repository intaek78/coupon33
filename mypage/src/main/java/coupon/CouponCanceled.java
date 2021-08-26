package coupon;

public class CouponCanceled extends AbstractEvent {

    private Long id;
    private String memId;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderId() {
        return memId;
    }

    public void setOrderId(String memId) {
        this.memId = memId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}