package com.rgr.webtransferback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgr.webtransferback.models.Schedule;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, UUID> {
    
}
