   package com.ty.hospitalapp.report;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
   public class ReportRepository {

       private final JdbcTemplate jdbc;

       public ReportRepository(JdbcTemplate jdbc) {
           this.jdbc = jdbc;
       }

       public List<Map<String, Object>> visitsPerBranch() {
           String sql = "SELECT b.bid AS branchId, b.name AS branchName, "
                   + "COUNT(e.eid) AS visits, COUNT(DISTINCT e.person_id) AS patients "
                   + "FROM branch b "
                   + "LEFT JOIN encounter e ON e.branch_id = b.bid "
                   + "GROUP BY b.bid, b.name "
                   + "ORDER BY visits DESC";
           return jdbc.queryForList(sql);
       }

       public List<Map<String, Object>> medicineCostPerVisit() {
           String sql = "SELECT e.eid AS encounterId, p.name AS patient, e.reason AS reason, "
                   + "SUM(i.cost * i.quantity) AS medicineCost "
                   + "FROM encounter e "
                   + "JOIN person p ON p.pid = e.person_id "
                   + "JOIN med_order m ON m.encounter_id = e.eid "
                   + "JOIN item i ON i.med_order_id = m.mid "
                   + "GROUP BY e.eid, p.name, e.reason "
                   + "ORDER BY medicineCost DESC";
           return jdbc.queryForList(sql);
       }
   }