package com.rayan.agendadortarefas.infrastructure.client;

import com.rayan.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscaUsuariopPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization")String token);

}
