package com.tanvoid0.tanspring.service.tutorial;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tutorials")
public class Tutorial {
  @Id
  private String id;
  private String title;
  private String description;
  private boolean published;

  public Tutorial(String title, String description, boolean published) {
    this.title = title;
    this.description = description;
    this.published = published;
  }
}