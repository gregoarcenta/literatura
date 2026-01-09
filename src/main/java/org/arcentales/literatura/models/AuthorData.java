package org.arcentales.literatura.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorData(String name,
                         @JsonProperty("birth_year") Integer birthYear,
                         @JsonProperty("death_year") Integer deathYear) {
}
