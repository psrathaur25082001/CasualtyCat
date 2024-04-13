package com.CasualtyCat.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("t_client_type")
public class Type {

    @Id
    private String id;
    @NotBlank(message = "Industry name cannot be blank")
    private String type;

}
