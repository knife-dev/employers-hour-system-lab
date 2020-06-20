package entities;

public class Task {
    private Long id;
    private Long userId;
    private Float hours;
    private String date;

    public Task(Long id, Long userId, Float hours, String date) {
        this.setId(id);
        this.setUserId(userId);
        this.setHours(hours);
        this.setDate(date);
    }

    public Task(Long userId, Float hours, String date) {
        this.setId(id);
        this.setUserId(userId);
        this.setHours(hours);
        this.setDate(date);
    }

    public Task(Long userId) {
        this.setUserId(userId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getHours() {
        return hours;
    }

    public void setHours(Float hours) {
        this.hours = hours;
    }

    public String toString() {
        return String.format("%d,%d,%.2f,%s", getId(), getUserId(), getHours(), getDate());
    }

}