package nl.first8.hu.ticketsale.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditTrial implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long sale_id;
    private Long account_id;

    //created a custom constructor because of the order of arguments.
    public AuditTrial(Long saleId, Long accountId) {
        this.sale_id = saleId;
        this.account_id = accountId;
    }
}
