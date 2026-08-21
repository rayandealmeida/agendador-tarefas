package com.rayan.agendadortarefas.business.mapper;

import com.rayan.agendadortarefas.business.dto.TarefasDTO;
import com.rayan.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {
    TarefasEntity paraTarefaEntity(TarefasDTO dto);
    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
