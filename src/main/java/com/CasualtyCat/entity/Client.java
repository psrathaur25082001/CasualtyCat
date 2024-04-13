package com.CasualtyCat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("t_client_dtls")
public class Client {
    @Id
    @Field("client_id")
    private String clientId;
    private String name;
    private String designation;
    @Field("company_name")
    private String companyName;
    @Field("industry_id")
    private String industryId;
    @Field("email_id")
    private String emailId;
    private long mobile;
    @Field("duration_id")
    private String durationId;
    @Field("type_id")
    private String typeId;
    @LastModifiedDate
    @Field("create_date")
    private Date createDate;
}
