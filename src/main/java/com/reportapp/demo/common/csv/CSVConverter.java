package com.reportapp.demo.common.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Comuna;
import com.reportapp.demo.entity.TipoReporte;
import com.reportapp.demo.repository.IComunaRepository;

public class CSVConverter {

    private CSVConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Comuna> convertToListComuna(InputStreamReader inputStreamReader) throws IOException {

        List<Comuna> comunas = new ArrayList<>();

        BufferedReader br = new BufferedReader(inputStreamReader);
        // Saltar la primera línea
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {

            String[] columns = line.split(",");

            Comuna comuna = new Comuna();
            comuna.setId(Long.parseLong(columns[0]));
            comuna.setNombre(columns[1]);

            comunas.add(comuna);
        }

        return comunas;
    }

    public static List<Barrio> convertToListBarrio(InputStreamReader inputStreamReader, IComunaRepository comunaRepository)
            throws IOException {

        List<Barrio> barrios = new ArrayList<>();

        BufferedReader br = new BufferedReader(inputStreamReader);
        // Saltar la primera línea
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",");

            Barrio barrio = new Barrio();
            barrio.setId(Long.parseLong(columns[0]));
            Optional<Comuna> comuna = comunaRepository.findById(Long.parseLong(columns[1]));
            barrio.setComuna(comuna.get());
            barrio.setNombre(columns[2].replace("\"", ""));
            if (!comuna.isEmpty()) {
                    barrios.add(barrio);   
            }
        }
        return barrios;
    }

    public static List<TipoReporte> convertToListTipoReporte(InputStreamReader inputStreamReader) throws IOException {

        List<TipoReporte> tipos = new ArrayList<>();

        BufferedReader br = new BufferedReader(inputStreamReader);
        // Saltar la primera línea
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {

            String[] columns = line.split(",");
            TipoReporte tipo = new TipoReporte();
            tipo.setId(Long.parseLong(columns[0]));
            tipo.setDescripcion(columns[1].replace("\"", ""));
            tipo.setFecha(LocalDate.now());
            tipo.setNombreUsuario("ADMIN");
            tipos.add(tipo);
        }

        return tipos;
    }
}
