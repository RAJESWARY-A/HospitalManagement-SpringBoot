   package com.ty.hospitalapp.report;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
   @RequestMapping("/reports")
   public class ReportController {

       private final ReportRepository repo;

       public ReportController(ReportRepository repo) {
           this.repo = repo;
       }

       @GetMapping("/visits-per-branch")
       public List<Map<String, Object>> visitsPerBranch() {
           return repo.visitsPerBranch();
       }

       @GetMapping("/medicine-cost")
       public List<Map<String, Object>> medicineCost() {
           return repo.medicineCostPerVisit();
       }
   }
