package com.example.interviewBot.model;

import java.util.Objects;

public class Teacher {
    private int id;
    private String name;
    private String specialization;
    private int experience;
    private double rating;

    public Teacher(int id, String name, String specialization, int experience, double rating) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.rating = rating;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }
    public double getRating() { return rating; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        if (specialization != null && experience > 0) {
            return String.format("%d. %s | %s | Опыт: %d лет | Рейтинг: %.1f",
                    id, name, specialization, experience, rating);
        }
        return String.format("%d. %s", id, name);
    }

    public String toShortString() {
        return String.format("%d. %s - %s", id, name, specialization);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        return id == teacher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}