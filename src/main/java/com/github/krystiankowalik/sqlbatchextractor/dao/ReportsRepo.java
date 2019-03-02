package com.github.krystiankowalik.sqlbatchextractor.dao;

import com.github.krystiankowalik.sqlbatchextractor.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepo extends JpaRepository<Report, Integer> {
}
