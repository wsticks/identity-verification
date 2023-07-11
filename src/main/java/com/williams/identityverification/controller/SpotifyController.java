package com.williams.identityverification.controller;

import com.williams.identityverification.service.SpotifyConsumeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("spotify")
public class SpotifyController {

    private SpotifyConsumeService service;

    @GetMapping("/{artistId}")
    public Object getArtistInfo(@PathVariable String artistId){
        Object obj = service.getArtistInfo(artistId);
        return obj;
    }

    @PostMapping()
    public Object createArtistInfo(@RequestBody Object objReq){
        Object obj = service.createArtistInfo(objReq);
        return obj;
    }

    @GetMapping("/pers/{artistId}")
    public Object getArtistBtId(@PathVariable String artistId) throws IOException {
        Object obj = service.getArtistById(artistId);
        return obj;
    }
}
