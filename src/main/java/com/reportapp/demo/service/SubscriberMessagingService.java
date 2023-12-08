package com.reportapp.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.firebase.internal.NonNull;
import com.reportapp.demo.entity.SubscriberMessaging;
import com.reportapp.demo.entity.usuario.Usuario;
import com.reportapp.demo.firebase.model.PushNotificationRequest;
import com.reportapp.demo.repository.ISubscriberMessagingRepository;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.share.constant.CustomMessage;

@Service
public class SubscriberMessagingService {

    private final ISubscriberMessagingRepository subscriberMessagingRepository;
    private final PushNotificationService pushNotificationService;
    private final IUsuarioRepository usuarioRepository;

    public SubscriberMessagingService(ISubscriberMessagingRepository subscriberMessagingRepository,
            PushNotificationService pushNotificationService, IUsuarioRepository usuarioRepository) {
        this.subscriberMessagingRepository = subscriberMessagingRepository;
        this.pushNotificationService = pushNotificationService;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Saves the subscriber messaging DTO.
     *
     * @param subDtoSave the subscriber messaging DTO to save
     * @return the ResponseEntity indicating the result of the save operation
     */
    public ResponseEntity<Object> save(String token, String username) {
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        SubscriberMessaging subFound = subscriberMessagingRepository.findByToken(token);

        if (subFound != null && subFound.getId() != null) {
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).build();
        }

        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isEmpty())
            return ResponseEntity.badRequest().build();
            
            try {
                subscriberMessagingRepository
                .save(SubscriberMessaging.builder().usuario(usuario.get()).token(token).build());
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(e);
            }

        return ResponseEntity.ok().build();
    }

    /**
     * Communicates a title, message, and topic to all subscribers.
     *
     * @param title   the title of the communication
     * @param message the message of the communication
     * @param topic   the topic of the communication
     */
    public void communicate(@NonNull String title, @NonNull String message, String topic) {
        List<SubscriberMessaging> subs = subscriberMessagingRepository.findAll();
        if (!subs.isEmpty()) {
            String hasTopic = !topic.isEmpty() && !topic.isBlank() ? topic : CustomMessage.DEFAULT_TOPIC;

            // subs.stream().forEach(sub -> {
            //     pushNotificationService.sendPushNotificationToToken(
            //             PushNotificationRequest.builder()
            //                     .title(title)
            //                     .message(message)
            //                     .topic(hasTopic)
            //                     .token(sub.getToken())
            //                     .build());
            // });

            for (int i = 0; i < subs.size(); i++) {
                String tok = subs.get(i).getToken();
                    pushNotificationService.sendPushNotificationToToken(

                        PushNotificationRequest.builder()
                                .title(title)
                                .message(message)
                                .topic(hasTopic)
                                .token(tok)
                                .build());
            }
        }
    }
}
