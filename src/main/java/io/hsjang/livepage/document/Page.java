package io.hsjang.livepage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor
public class Page {

    @Id 
    String name;
    String type;
    Object items;

    String owner;

}