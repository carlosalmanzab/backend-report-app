package com.reportapp.demo.script;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.reportapp.demo.common.csv.CSVConverter;
import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Comuna;
import com.reportapp.demo.entity.TipoReporte;
import com.reportapp.demo.repository.IBarrioRepository;
import com.reportapp.demo.repository.IComunaRepository;
import com.reportapp.demo.repository.ITipoReporteRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    @Value("${reader.file.comuna}")
    private String comunaFile;

    @Value("${reader.file.barrio}")
    private String barrioFile;

    @Value("${reader.file.tiporeporte}")
    private String tiporeporteFile;

    @Autowired
    private IComunaRepository comunaRepository;

    @Autowired
    private IBarrioRepository barrioRepository;

    @Autowired
    private ITipoReporteRepository reporteRepository;

    @PostConstruct
    public void init() throws IOException {
        if (comunaRepository.count() == 0)
            loadDataComuna();
        if (barrioRepository.count() == 0)
            loadDataBarrio();
        if (reporteRepository.count() == 0)
            loadDataTiporeporte();
    }

    private void loadDataComuna() throws IOException {
        InputStream inputStream = new FileInputStream(comunaFile);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<Comuna> comunas = CSVConverter.convertToListComuna(inputStreamReader);
        for (Comuna comuna : comunas) {
            comunaRepository.save(comuna);
        }
    }

    private void loadDataBarrio() throws IOException {
        InputStream inputStream = new FileInputStream(barrioFile);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<Barrio> barrios = CSVConverter.convertToListBarrio(inputStreamReader, comunaRepository);
        for (Barrio barrio : barrios) {
            barrioRepository.save(barrio);
        }
    }

    private void loadDataTiporeporte() throws IOException {
        InputStream inputStream = new FileInputStream(tiporeporteFile);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<TipoReporte> tipos = CSVConverter.convertToListTipoReporte(inputStreamReader);
        for (TipoReporte tipo : tipos) {
            reporteRepository.save(tipo);
        }
    }
}
