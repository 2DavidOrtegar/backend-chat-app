package com.backend.chat.models.service;

import com.backend.chat.models.documents.Mensaje;

import java.util.List;

public interface ChatService {
    List<Mensaje> obtenerUltimos10Mensajes();
    Mensaje guardar(Mensaje mensaje);
}
