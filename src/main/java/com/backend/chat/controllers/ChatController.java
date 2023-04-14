package com.backend.chat.controllers;


import com.backend.chat.models.documents.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {


    private String[] colores = {"red", "green", "blue", "magneta", "purple", "orange"};

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        if (mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("nuevo usuario");
        }

        return mensaje;
    }





}
