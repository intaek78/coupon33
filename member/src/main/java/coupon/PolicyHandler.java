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
    @Autowired MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPublished_UpdateStatus(@Payload Published published){

        System.out.println("$$$$$$$$$$$$$$$$$$$ MEMBER PolicyHandler wheneverPublished_UpdateStatus $$$$$$$$$$$$$$$$$$$$$$$$$");
        if(!published.validate()) return;
        List<Member> memberOptional = memberRepository.findByMemId(published.getMemId());
        System.out.println("LENGTH : "+memberOptional.size());
        for(Member member:memberOptional){
            member.setStatus(published.getStatus()+", coupon id : "+published.getCouponId());
            member.setCouponYN("Y");
            memberRepository.save(member);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPublished_UpdateStatus(@Payload AfterPublished afterPublished){

        System.out.println("$$$$$$$$$$$$$$$$$$$ MEMBER PolicyHandler wheneverPublished_UpdateStatus $$$$$$$$$$$$$$$$$$$$$$$$$");
        if(!afterPublished.validate()) return;
        List<Member> memberOptional = memberRepository.findByMemId(afterPublished.getMemId());
        System.out.println("LENGTH : "+memberOptional.size());
        for(Member member:memberOptional){
            member.setMemNo(afterPublished.getMemNo());
            memberRepository.save(member);
        }
    }



    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberRegisted_UpdateMemNo(@Payload MemberRegisted memberRegisted){

        /*System.out.println("$$$$$$$$$$$$$$$$$$$ MEMBER PolicyHandler wheneverMemberRegisted_UpdateMemNo $$$$$$$$$$$$$$$$$$$$$$$$$");

        if(!memberRegisted.validate()) return;
        Member member = new Member();
        member.setMemNo(member.getId());
        memberRepository.save(member);*/
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverNotified_UpdateStatus(@Payload Notified notified){

        if(!notified.validate()) return;

        System.out.println("\n\n##### listener UpdateStatus : " + notified.toJson() + "\n\n");



        // Sample Logic //
        // Member member = new Member();
        // memberRepository.save(member);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
