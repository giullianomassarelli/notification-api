package br.com.gd.notificationapi.mappers;

import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SheetMapper {

    @Autowired
    private ModelMapper mapper;

    public SheetResponseDTO toDto(SheetEntity entity) {
        log.info("convertendo entidade {} para dto", entity);
        return mapper.map(entity, SheetResponseDTO.class);
    }

    public List<SheetResponseDTO> toDtoList(List<SheetEntity> list) {
        log.info("convertendo lista de entidade {} para lista de dto", list);
        List<SheetEntity> result = new ArrayList<>();
        list.forEach(result::add);
        return result.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
