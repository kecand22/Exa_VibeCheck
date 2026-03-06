package anna.backend.controller;

import anna.backend.dto.ArtistDto;
import anna.backend.entities.Artist;
import anna.backend.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("")
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        List<ArtistDto> dtos = artistService.loadAllArtists();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("{artistId}")
    public ResponseEntity<ArtistDto> getArtistById(
            @PathVariable("artistId") long id
    ){
        ArtistDto dto = this.artistService.getArtistById(id);

        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/artists_pagable")
    ResponseEntity<Page<Artist>> gellAllPageable(
            @RequestParam(defaultValue = "0") int page, //welche Seite
            @RequestParam (defaultValue = "10") int size, //wie viel pro seite
            @RequestParam (defaultValue = "artistId") String sortBy,
            @RequestParam (defaultValue = "asc") String sortDirection
    ){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Artist> artists = artistService.getAllPageable(pageable);
        return ResponseEntity.ok(artists);

    }
}
