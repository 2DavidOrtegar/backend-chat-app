package com.backend.chat.controllers;


import com.backend.chat.models.documents.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {


    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        mensaje.setTexto("Recibido por el Broker: "+ mensaje.getTexto());

        return mensaje;
    }



}
