package com.example.demo.dto.contents;

import com.example.demo.entity.Contents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentResponse {

	private Long id;
	private String title;
	private String genre;
	private String director;
	private Integer year;
	private Integer running;
	private String summary;
	private Integer hits;
	private String kind;
	private String posterImgUrl;

	public ContentResponse(Contents content, String baseUrl, String bucketName) {
		this.id = content.getId();
		this.title = content.getTitle();
		this.genre = content.getGenre();
		this.director = content.getDirector();
		this.year = content.getYear();
		this.running = content.getRunning();
		this.summary = content.getSummary();
		this.kind = content.getKind();
		this.posterImgUrl = String.format("%s/%s/%s", baseUrl, bucketName, content.getPosterImgSavePath());
	}
}
