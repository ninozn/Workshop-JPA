package nl.first8.hu.ticketsale.venue;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {

    private final ConcertRepository concertRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    List<Concert> list() {
        return concertRepository.findAll();
    }

    public List<Concert> getByName(@NonNull final String name) {
        return concertRepository.findByName(name);
    }

}
