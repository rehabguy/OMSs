package com.rk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.util.List;

@Document(collection="Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private Long id;
    private String name;
    private List<String> possibleFacets;

}
