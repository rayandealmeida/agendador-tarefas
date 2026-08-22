package com.rayan.agendadortarefas.infrastructure.entity;

import com.rayan.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tarefas")
public class TarefasEntity {

    @Id
    private String id;

    private String nomeDaTarefa;
    private String descricao;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
