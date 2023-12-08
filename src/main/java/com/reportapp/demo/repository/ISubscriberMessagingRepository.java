package com.reportapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reportapp.demo.entity.SubscriberMessaging;

@Repository
public interface ISubscriberMessagingRepository extends JpaRepository<SubscriberMessaging, Long> {

    SubscriberMessaging findByToken(String token);
    
}
