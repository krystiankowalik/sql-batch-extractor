package com.github.krystiankowalik.sqlbatchextractor;

import com.github.krystiankowalik.sqlbatchextractor.service.SqlFetchingService;
import com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask;
import com.github.krystiankowalik.sqlbatchextractor.service.EnvironmentService;
import com.github.krystiankowalik.sqlbatchextractor.service.ReportingTasksService;
import com.github.krystiankowalik.sqlbatchextractor.service.SqlToCsvService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.github.krystiankowalik.sqlbatchextractor.model.ReportingTask.Status.*;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Log4j2
public class ScheduledJob {

    @NonNull
    private ReportingTasksService reportingTasksService;
    @NonNull
    private SqlToCsvService sqlToCsvService;
    @NonNull
    private SqlFetchingService sqlFetchingService;
    @NonNull
    private EnvironmentService environmentService;

    @Value("${file.upload-dir}")
    private String baseFilePath;


    @Autowired
    @Qualifier("myTaskExecutor")
    private TaskExecutor taskExecutor;


    @Scheduled(fixedRate = 10)
    public void processNextTask() {
        //Process next pending job
        ReportingTask nextPendingTask = reportingTasksService.getNextTask(PENDING);
        if (!initializeTask(nextPendingTask)) {
            ReportingTask nextFailedTask = reportingTasksService.getNextTask(FAILED);
            initializeTask(nextFailedTask);
        }
    }

    private boolean initializeTask(ReportingTask nextTask) {
        if (nextTask != null) {
            log.info("Next task to process is " + nextTask);
            reportingTasksService.updateTaskStatus(nextTask, INITIATED);
            log.info("Submitting task with id: " + nextTask.getId() + " for execution");
            submitTaskForExecution(nextTask);
            return true;
        }
        return false;

    }

    private void submitTaskForExecution(ReportingTask reportingTask) {
        taskExecutor.execute(() -> completeProcessing(reportingTask));
    }

    private void completeProcessing(ReportingTask reportingTask) {
        log.info("Processing task " + reportingTask);

//        waitSomeTime();

        String fileName = createFileName(reportingTask);
        String outputPath = createOutputPath(fileName);

        handleFetchedResults(reportingTask, fileName, outputPath);

        log.info("Processed task " + reportingTask);
    }

    private void handleFetchedResults(ReportingTask reportingTask, String fileName, String outputPath) {
        try {
            writeResultSetToFile(reportingTask, fetchResultSetFromDb(reportingTask), fileName, outputPath);
        } catch (SQLException e) {
            reportingTasksService.updateTaskStatus(reportingTask, FAILED);
            log.error(e.getMessage());
        } catch (Exception e) {
            reportingTasksService.updateTaskStatus(reportingTask, FAILED);
            log.error(e.getMessage());
        }
    }

    private void writeResultSetToFile(ReportingTask reportingTask, ResultSet resultSet, String fileName, String outputPath) throws SQLException {

        try {
            doWriteResultSetToFile(reportingTask, resultSet, outputPath);
            updateCompletedReportingTask(reportingTask, fileName);

        } catch (IOException e) {
            reportingTasksService.updateTaskStatus(reportingTask, FAILED);
            log.error(e.getMessage());
        } catch (SQLException e) {
            reportingTasksService.updateTaskStatus(reportingTask, FAILED);
            log.error(e.getMessage());
        } catch (Exception e) {
            reportingTasksService.updateTaskStatus(reportingTask, FAILED);
            log.error(e.getMessage());
        }

    }

    private ResultSet fetchResultSetFromDb(ReportingTask reportingTask) throws SQLException {
        reportingTasksService.updateTaskStatus(reportingTask, FETCHING_FROM_DB);
        return sqlFetchingService.fetchResultSet(reportingTask);
    }

    private void doWriteResultSetToFile(ReportingTask reportingTask, ResultSet resultSet, String outputPath) throws IOException, SQLException {
        reportingTasksService.updateTaskStatus(reportingTask, WRITING_RESULTS_TO_FILE);
        sqlToCsvService.writeSqlResultToCsv(resultSet, outputPath);
    }

    private void updateCompletedReportingTask(ReportingTask reportingTask, String fileName) {
        reportingTask.setResourceLink(environmentService.getBaseUrl() + "file/" + fileName);
        reportingTasksService.save(reportingTask);
        reportingTasksService.updateTaskStatus(reportingTask, COMPLETED);
    }

    private void waitSomeTime() {
        if (new Random().nextInt(5) >= 3) {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String createFileName(ReportingTask reportingTask) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss");
        return reportingTask.getReport().getName() + "_" + LocalDateTime.now().format(dateTimeFormatter) + ".csv";
    }

    private String createOutputPath(String fileName) {
        return baseFilePath + File.separator + fileName;

    }

}
