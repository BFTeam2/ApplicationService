package com.example.transactionmanagementdemo.controller;

import com.example.transactionmanagementdemo.domain.entity.Digitaldocument;
import com.example.transactionmanagementdemo.service.DigitaldocumentService;
import com.example.transactionmanagementdemo.service.StorageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storage")

public class StorageController {

    @Autowired
    private StorageService service;

    @Autowired
    private DigitaldocumentService digitaldocumentService;

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
//        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
//    }
//
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
//        byte[] data = service.downloadFile(fileName);
//        ByteArrayResource resource = new ByteArrayResource(data);
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
//                .body(resource);
//    }

//    @DeleteMapping("/delete/{fileName}")
//    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
//        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
//    }


    @GetMapping(value = "/getAllDigitaldocument/{title}")
    @ApiOperation(value = "Get Digitaldocument File", response = Iterable.class)
    public ResponseEntity<ByteArrayResource> getAllDigitaldocument(@PathVariable String title) {
        List<Digitaldocument> lst = digitaldocumentService.getAllDigitaldocument();
        String path = lst.stream()
                .filter(d -> d.getTitle().toLowerCase().equals(title))
                .collect(Collectors.toList()).get(0).getPath();
        byte[] data = service.downloadFile(path);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + path + "\"")
                .body(resource);
    }

    @GetMapping(value = "/getRequiredDigitaldocument")
    @ApiOperation(value = "Get Digitaldocument File", response = Iterable.class)
    public List<String> getRequiredDigitaldocument() {
        return digitaldocumentService.getAllDigitaldocument().stream()
                .filter(d -> d.getIsRequired().toLowerCase().equals("yes"))
                .map(d -> d.getPath())
                .collect(Collectors.toList());
    }
}