package pl.sda.poznan.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ClientOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;

  @ManyToMany(mappedBy = "clientOrders")
  private Set<Product> products = new HashSet<>();

}
