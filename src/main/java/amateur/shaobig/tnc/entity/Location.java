package amateur.shaobig.tnc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "locations", uniqueConstraints = {@UniqueConstraint(name = "unique_location_constraint", columnNames = {"country", "region", "locality"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String country;
    private String region;
    private String locality;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return country.equals(location.country) && Objects.equals(region, location.region) && Objects.equals(locality, location.locality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, region, locality);
    }

}
