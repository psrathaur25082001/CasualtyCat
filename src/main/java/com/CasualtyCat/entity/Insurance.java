package com.CasualtyCat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("insurance")
public class Insurance {

    @Id
    private String id;
    @Field("company_name")
    private String companyName;
    private String address;
    @Field("company_logo")
    private String companyLogo;
    @Field("contact_number")
    private Long contactNumber;
    @Field("expiry_date")
    private Date expiryDate;
    @Field("duration_of_subscription")
    private  String durationOfSubscription;
    @Field("number_of_agent")
    private int numberOfAgent;
    @Field("payment_mode_id")
    private String paymentModeId;
    @Field("no_of_tokens")
    private int noOfTokens;
    @LastModifiedDate
    @Field("created_date")
    private Date createdDate;

}
