package com.cheque.validations;


public interface Validatable {
    /**
     * validate the UI
     * @return is input valid 
     */
    boolean isValid();
    
    /**
     * clear input
     */
    void clearInput();
    
    /**
     * clear special validation styles
     */
    void clearValidations();
}
