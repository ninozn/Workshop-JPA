package nl.first8.hu.ticketsale.artistInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.first8.hu.ticketsale.venue.Concert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @JsonIgnore //was needed because TestRepositoryIntegrationTest.testGetTicket caused a NestedServletException error. Don't know why...
    @OneToMany(mappedBy = "artist")
    private List<Concert> concerts;
}
