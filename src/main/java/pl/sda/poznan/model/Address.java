package pl.sda.poznan.model;

import javax.persistence.*;

@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // todo
  private Long id;
  private String street;
  private String country;
  private ZipCode zipCode;

  @OneToOne //todo
  private User user; // todo: add getter & setter

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public ZipCode getZipCode() {
    return zipCode;
  }

  public void setZipCode(ZipCode zipCode) {
    this.zipCode = zipCode;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
