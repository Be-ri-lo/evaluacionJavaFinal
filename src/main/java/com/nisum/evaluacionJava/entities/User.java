package com.nisum.evaluacionJava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="usuario")
@ToString(exclude = "phone")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    //@Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created;

    //@Column(name = "updated_at")
    @CreationTimestamp
    private LocalDateTime updated;

    @Column
    private String lastLogin;

    @Column
    //para que un dato no se persistido @Transient
    private String password;

    @Column
    private String phone;

    @Column
    private String tokenId;


    /*@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Phone> phone;*/




}
