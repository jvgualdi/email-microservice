package tec.jvgualdi.emailmicroservice.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tec.jvgualdi.emailmicroservice.dtos.EmailRequest;
import tec.jvgualdi.emailmicroservice.services.EmailService;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest);
    }
}
