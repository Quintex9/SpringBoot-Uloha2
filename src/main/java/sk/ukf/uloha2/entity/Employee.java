package sk.ukf.uloha2.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public boolean isFullTime() {
        return fullTime;
    }


    @NotBlank(message = "Meno je povinné")
    @Size(min = 3, max = 20, message = "Meno musí mať 3-20 znakov")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Priezvisko je povinné")
    @Size(min = 3, max = 25, message = "Priezvisko musí mať 3-25 znakov")
    @Column(name = "last_name")
    private String lastName;


    @Column(name = "birth_date")
    private Date birthDate;

    @Pattern(
            regexp = "^[A-Za-z0-9%.]{1,64}@[A-Za-z0-9.]{2,190}\\.[A-Za-z]{2,20}$",
            message = "Email musí byť vo správnom formáte"
    )
    @Column(name = "email")
    private String email;

    @Pattern(
            regexp = "^\\+421\\d{9}$"
    )
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Práca nemože byť prázdna")
    @Size(min = 5, max = 75, message = "Práca musí mať 5-75 znakov")
    @Column(name = "job_title")
    private String jobTitle;

    @NotNull(message = "Musíte zadať príjem")
    @Positive
    @Column(name = "salary")
    private Double salary;

    @NotNull(message = "Musíte zadať, či ide o brigádu alebo full-time")
    @Column(name = "full_time")
    private Boolean fullTime;

}
