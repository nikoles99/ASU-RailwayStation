package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by nolesuk on 21-Feb-17.
 */
public enum CarriageType implements Serializable {
    @JsonProperty("coup")COUP, @JsonProperty("common")COMMON, @JsonProperty("seatPlaces")SEAT_PLACE, @JsonProperty("reservedSeat")RESERVED_SEAT
}
