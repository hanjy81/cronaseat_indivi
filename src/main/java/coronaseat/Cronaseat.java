package coronaseat;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Cronaseat_table")
public class Cronaseat {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long seatId;
    private String seatType;
    private Integer reservableSeat;

    @PostPersist
    public void onPostPersist(){
        CronaseatRegistered seatRegistered = new CronaseatRegistered();
        BeanUtils.copyProperties(this, seatRegistered);
        seatRegistered.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){
        SeatModified seatModified = new SeatModified();
        BeanUtils.copyProperties(this, seatModified);
        seatModified.publishAfterCommit();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }


    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getReservableSeat() {
        return reservableSeat;
    }

    public void setReservableSeat(Integer reservableSeat) {
        this.reservableSeat = reservableSeat;
    }




}