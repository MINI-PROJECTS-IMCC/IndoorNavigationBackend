package com.example.demo.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MapController {

    @Autowired
    private MapService mapService;

    @PostMapping("/getMapZip")
    public ResponseEntity<?> getMap(@RequestBody Maprequest request) {
    try {
        byte[] zipBytes = mapService.generateZip(request.getQrValue());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mapdata.zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zipBytes);
    } 
    catch (Exception e) {
        return ResponseEntity
                .badRequest()
                .body("Invalid QR Code");
    }
}

}
