package com.backend.chat.controllers;


import com.backend.chat.models.documents.Mensaje;
import com.backend.chat.models.service.ChatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    @Autowired
    private ChatsServiceImpl chatsService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private String[] colores = {"red", "green", "blue", "magneta", "purple", "orange"};

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        if (mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("nuevo usuario");

        }else{
            chatsService.guardar(mensaje);
        }

        return mensaje;
    }


    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username){
        return username.concat(" está escribiendo...");
    }


    @MessageMapping("/historial")
    @SendTo("/chat/historial")
    public void historial(String clienteID){
        webSocket.convertAndSend("/chat/historial/"+clienteID, chatsService.obtenerUltimos10Mensajes());
    }


}
