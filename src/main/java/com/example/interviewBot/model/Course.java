package com.example.interviewBot.model;

import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private String level;
    private int duration;
    private String topic;

    public Course(int id, String name, String level, int duration, String topic) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.duration = duration;
        this.topic = topic;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getLevel() { return level; }
    public int getDuration() { return duration; }
    public String getTopic() { return topic; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLevel(String level) { this.level = level; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setTopic(String topic) { this.topic = topic; }

    @Override
    public String toString() {
        if (level != null && duration > 0) {
            return String.format("%d. %s | %s | %d часов | Тема: %s",
                    id, name, level, duration, topic);
        }
        return String.format("%d. %s", id, name);
    }

    public String toShortString() {
        return String.format("%d. %s (%s)", id, name, level);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}