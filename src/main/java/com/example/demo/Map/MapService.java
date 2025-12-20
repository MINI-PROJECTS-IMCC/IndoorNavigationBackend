package com.example.demo.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class MapService {

    public byte[] generateZip(String qrValue) throws Exception {

        // Map QR to files
        Map<String, String> imageMap = Map.of(
                "IMCC_M_floor0", "IMCC_M_floor0.png",
                "IMCC_M_floor1", "IMCC_M_floor1.png",
                "IMCC_M_floor2", "IMCC_M_floor2.png",
                "IMCC_M_floor3", "IMCC_M_floor3.png",
                "IMCC_M_floor4", "IMCC_M_floor4.png",
                "IMCC_M_floor5", "IMCC_M_floor5.png"
        );

        Map<String, String> xmlMap = Map.of(
                "IMCC_M_floor0", "IMCC_M_floor0.xml",
                "IMCC_M_floor1", "IMCC_M_floor1.xml",
                "IMCC_M_floor2", "IMCC_M_floor2.xml",
                "IMCC_M_floor3", "IMCC_M_floor3.xml",
                "IMCC_M_floor4", "IMCC_M_floor4.xml",
                "IMCC_M_floor5", "IMCC_M_floor5.xml"
        );

        if (!imageMap.containsKey(qrValue)) {
            throw new Exception("Invalid QR Code");
        }

        // Load files
        ClassPathResource img = new ClassPathResource("static/images/" + imageMap.get(qrValue));
        ClassPathResource xml = new ClassPathResource("data/" + xmlMap.get(qrValue));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        // Add image
        zos.putNextEntry(new ZipEntry("map.png"));
        zos.write(img.getInputStream().readAllBytes());
        zos.closeEntry();

        // Add XML
        zos.putNextEntry(new ZipEntry("datapoints.xml"));
        zos.write(xml.getInputStream().readAllBytes());
        zos.closeEntry();

        zos.close();
        return baos.toByteArray();
    }
}
