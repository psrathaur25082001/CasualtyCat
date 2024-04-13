package com.CasualtyCat.payload;

import com.CasualtyCat.entity.Duration;
import com.CasualtyCat.entity.Industry;
import com.CasualtyCat.entity.Type;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private String clientId;
    private String name;
    private String designation;
    private String companyName;
    private String industryId;
    @NotEmpty
    @Email
    private String emailId;
    @Digits(integer = 10, fraction = 0, message = "Please enter a valid 10-digit Mobile Number")
    private long mobile;
    private String durationId;
    private String typeId;
    private Date createDate;

}
