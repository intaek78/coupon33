package coupon;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Mypage_table")
public class Mypage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String memId;
        private String regDate;
        private String withdrawDate;
        private String status;
        private String startDate;
        private String endDate;
        private Long memNo;
        private String memNm;
        private String couponId;


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
        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

}
