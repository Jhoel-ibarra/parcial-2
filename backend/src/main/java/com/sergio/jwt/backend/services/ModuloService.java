package com.sergio.jwt.backend.services;


import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.entites.Modulo;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.ModuloMapper;
import com.sergio.jwt.backend.repositories.ModuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final ModuloMapper moduloMapper;

    public List<ModuloDto> allModulos() {
        List<Modulo> all = moduloRepository.findAll();
        return moduloMapper.toModuloDtos(all);
    }

    public ModuloDto getModulo(Long id) {
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new AppException("modulo not found", HttpStatus.NOT_FOUND));
        return moduloMapper.toModuloDto(modulo);
    }

    public ModuloDto createModulo(ModuloDto moduloDto) {
        Modulo modulo = moduloMapper.toModulo(moduloDto);
        Modulo createModulo = moduloRepository.save(modulo);
        return moduloMapper.toModuloDto(createModulo);
    }

    public ModuloDto deleteModulo(Long id) {
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new AppException("modulo not found", HttpStatus.NOT_FOUND));
        moduloRepository.delete(modulo);
        return moduloMapper.toModuloDto(modulo);
    }
    public ModuloDto updateModulo(Long id, ModuloDto moduloDto) {
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new AppException("modulo not found", HttpStatus.NOT_FOUND));
        moduloMapper.updateModulo(modulo,moduloMapper.toModulo(moduloDto));

        Modulo updateModulo = moduloRepository.save(modulo);
        return moduloMapper.toModuloDto(updateModulo);
    }


}
