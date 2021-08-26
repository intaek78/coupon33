package coupon;

import coupon.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired CancellationRepository cancellationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPublished_UpdateStatus(@Payload Published published){

        System.out.println("$$$$$$$$$$$$$$$$$$$ Cancellation PolicyHandler wheneverPublished_UpdateStatus $$$$$$$$$$$$$$$$$$$$$$$$$");

        /*if(!published.validate()) {
            System.out.println("##### Cancellation PolicyHandler wheneverPublished_UpdateStatus validation 실패##########");
            return;
        }
        //System.out.println("\n\n##### Cancellation listener Publish : " + memberRegisted.toJson() + "\n\n");
        Cancellation cancellation = new Cancellation();
        System.out.println("##### Cancellation insert##########");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //cal.add(Calendar.MONTH, 6);
        //cal.add(Calendar.DATE, -3);
        //System.out.println("after: " + df.format(cal.getTime()));
        cancellation.setStatus("member signed up!!!!");
        cancellation.setSignupDate((df.format(cal.getTime())));
        cancellation.setMemId(published.getMemId());
        cancellationRepository.save(cancellation);*/
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelled_Cancelmember(@Payload MemberCancelled cancelled){

        System.out.println("$$$$$$$$$$$$$$$$$$$ Cancellation PolicyHandler wheneverCancelled_Cancelmember $$$$$$$$$$$$$$$$$$$$$$$$$");

        if(!cancelled.validate()) {
            System.out.println("##### Cancellation PolicyHandler wheneverCancelled_Cancelmember validation 실패##########");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("##### Cancellation 탈퇴##########");
        //if(cancelled.isMe()){
            System.out.println("==== cancelled.getMemNo()===> "+cancelled.getMemNo());
            List<Cancellation> cancellations = cancellationRepository.findByMemId(cancelled.getMemId());
            System.out.println("길이 : "+cancellations.size());
            for(Cancellation cancellation:cancellations){
                System.out.println("##### 멤버 탈퇴에 따른 REQ/RES ##########");
                cancellation.setStatus("member cancelled!!(Cancellation REQ/RES)");
                cancellation.setWithdrawDate(df.format(cal.getTime()));
                cancellation.setMemNm(cancelled.getMemNm());
                cancellationRepository.save(cancellation);
            }
        //}
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
