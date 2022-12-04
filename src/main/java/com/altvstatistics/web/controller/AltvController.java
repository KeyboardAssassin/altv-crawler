package com.altvstatistics.web.controller;


import com.altvstatistics.web.api.AltvApiClient;
import com.altvstatistics.web.dto.AltvOnlineStatus;
import com.altvstatistics.web.entity.AltvOnlineMonitoringEntity;
import com.altvstatistics.web.entity.AltvOnlineMonitoringRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/altv")
public class AltvController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AltvController.class);
    public static final long SERVER_ID_SIBAUI = 91L;

    private AltvApiClient altvApiClient;

    private final Counter altvCounter;

    private AltvOnlineMonitoringRepository altvOnlineMonitoringRepository;


    public AltvController(AltvApiClient altvApiClient, MeterRegistry meterRegistry, AltvOnlineMonitoringRepository altvOnlineMonitoringRepository) {
        this.altvApiClient = altvApiClient;
        altvCounter = meterRegistry.counter("sibaui");
        this.altvOnlineMonitoringRepository = altvOnlineMonitoringRepository;
    }

    @GetMapping
    public AltvOnlineStatus doSo() {
        AltvOnlineStatus onlineStatus = altvApiClient.getOnlineStatus(SERVER_ID_SIBAUI);

        return onlineStatus;
    }


    @Scheduled(cron = "0 0/5 * * * *")
    public void fetchDataScheduled() {

        try {
            AltvOnlineStatus onlineStatus = altvApiClient.getOnlineStatus(SERVER_ID_SIBAUI);
            altvCounter.increment(onlineStatus.Players());


            AltvOnlineMonitoringEntity altvOnlineMonitoring = new AltvOnlineMonitoringEntity();
            altvOnlineMonitoring.setAmountPlayers(onlineStatus.Players());
            altvOnlineMonitoring.setLastUpdate(onlineStatus.LastUpdate());
            altvOnlineMonitoring.setTimestamp(System.currentTimeMillis());
            altvOnlineMonitoring.setServerId(SERVER_ID_SIBAUI);
            altvOnlineMonitoring = altvOnlineMonitoringRepository.save(altvOnlineMonitoring);


            LOGGER.info("Crawl successful: " + altvOnlineMonitoring);
        } catch (Exception e) {
            LOGGER.error("Error by processing", e);
        }
    }


}
