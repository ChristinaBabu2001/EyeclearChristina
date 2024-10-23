package com.eyeclear;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private static int idCounter = 1;
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Getters and Setters
    public Prescription() {
        this.prescID = idCounter++; // Assign unique ID and increment counter
    }
    public int getprescID() {
        return prescID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSphere() {
        return sphere;
    }

    public void setSphere(float sphere) {
        this.sphere = sphere;
    }

    public float getCylinder() {
        return cylinder;
    }

    public void setCylinder(float cylinder) {
        this.cylinder = cylinder;
    }

    public float getAxis() {
        return axis;
    }

    public void setAxis(float axis) {
        this.axis = axis;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getOptometrist() {
        return optometrist;
    }

    public void setOptometrist(String optometrist) {
        this.optometrist = optometrist;
    }

    public boolean addPrescription() {
        // Condition 1: Validate first and last name length
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false; // Fail if the names are invalid
        }
        // Condition 2: Validate address length
        if (address.length() < 20) {
            return false; // Fail if address is too short
        }
        // Condition 3: Validate sphere, axis, and cylinder ranges
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false; // Fail if any of the optical values are invalid
        }
        // Condition 4: Validate examination date format
        if (examinationDate == null) {
            return false; // Ensure the date is set
        }
        // Check if the date format is valid
        if (!validateDateFormat(examinationDate)) {
            return false; // Fail if the date format is invalid
        }
        // Condition 5: Validate optometrist name length
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false; // Fail if the optometrist's name is invalid
        }

        // If validation passes, write the data to a TXT file
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            writer.write(prescID + "," + firstName + "," + lastName + "," + address + "," + sphere + "," + cylinder + "," + axis + "," + sdf.format(examinationDate) + "," + optometrist + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Validate the date format to ensure it's in DD/MM/YY
    private boolean validateDateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false); // Strictly parse dates
        String dateStr = sdf.format(date);

        // Check if the date string matches the expected format
        return dateStr.matches("\\d{2}/\\d{2}/\\d{2}");
    }

    public boolean addRemark(String remark, String remarkType) {
        // Condition 1: Validate remark length and format
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(remark.charAt(0))) {
            return false; // Fail if remark is not in correct format
        }
        // Condition 2: Validate remark type
        if (!remarkType.equalsIgnoreCase("client") && !remarkType.equalsIgnoreCase("optometrist")) {
            return false; // Fail if the remark type is invalid
        }
        // Check if remark limit is exceeded
        if (postRemarks.size() >= 2) {
            return false; // Fail if there are already 2 remarks
        }

        postRemarks.add(remark); // Add remark to the list
        // Write remark to file
        try (FileWriter writer = new FileWriter("review.txt", true)) {
            writer.write(remarkType + ": " + remark + "\n");
            return true; // Return true if the remark is successfully added
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
