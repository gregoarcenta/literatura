package org.arcentales.literatura.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record BookData(Long id, String title,
                       List<AuthorData> authors,
                       List<String> summaries,
                       Map<String, String> formats,
                       @JsonProperty("download_count") Integer downloadCount) {
}
