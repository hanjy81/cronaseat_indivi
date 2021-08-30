package coronaseat;

import coronaseat.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired CronaseatRepository cronaseatRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCanceled_IncreaseSeat(@Payload Canceled canceled){

        if(!canceled.validate()) return;

        Long seatId = Long.valueOf(canceled.getSeatId());
        Cronaseat cronaseat = cronaseatRepository.findByseatId(seatId);
        cronaseat.setReservableSeat(cronaseat.getReservableSeat()+canceled.getSeatNum().intValue());
        cronaseatRepository.save(cronaseat);
    }

}