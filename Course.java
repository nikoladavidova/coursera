package com.example.demo.Course;

public class Course {
    private int id;
    private String title;
    private String organization;
    private String certificate_type;
    private String time;
    private double rating
            ;
    private int  reviews_num;
    private String  difficulty;
    private String students_enrolled;

    public Course(int id, String title, String organization, String certificate_type, String time, double rating, int reviews_num, String difficulty, String students_enrolled) {
        this.id = id;
        this.title = title;
        this.organization = organization;
        this.certificate_type = certificate_type;
        this.time = time;
        this.rating = rating;
        this.reviews_num = reviews_num;
        this.difficulty = difficulty;
        this.students_enrolled = students_enrolled;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOrganization() {
        return organization;
    }

    public String getCertificate_type() {
        return certificate_type;
    }

    public String getTime() {
        return time;
    }

    public double getRating() {
        return rating;
    }

    public int getReviews_num() {
        return reviews_num;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getStudents_enrolled() {
        return students_enrolled;
    }
}
