package edu.matc.legendsmith.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataValidator {
    private String errorMsg;

    private final Logger logger = LogManager.getLogger(this.getClass());

    public DataValidator() {
        errorMsg = ""; //This stays blank if there is nothing wrong

    }

    public String checkWhetherStringIsEmpty(String text) {
        if (text.isEmpty()) {
            errorMsg = "Please enter your information in each field.";
        }

        return errorMsg;
    }

    public String checkWhetherStringSizeOver20(String text) {
        if (text.length() > 20) {
            errorMsg = "You cannot exceed 20 characters in length.";
        }

        return errorMsg;
    }


    public String checkForNoWhiteSpace(String text) {
        if (text.matches("\\s")) {
            errorMsg = "You cannot enter any white spaces.";
        }

        return errorMsg;
    }

    public String checkIfUnique(String text) {
        if () {

        }

        return errorMsg;
    }

    public String checkWhetherPasswordsMatch(String pass1, String pass2) {
        if (pass1.equals(pass2) == false) {
            errorMsg = "Please make sure your passwords match.";
        }

        return errorMsg;
    }
}
