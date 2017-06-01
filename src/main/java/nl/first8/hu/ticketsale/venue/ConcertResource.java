package nl.first8.hu.ticketsale.venue;

import nl.first8.hu.ticketsale.sales.Sale;
import nl.first8.hu.ticketsale.sales.Ticket;
import nl.first8.hu.ticketsale.sales.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/concerts")
@Transactional
public class ConcertResource {

    private final ConcertService service;

    @Autowired
    public ConcertResource(ConcertService service) {
        this.service = service;
    }

    @GetMapping
    public List<Concert> getAll() {
        return service.list();
    }

    @GetMapping(path = "/artistname/{name:.+}")
    public List<Concert> getByName(@PathVariable("name") final String name) {
        return service.getByName(name);
    }

}
