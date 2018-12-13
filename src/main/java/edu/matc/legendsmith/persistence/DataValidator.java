package edu.matc.legendsmith.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataValidator {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void validateSignUpData() {
        //All fields must not be empty

        //No empty spaces in the fields

        //Fields cannot exceed a certain length (20)
    }

    public void checkIfUnique() {

    }

    public void checkWhetherStringsMatch() {

    }
}
