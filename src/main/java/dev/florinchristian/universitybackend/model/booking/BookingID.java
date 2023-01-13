package dev.florinchristian.universitybackend.model.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


public class BookingID implements Serializable {

//    @Getter
//    @Setter
//    private Integer id;

    @Getter
    @Setter
    private String startTime;

    @Getter
    @Setter
    private String endTime;

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this == obj)
            return true;

        if (this.getClass() != obj.getClass())
            return false;

        BookingID bid = (BookingID) obj;

        return startTime.equals(bid.getStartTime()) && endTime.equals(bid.getEndTime());
    }

    @Override
    public int hashCode() {
        return startTime.hashCode() + endTime.hashCode();
    }
}
