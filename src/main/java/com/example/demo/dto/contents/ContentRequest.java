package com.example.demo.dto.contents;

import java.time.LocalDateTime;

import com.example.demo.entity.Contents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentRequest {

	private String title;
	private String genre;
	private String director;
	private Integer year;
	private Integer running;
	private String summary;
	private String kind;

	public Contents toEntity(String posterImgName, String posterImgSavePath) {
		Contents content = new Contents();
		content.setTitle(title);
		content.setGenre(genre);
		content.setDirector(director);
		content.setYear(year);
		content.setRunning(running);
		content.setSummary(summary);
		content.setKind(kind);
		content.setHits(0);
		content.setRegDt(LocalDateTime.now());
		content.setPosterImgName(posterImgName);
		content.setPosterImgSavePath(posterImgSavePath);
		return content;
	}

	public void updateEntity(Contents content, String posterImgName, String posterImgSavePath) {
		content.setTitle(this.title);
		content.setGenre(this.genre);
		content.setDirector(this.director);
		content.setYear(this.year);
		content.setRunning(this.running);
		content.setSummary(this.summary);
		content.setKind(this.kind);
		content.setPosterImgName(posterImgName);
		content.setPosterImgSavePath(posterImgSavePath);
	}
}
