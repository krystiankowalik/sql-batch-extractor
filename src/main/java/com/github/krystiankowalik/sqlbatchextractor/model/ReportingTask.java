package com.github.krystiankowalik.sqlbatchextractor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reporting_tasks")
public class ReportingTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;
    @Column(name = "resource_link")
    private String resourceLink;
    @Column(name = "status")
    private Status status;

    public enum Status {
        PENDING("PENDING"),
        INITIATED("INITIATED"),
        FETCHING_FROM_DB("FETCHING_FROM_DB"),
        WRITING_RESULTS_TO_FILE("WRITING_RESULTS_TO_FILE"),
        COMPLETED("COMPLETED"),
        FAILED("FAILED"),
        CANCELLED("CANCELLED");

        String desc;

        Status(String desc) {
            this.desc = desc;
        }
    }
}
