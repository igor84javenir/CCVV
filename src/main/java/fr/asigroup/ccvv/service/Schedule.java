package fr.asigroup.ccvv.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class Schedule {

    private RdvService rdvService;

    public Schedule(RdvService rdvService) {
        this.rdvService = rdvService;
    }

    @Scheduled(cron = "@daily")
    private void ScheduledRunRGPD() {
        rdvService.doRGPD();
        rdvService.doPastRdv();
    }

    @Scheduled(initialDelayString = "PT01S", fixedDelayString = "P9999D")
    private void initialRunRGPD() {
        rdvService.doRGPD();
        rdvService.doPastRdv();
    }


}
