package com.github.krystiankowalik.sqlbatchextractor.service.impl;

import com.github.krystiankowalik.sqlbatchextractor.dao.DatabasesRepo;
import com.github.krystiankowalik.sqlbatchextractor.model.Database;
import com.github.krystiankowalik.sqlbatchextractor.service.DatabaseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class DatabasesServiceImpl implements DatabaseService {


    private DatabasesRepo databasesRepo;

    public Database save(Database database) {
        return databasesRepo.save(database);
    }

    public List<Database> getAll() {
        return databasesRepo.findAll();
    }
}
