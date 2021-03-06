package api.entity;

import javax.persistence.*;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "places")
public class PlaceEntity extends AbstractEntity {

    @Column(name = "number")
    private Integer number;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "carriage_id")
    private CarriageEntity carriage;

    @OneToOne
    @JoinColumn(name = "train_id")
    private TrainEntity train;

    @OneToOne(mappedBy = "place")
    private TicketEntity ticket;

    public PlaceEntity() {
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CarriageEntity getCarriage() {
        return carriage;
    }

    public void setCarriage(CarriageEntity carriage) {
        this.carriage = carriage;
    }

    public TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }
}
