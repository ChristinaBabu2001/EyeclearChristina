package com.eyeclear.test;
import com.eyeclear.Prescription;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrescriptionTest {
    private Prescription prescription;


    @BeforeEach
    public void setUp() {
        prescription = new Prescription();
         // Set a default prescID for the tests
    }
    @Test
    public void testCase1() {
        // Invalid optometrist: Too short
        prescription.setFirstName("Leen");
        prescription.setLastName("Smith");
        prescription.setAddress("123 Asr St, Springvalley, 12345, USA");
        prescription.setSphere(15.0f);
        prescription.setCylinder(2.0f);
        prescription.setAxis(90);
        prescription.setOptometrist("Dr. Jam");
        prescription.setExaminationDate(parseDate("12/05/23"));

        assertFalse(prescription.addPrescription());



        // Invalid LastName: Too short
        prescription.setFirstName("Dian");
        prescription.setLastName("Joe"); // Invalid: Too short
        prescription.setAddress("23 Loan St, Varigul, 5678, USA");
        prescription.setSphere(12.0f);
        prescription.setCylinder(2.5f);
        prescription.setAxis(70);
        prescription.setOptometrist("Dr. Maryanna");
        prescription.setExaminationDate(parseDate("03/05/23"));

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testCase2() {
        //all datas are valid
        prescription.setFirstName("Alex");
        prescription.setLastName("John");
        prescription.setAddress("9 Baker St, London, 35672, USA");
        prescription.setSphere(-5.0f);
        prescription.setCylinder(-1.0f);
        prescription.setAxis(170);
        prescription.setOptometrist("Oppor Alice");
        prescription.setExaminationDate(parseDate("23/09/21"));

        assertTrue(prescription.addPrescription());

        //all datas are valid
        prescription.setFirstName("Michael");
        prescription.setLastName("Chartwer");
        prescription.setAddress("23 Oak Street, Big City Mall, 45876, USA");
        prescription.setSphere(0.0f);
        prescription.setCylinder(-2.5f);
        prescription.setAxis(150);
        prescription.setOptometrist("Dr. Sanuytra Thomas");
        prescription.setExaminationDate(parseDate("08/09/23"));

        assertTrue(prescription.addPrescription());
        // Invalid: Exceeds allowed range for cylinder
        prescription.setFirstName("Amisha");
        prescription.setLastName("Cartsy");
        prescription.setAddress("56 High Street, Sammeria, 25816, USA");
        prescription.setSphere(10.5f);
        prescription.setCylinder(4.1f);
        prescription.setAxis(50);
        prescription.setOptometrist("Dr. Aanna Lee");
        prescription.setExaminationDate(parseDate("30/03/23"));

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testCase3() {

        // Invalid: Exceeds allowed range for sphere
        prescription.setFirstName("Aiswarya");
        prescription.setLastName("Lsen");
        prescription.setAddress("74 Main St, Lanstar, 83763, USA");
        prescription.setSphere(23.5f);
        prescription.setCylinder(-0.5f);
        prescription.setAxis(110);
        prescription.setOptometrist("Dr. Samantha Clara");
        prescription.setExaminationDate(parseDate("12/12/22"));

        assertFalse(prescription.addPrescription());

        // Invalid: Exceeds allowed range for cylinder
        prescription.setFirstName("Amisha");
        prescription.setLastName("Cartsy");
        prescription.setAddress("56 High Street, Sammeria, 25816, USA");
        prescription.setSphere(10.5f);
        prescription.setCylinder(4.1f);
        prescription.setAxis(50);
        prescription.setOptometrist("Dr. Aanna Lee");
        prescription.setExaminationDate(parseDate("30/03/23"));

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testCase4() {

        // Invalid FirstName: Too short
        prescription.setFirstName("Jin");
        prescription.setLastName("Jopse");
        prescription.setAddress("50 Murchison Crescent, 83643, USA");
        prescription.setSphere(13.5f);
        prescription.setCylinder(-0.4f);
        prescription.setAxis(120);
        prescription.setOptometrist("Dr. Naseer Rahman");
        prescription.setExaminationDate(parseDate("12/12/22"));

        assertFalse(prescription.addPrescription());

        // Invalid address: Too short
        prescription.setFirstName("Rebecca");
        prescription.setLastName("Moore");
        prescription.setAddress("92 Chet, 23456, USA");
        prescription.setSphere(1.5f);
        prescription.setCylinder(2.5f);
        prescription.setAxis(30);
        prescription.setOptometrist("Optometrist Sarah Thompson");
        prescription.setExaminationDate(parseDate("10/10/22"));

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testCase5() {

        // Invalid: Exceeds allowed range for axis
        prescription.setFirstName("Jonathan");
        prescription.setLastName("Philip");
        prescription.setAddress("45 Dingle Pla, Metropolis, NY, 10027, USA");
        prescription.setSphere(-11.0f);
        prescription.setCylinder(3.5f);
        prescription.setAxis(195); // Invalid: Exceeds allowed range
        prescription.setOptometrist("Dr. Michael Hawkings");
        prescription.setExaminationDate(parseDate("01/04/22"));

        assertFalse(prescription.addPrescription());

        prescription.setFirstName("Jaden");
        prescription.setLastName("Brownshipmarkewn"); // Invalid: Too long
        prescription.setAddress("89 Oakland Ave, East City, 54321, USA");
        prescription.setSphere(0.0f);
        prescription.setCylinder(0.0f);
        prescription.setAxis(0);
        prescription.setExaminationDate(parseDate("01/01/23"));
        prescription.setOptometrist("Optometrist David Hall");

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testCase6() {

        // Invalid date format
        prescription.setFirstName("Liam");
        prescription.setLastName("Payne");
        prescription.setAddress("89 Green Ave, Suburbia, USA");
        prescription.setSphere(11.5f);
        prescription.setCylinder(3.0f);
        prescription.setAxis(170);
        prescription.setOptometrist("Dr. Andrew Williamson");
        prescription.setExaminationDate(parseDate("25/80/2023")); // Invalid date format

        assertFalse(prescription.addPrescription());

        prescription.setFirstName("Chris");
        prescription.setLastName("Evans");
        prescription.setAddress("78 Pine St, Smalltown, TX, 75604, USA");
        prescription.setSphere(5.0f);
        prescription.setCylinder(2.0f);
        prescription.setAxis(120);
        prescription.setOptometrist("Dr Albertiann Anderson Philipson"); // Invalid: Too long
        prescription.setExaminationDate(parseDate("29/09/23"));

        assertFalse(prescription.addPrescription());
    }

    // Test Cases for addRemark

    @Test
    public void testAddRemark1() {

        // Invalid: remark Less than 6 words
        assertTrue(prescription.addRemark("Client informed glasses are very uncomfortable", "client"));

        assertTrue(prescription.addRemark("The patient needs to revisit the clinic next week for follow up", "optometrist"));


    }

    @Test
    public void testAddRemark2() {

        // Invalid category
        assertFalse(prescription.addRemark("Client requested for a refund for her booking", "receptionist"));

        assertFalse(prescription.addRemark("Lens prescription is not clear", "optometrist")); // Invalid: Less than 6 words
    }

    @Test
    public void testAddRemark3() {

        // Invalid: First letter of remark not uppercase
        assertFalse(prescription.addRemark("client upset due to lens issues", "client"));

        assertFalse(prescription.addRemark("The patient experienced nausea", "optometrist"));// Invalid: Less than 6 words

    }

    @Test
    public void testAddRemark4() {
        assertTrue(prescription.addRemark("Client was happy with the glasses and said the glasses fit well and felt comfortable", "client"));

        //all datas are valid
        assertTrue(prescription.addRemark("Client mentioned headaches when wearing glasses in sunlight", "client"));
        assertFalse(prescription.addRemark("The patient experienced nausea", "optometrist"));// Invalid: Less than 6 words
    }

    @Test
    public void testAddRemark5() {

        // Invalid remark: More than 20 words
        String longRemark = "The patient reported that since they started using the new glasses, their vision has improved significantly, especially when reading fine print in dim light conditions.";
        assertFalse(prescription.addRemark(longRemark, "client"));

        //invalid data:first letter not capital
        assertFalse(prescription.addRemark("optometrist advised the patient to start wearing the glasses permanently", "optometrist"));
    }


    @Test
    public void testAddRemark6() {

        //a prescription cannot have more than 2 remarks.
        assertTrue(prescription.addRemark("Client experiencing blurry vision while reading indoors", "optometrist"));
        assertTrue(prescription.addRemark("A new drop has been prescribed to the client", "optometrist"));
        assertFalse(prescription.addRemark("Clients needs to followup after a week", "client"));
    }
    // Helper method to parse date
    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            sdf.setLenient(false);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

}

