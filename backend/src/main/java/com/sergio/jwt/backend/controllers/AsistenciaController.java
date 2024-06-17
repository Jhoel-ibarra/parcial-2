package com.sergio.jwt.backend.controllers;


import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.Asistencia;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.repositories.AsistenciaRespository;
import com.sergio.jwt.backend.services.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
@RestController("/asistencia")
public class AsistenciaController {


    private final AsistenciaService asistenciaService;
    private final AsistenciaRespository asistenciaRespository;

    @PostMapping("/upload")
    public String upload(@AuthenticationPrincipal UserDto user,
                         @RequestParam("file") MultipartFile file,
                         Model model, Long id) {

        // Validar el tipo de archivo
        if (!file.getOriginalFilename().toLowerCase().endsWith(".jpg") &&
                !file.getOriginalFilename().toLowerCase().endsWith(".jpeg")) {
            model.addAttribute("message", "Solo se permiten archivos JPG/JPEG.");
            return "result";
        }

        try {
            Optional<GeoLocation> location =  extractGeoLocation(file);

            if (location.isPresent() && isLocationInUniversity(location.get())) {
                // Guardar la asistencia
                Asistencia asistencia = asistenciaRespository.findById(id)
                        .orElseThrow(()-> new AppException("asistencia no encontrada", HttpStatus.NOT_FOUND));

                asistencia.setEstado("presente");
                asistenciaRespository.save(asistencia);

                model.addAttribute("message", "Asistencia registrada correctamente.");
         }else{
            model.addAttribute("message", "Ubicación no válida. No se pudo registrar la asistencia ");
         }
        }catch (IOException e){
            model.addAttribute("message", "Error al procesar la imagen.");
        }
        return "resultado";
    }
    private Optional<GeoLocation> extractGeoLocation(MultipartFile file) throws IOException {
        // Implementar extracción de metadatos GPS aquí
        // Por ejemplo, usando metadata-extractor: https://drewnoakes.com/code/exif/

        try (InputStream inputStream = new ByteArrayInputStream(file.getBytes())) {
            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
            for (Directory directory : metadata.getDirectories()) {
                if (directory instanceof GpsDirectory) {
                    GeoLocation geoLocation = ((GpsDirectory) directory).getGeoLocation();
                    if (geoLocation != null) {
                        return Optional.of(geoLocation);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private boolean isLocationInUniversity(GeoLocation location) {
        // Verificar si la ubicación está dentro del campus
        double universityLat = 17.7762882081; // Latitud de la universidad
        double universityLng = 63.195041503; // Longitud de la universidad
        double radius = 0.05; // Radio de tolerancia en grados (aproximadamente 500 metros)

        return Math.abs(location.getLatitude() - universityLat) < radius &&
                Math.abs(location.getLongitude() - universityLng) < radius;
    }
}
