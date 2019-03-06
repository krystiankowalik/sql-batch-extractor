package com.github.krystiankowalik.sqlbatchextractor.service.impl;

import com.github.krystiankowalik.sqlbatchextractor.dao.ReportsRepo;
import com.github.krystiankowalik.sqlbatchextractor.model.Report;
import com.github.krystiankowalik.sqlbatchextractor.service.ReportsService;
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
public class ReportsServiceImpl implements ReportsService {


    private ReportsRepo reportsRepo;

    public Report save(Report report) {
        return reportsRepo.save(report);

    }

    public Report get(Integer id) {
        return reportsRepo.getOne(id);
    }

    public List<Report> getAll() {
        return reportsRepo.findAll();
    }
}
