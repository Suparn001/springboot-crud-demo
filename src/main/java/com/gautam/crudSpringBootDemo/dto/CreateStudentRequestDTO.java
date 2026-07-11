package com.gautam.crudSpringBootDemo.dto;


import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
    @NotBlank(message = "Name cannot be null/empty or blank")
    @Size(min = 2, max = 15, message = "Student name must be within 2 to 15 character long")
    private String name;

    @Min(value = 18, message = "Student must be at least 18 years old")
    @NotNull(message = "Age is required")
    private Integer age;

    @Email(message = "Student email must be valid")
    @NotBlank(message = "Student email cannot be blank")
    private String email;

    @NotNull(message = "Roll No. is required")
    private int rollNo;

    @NotBlank(message = "Subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
