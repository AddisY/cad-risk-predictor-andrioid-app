package models;

/**
 * Created by Gurme L on 3/18/2018.
 */
public class Doctor {
    private int yearsOfExperience;
    private String hospital;
    private User user;

    public Doctor(User user){
        this.user=user;
    }
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public int getYearsOfExperiance() {
        return yearsOfExperience;
    }

    public void setYearsOfExperiance(int yearsOfExperiance) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    private String fieldOfStudy;
}
