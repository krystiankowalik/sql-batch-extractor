package com.github.krystiankowalik.sqlbatchextractor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String  name;

    @Column(name = "query")
    private String query;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "database_id", referencedColumnName = "id")
    private Database database;

}
