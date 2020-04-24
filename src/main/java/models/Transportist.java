package main.java.models;

public class Transportist {

    // TODO Add Constructor, Getters & Setters

    private Integer userId;
    private String licensePlate;

    public Transportist(Integer userId, String licensePlate) {
        this.setUserId(userId);
        this.setLicensePlate(licensePlate);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }



}