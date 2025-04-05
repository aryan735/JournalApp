package com.Astra.journalApp.service;


import com.Astra.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weekly_sentiments", groupId = "weekly-sentiment-group")
    public void consumer(SentimentData sentimentData){
        sendEmail(sentimentData);
    }
    private void sendEmail(SentimentData  sentimentData){
        emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }
}


