package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.client.ContentsClient;
import com.example.demo.entity.Contents;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.dto.contents.*;
@Slf4j
@Controller
public class ContentsController {

	@Autowired
	private ContentsClient contentsClient;

	@GetMapping("/movie")
	public String getMovieList(Model model) {
		List<ContentResponse> contentsList = contentsClient.getAllContents();
		List<String> genres = List.of("thriler", "romance", "action", "comedy", "horror");
		System.out.println("전체 콘텐츠 수: " + contentsList.size());

		// 장르별 콘텐츠 리스트를 Map으로 분류
		Map<String, List<ContentResponse>> contentsByGenre = new HashMap<>();
		for (String genre : genres) {
			List<ContentResponse> filtered = contentsList.stream().filter(c -> genre.equalsIgnoreCase(c.getGenre()))
					.collect(Collectors.toList());

			System.out.println("장르: " + genre + ", 콘텐츠 수: " + filtered.size());
			System.out.println("==> 저장 키: " + genre); // 여기 로그 확인

			contentsByGenre.put(genre, filtered);
		}
		System.out.println("=== 최종 contentsByGenre ===");
		contentsByGenre.forEach((k, v) -> System.out.println("장르: " + k + ", 콘텐츠 수: " + v.size()));

		model.addAttribute("genres", genres);
		model.addAttribute("contentsByGenre", contentsByGenre);
		return "common/contents/MoviePage";
	}

	@GetMapping("/contents/form")
	public String addContentsForm() {
		return "admin/contents/addContentsForm";
	}
/*
	@PostMapping("/contents/new")
	public String registerContents(   @RequestParam("title") String title,
	        @RequestParam("genre") String genre,
	        @RequestParam("director") String director,
	        @RequestParam("year") Integer year,
	        @RequestParam("running") Integer running,
	        @RequestParam("summary") String summary,
	        @RequestParam("kind") String kind,
	        @RequestParam(value = "contentsFile", required = false) MultipartFile contentsFile) {
	    ContentRequest requestDto = new ContentRequest(title, genre, director, year, running, summary, kind);
	    System.out.println("=== 받은 파라미터들 ===");
	    System.out.println("title: " + title);
	    System.out.println("genre: " + genre);
	    System.out.println("director: " + director);
	    System.out.println("year: " + year);
	    System.out.println("running: " + running);
	    System.out.println("summary: " + summary);
	    System.out.println("kind: " + kind);
	    System.out.println("file: " + (contentsFile != null ? contentsFile.getOriginalFilename() : "null"));
	    
	    System.out.println("=== 생성된 DTO ===");
	    System.out.println(requestDto);
		contentsClient.createContents(requestDto, contentsFile);

		return "redirect:/"; // 목록 페이지로 리디렉션
	}
*/
	@PostMapping("/contents/new")
	public String registerContents(
	    @RequestParam("title") String title,
	    @RequestParam("genre") String genre,
	    @RequestParam("director") String director,
	    @RequestParam("year") Integer year,
	    @RequestParam("running") Integer running,
	    @RequestParam("summary") String summary,
	    @RequestParam("kind") String kind,
	    @RequestParam(value = "contentsFile", required = false) MultipartFile contentsFile) {
	    
	    System.out.println("=== 받은 데이터 확인 ===");
	    System.out.println("title: " + title);
	    System.out.println("genre: " + genre);
	    
	    ContentRequest requestDto = new ContentRequest(title, genre, director, year, running, summary, kind);
	   
	    ContentResponse response = contentsClient.createContentInfo(requestDto);
	    contentsClient.uploadPoster(response.getId(), contentsFile);
	    
	    return "redirect:/";
	}
	
	@PostMapping("/contents/delete")
	public String deleteContents(@RequestParam Long id) {
		contentsClient.deleteContents(id);
		return "redirect:/";
	}

	@PostMapping("/contents/modifyForm")
	public String getContentsModifyForm(@RequestParam Long id, Model model) {
		ContentResponse content = contentsClient.getContentsById(id);
		model.addAttribute("contents", content);
		return "admin/contents/modifyContentsForm";
	}

	@PostMapping("/contents/modify")
	public String modifyContents(
			@RequestParam("id") Long id, 
			@ModelAttribute ContentRequest requestDto,
			@RequestPart(value = "contentsFile", required = false) MultipartFile contentsFile) {
		ContentResponse response =  contentsClient.updateContentInfo(id, requestDto);
		contentsClient.uploadPoster(response.getId(), contentsFile);
		return "redirect:/";
	}

	@GetMapping("/contents/detail")
	public String getContentsDetail(@RequestParam Long contents_id, Model model) {
		ContentResponse content = contentsClient.getContentsById(contents_id);
		model.addAttribute("content", content);
		return "common/contents/ContentsDetail";
	}

	@ResponseBody
	@PostMapping("/contents/checkPassword")
	public boolean checkPassword(@RequestParam Long contentid, @RequestParam String password) {
		// API에 맞게 check password 호출 로직 조정 필요 (현재 ContentsClient엔 해당 API 없음)
		// 예를 들어, 별도 API를 추가해야 할 수 있음
		return true; // 임시 처리
	}

}
