package api.entity;

import javax.persistence.*;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "passengers")
public class PassengerEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "last_name")
    private ScheduleEntity schedule;

    @Column(name = "rate")
    private Double rate;

    @OneToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public PassengerEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
