package com.rayan.agendadortarefas.business;

import com.rayan.agendadortarefas.business.dto.TarefasDTO;
import com.rayan.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.rayan.agendadortarefas.business.mapper.TarefasConverter;
import com.rayan.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.rayan.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.rayan.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.rayan.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.rayan.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;


    /*
    Cadastrar tarefa
    * */
    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        // Converte DTO para Entity
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);
        // Salva no MongoDB
        TarefasEntity entitySalva = tarefasRepository.save(entity);
        // Converte Entity para DTO
        TarefasDTO resposta = tarefaConverter.paraTarefaDTO(entitySalva);

        return resposta;
    }


    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE)
        );
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        List<TarefasEntity> listaDeTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefasDTO(listaDeTarefas);

    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, ID inexistente!! " + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada!!"));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Error ao alterar statu da tarefa!!" + e.getCause());
        }
    }
    public TarefasDTO updateTarefas(TarefasDTO dto, String id){
        try{
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada!!"));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Error ao alterar statu da tarefa!!" + e.getCause());
        }

    }
}
