package com.rgr.webtransferback.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rgr.webtransferback.models.Schedule;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, UUID> {

    @Query(value = "SELECT * FROM transfer_schedules s WHERE s.deleted IS false", nativeQuery = true)
    Page<Schedule> findAllActive(Pageable pageable);
    
}
