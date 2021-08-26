package coupon;

import coupon.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberRegisted_Notification(@Payload MemberRegisted memberRegisted){

        if(!memberRegisted.validate()) return;

        System.out.println("\n\n##### listener Notification : " + memberRegisted.toJson() + "\n\n");



        // Sample Logic //
        // Notification notification = new Notification();
        // notificationRepository.save(notification);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
