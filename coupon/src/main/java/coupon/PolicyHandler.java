package coupon;

import coupon.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PolicyHandler{
    @Autowired CouponRepository couponRepository;
    @Autowired CancellationRepository cancellationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberRegisted_Publish(@Payload MemberRegisted memberRegisted){

        System.out.println("$$$$$$$$$$$$$$$$$$$ Coupon PolicyHandler wheneverMemberRegisted_Publish $$$$$$$$$$$$$$$$$$$$$$$$$");

            if(!memberRegisted.validate()) return;
            System.out.println("------쿠폰 초기등록-------");
            //System.out.println("\n\n##### listener Publish : " + memberRegisted.toJson() + "\n\n");
            Coupon coupon = new Coupon();

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //System.out.println("current: " + df.format(cal.getTime()));

            coupon.setStartDate(df.format(cal.getTime()));
            coupon.setCouponId(memberRegisted.getMemId()+System.currentTimeMillis());

            cal.add(Calendar.MONTH, 6);
            //cal.add(Calendar.DATE, -3);
            //System.out.println("after: " + df.format(cal.getTime()));
            coupon.setEndDate(df.format(cal.getTime()));
            coupon.setStatus("coupon issued!!");
            coupon.setMemId(memberRegisted.getMemId());
            couponRepository.save(coupon);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberAfterRegisted_updateMemno(@Payload MemberAfterRegisted memberAfterRegisted) {
        if(!memberAfterRegisted.validate()) return;
        System.out.println("------쿠폰 memNo수정-------");
        List<Coupon> couponOptional = couponRepository.findByMemId(memberAfterRegisted.getMemId());
        System.out.println("LENGTH : "+couponOptional.size());
        for(Coupon coupon:couponOptional){
            coupon.setMemNo(memberAfterRegisted.getMemNo());
            System.out.println("MemNo : "+memberAfterRegisted.getMemNo());
            couponRepository.save(coupon);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAfterCancelled_updateMemno(@Payload AfterCancelled afterCancelled) {
        if(!afterCancelled.validate()) return;
        System.out.println("------멤버탈퇴로 인한 쿠폰취소처리-------");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        List<Coupon> couponOptional = couponRepository.findByMemId(afterCancelled.getMemId());
        for(Coupon coupon:couponOptional){
            coupon.setStatus("Member withdrawed!!!!(coupon calcelled)");
            coupon.setEndDate(df.format(cal.getTime()));
            couponRepository.save(coupon);
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
