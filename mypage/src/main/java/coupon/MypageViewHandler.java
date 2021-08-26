package coupon;

import coupon.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMemberRegisted_then_CREATE_1 (@Payload MemberRegisted memberRegisted) {
        try {

            if (!memberRegisted.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            mypage.setRegDate(df.format(cal.getTime()));
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setMemId(memberRegisted.getMemId());
            //mypage.setStatus(memberRegisted.getStatus());
            //System.out.println("------mypage getStatus-------"+memberRegisted.getStatus());
            System.out.println("------mypage RegDate-------"+df.format(cal.getTime()));
            mypage.setMemNm(memberRegisted.getMemNm());
            //mypage.setMemNo(memberRegisted.getMemNo());
            // view 레파지 토리에 save
            mypageRepository.save(mypage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberAfterRegisted_updateMypageMemNo(@Payload MemberAfterRegisted memberAfterRegisted) {
        try {
            if (!memberAfterRegisted.validate()) return;
            System.out.println("------쿠폰 memNo수정-------");
            List<Mypage> mypageOptional = mypageRepository.findByMemId(memberAfterRegisted.getMemId());
            System.out.println("LENGTH : " + mypageOptional.size());
            for (Mypage mypage : mypageOptional) {
                mypage.setMemNo(memberAfterRegisted.getMemNo());
                System.out.println("MemNo : " + memberAfterRegisted.getMemNo());
                mypageRepository.save(mypage);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPublished_then_UPDATE_1(@Payload Published published) {
        try {
            if (!published.validate()) return;
                // view 객체 조회

                    List<Mypage> mypageList = mypageRepository.findByMemId(published.getMemId());
                    for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setStartDate(published.getStartDate());
                    mypage.setEndDate(published.getEndDate());
                    mypage.setCouponId(published.getCouponId());
                    mypage.setStatus(published.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenMemberAfterCanceled_then_UPDATE(@Payload AfterCancelled afterCancelled) {
        try {
            if (!afterCancelled.validate()) return;
                // view 객체 조회

                    List<Mypage> mypageList = mypageRepository.findByMemId(afterCancelled.getMemId());
                    for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    System.out.println("------mypage AfterCanceled getMemId-------"+afterCancelled.getMemId());
                    System.out.println("------mypage AfterCanceled getWithdrawDate-------"+afterCancelled.getWithdrawDate());
                    System.out.println("------mypage AfterCanceled getEndDate-------"+afterCancelled.getEndDate());
                    mypage.setStatus(afterCancelled.getStatus());
                    mypage.setWithdrawDate(afterCancelled.getEndDate());
                    mypage.setEndDate(afterCancelled.getEndDate());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

