package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.FeignMultipartSupportConfig;
import com.example.demo.dto.contents.ContentRequest;
import com.example.demo.dto.contents.ContentResponse;
import com.example.demo.entity.Contents;

@FeignClient(
    name = "contents-api", 
    url = "http://netflixclone-service:8082",
    configuration = FeignMultipartSupportConfig.class
)
public interface ContentsClient {

    @GetMapping("/api/contents")
    List<ContentResponse> getAllContents();
    
    @GetMapping("/api/contents/kind/{kind}")
    List<ContentResponse> getContentsByKind(@PathVariable("kind") String kind);
    
    @GetMapping("/api/contents/{id}")
    ContentResponse getContentsById(@PathVariable Long id);

    @PostMapping("/api/contents/json")
    ContentResponse createContents(@RequestBody Contents contents);

    @PostMapping(value = "/api/contents", consumes = "multipart/form-data")
    ContentResponse createContents(
        @RequestParam("title") String title,
        @RequestParam("genre") String genre,
        @RequestParam("director") String director,
        @RequestParam("year") Integer year,
        @RequestParam("running") Integer running,
        @RequestParam("summary") String summary,
        @RequestParam("kind") String kind,
        @RequestParam(value = "contentsFile", required = false) MultipartFile file
    );
    
    @PostMapping("/api/contents/info")
    ContentResponse createContentInfo(@RequestBody ContentRequest requestDto);
    
    @PostMapping(value = "/api/contents/{id}/poster", consumes = "multipart/form-data")
    void uploadPoster(@PathVariable Long id, @RequestPart("contentsFile") MultipartFile file);
    
    @PutMapping("/api/contents/{id}")
    ContentResponse updateContentInfo(@PathVariable("id") Long id, @ModelAttribute ContentRequest requestDto);
    
    
    @DeleteMapping("/api/contents/{id}")
    void deleteContents(@PathVariable("id") Long id);
}
