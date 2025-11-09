package com.example.eco_engage.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class User {

    @Id
    private String id;

    @Field(name = "firstname")
    private String firstname;

    @Field(name = "lastname")
    private String lastname;

    @Field(name = "username")
    private String username;

    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

    @Field(name = "dob") // Date of birth
    private String dob; // Can use LocalDate if needed

    @Field(name = "gender")
    private String gender;
}
