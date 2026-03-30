package lk.ijse.automationcontrolservice.repository;

import lk.ijse.automationcontrolservice.entity.AutomationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomationLogRepository extends JpaRepository<AutomationLog, Long> {
}