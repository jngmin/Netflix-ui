package com.example.demo.dto.contents;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentListResponse {

	private List<ContentResponse> contents;
	private long totalCount;
}
