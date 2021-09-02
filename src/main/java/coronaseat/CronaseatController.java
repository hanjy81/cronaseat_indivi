package coronaseat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class CronaseatController {
        @Autowired
        CronaseatRepository cronaseatRepository;
        
        @RequestMapping(value = "/chkAndModifySeat",
                        method = RequestMethod.GET,
                        produces = "application/json;charset=UTF-8")

        public boolean modifySeat(HttpServletRequest request, HttpServletResponse response) throws Exception {
              System.out.println("##### /cronaseat/modifySeat  called #####");
        
              boolean status = false;
              Long seatId = Long.valueOf(request.getParameter("seatId"));
              int seatNum = Integer.parseInt(request.getParameter("seatNum"));   
              
              Cronaseat cronaseat = cronaseatRepository.findByseatId(seatId);
        
            // siege - circuit break
                try {
                     Thread.currentThread().sleep((long) (220 + Math.random() * 220));
              } catch (InterruptedException e) {
                    e.printStackTrace();
              } 
              // circuit break end

              if(cronaseat.getReservableSeat() >= seatNum) {
                           status = true;
                           cronaseat.setReservableSeat(cronaseat.getReservableSeat()-seatNum);
                           cronaseatRepository.save(cronaseat);
              }
              return status;
        }
 }
