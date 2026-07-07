package com.gautam.crudSpringBootDemo.dto;


import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
    @NotBlank(message = "Name cannot be null/empty or blank")
    @Size(min = 2, max = 15, message = "Student name must be within 2 to 15 character long")
    private String name;

    @Min(value = 18)
    private int age;

    @Email
    private String email;

    @NotEmpty
    private int rollNo;
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
