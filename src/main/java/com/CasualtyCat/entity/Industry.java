package com.CasualtyCat.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("t_industry_code")
public class Industry {

    @Id
    private String id; // Consider String for ID
    @NotBlank(message = "Industry name cannot be blank")
    private String industryName;
    @Field("created_date")
    private LocalDate createdDate;

}