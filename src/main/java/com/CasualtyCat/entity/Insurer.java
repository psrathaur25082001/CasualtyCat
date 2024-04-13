package com.CasualtyCat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("insurer")
public class Insurer {
    @Id
    private String id;
    @Field("company_name")
    private String companyName;
    private String address;
    private String companyLogo;
    private long contactNumber;
    private Date linkExpiryDate;
    private  String durationOfSubscription;
    private int noOfAgents;
    private String paymentMode;
    private int noOfTokens;

}
