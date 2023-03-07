package com.bfs.springdatademo.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "games") // no longer use @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Game {
    @Id
    private ObjectId id;

    @ApiModelProperty(notes = "Game's title")
    private String title;

    @ApiModelProperty(notes = "Game's category")
    private String category;

    @ApiModelProperty(notes = "List of awards")
    private List<Award> awards = new ArrayList<>();
}
