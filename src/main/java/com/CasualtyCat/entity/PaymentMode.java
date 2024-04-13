package com.CasualtyCat.entity;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("t_payment_mode")
public class PaymentMode {
    private String paymentModeId;
    private String paymentModeName;
    @LastModifiedDate
    @Field("created_date")
    private Date createdDate;
}
