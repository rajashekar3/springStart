package com.example.springStart;

public class StudentResponse {

    private Long id;
    private String name;
    private Long courseId;
    private String courseName;


    public StudentResponse(Long id, String name, Long courseId, String courseName) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.courseName = courseName;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}