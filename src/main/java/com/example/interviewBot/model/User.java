package com.example.interviewBot.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Teacher> selectedTeachers;
    private List<Course> selectedCourses;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.selectedTeachers = new ArrayList<>();
        this.selectedCourses = new ArrayList<>();
    }

    // Getters и Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Teacher> getSelectedTeachers() { return selectedTeachers; }
    public void setSelectedTeachers(List<Teacher> selectedTeachers) { this.selectedTeachers = selectedTeachers; }

    public List<Course> getSelectedCourses() { return selectedCourses; }
    public void setSelectedCourses(List<Course> selectedCourses) { this.selectedCourses = selectedCourses; }
}