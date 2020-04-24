package main.java.models;

public class Transportist {

    // TODO Add Constructor, Getters & Setters

    private Long userId;
    private String licensePlate;

    public Transportist(Long userId, String licensePlate) {
        this.setUserId(userId);
        this.setLicensePlate(licensePlate);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }



}