package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(generator = "Customer_Id")
    @TableGenerator(name = "Customer_Id", table = "customer_id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val",initialValue = 1, allocationSize = 10)
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_name", length = 200, nullable = false)
    private String name;
    @Column(name = "cust_email", length = 200)
    private String email;
    @Column(name = "cust_phone", length = 100)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private List<Reservation> reservation;

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
