package com.elis.user.adapter.out.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "UserJpaEntity")
@Table(name = "user", schema = "public")
@Audited(withModifiedFlag = true)
public class UserJpaEntity {

    @Id
    @SequenceGenerator(
            name = "seq_user_id",
            sequenceName = "seq_user_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_user_id"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "username"
    )
    private String username;

    @Column(
            name = "email"
    )
    private String email;

    @Column(
            name = "password"
    )
    private String password;

    @Column(
            name = "address"
    )
    private String address;

    @Column(
            name = "age"
    )
    private int age;

}
