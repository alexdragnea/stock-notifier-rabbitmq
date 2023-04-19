package net.dg.thresholdservice.repository;

import net.dg.thresholdservice.entity.ThresholdData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThresholdDataRepository extends JpaRepository<ThresholdData, Long> {}
