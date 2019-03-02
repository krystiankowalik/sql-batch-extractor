package com.github.krystiankowalik.sqlbatchextractor.dao;

import com.github.krystiankowalik.sqlbatchextractor.model.Database;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabasesRepo extends JpaRepository<Database, Integer> {
}
