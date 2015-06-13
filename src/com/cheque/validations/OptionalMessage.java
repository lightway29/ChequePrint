/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.validations;

/**
 *
 * @author Shashi De Silva
 */
public enum OptionalMessage {
      
    
    NEW_TABLE("New Table"),
    ADD_NEW_TABLE("Add new Table"),
    TABLE_NO("Table No"),
    //
    NEW_PRINT_INFORMATION("New Print Information"),
    ADD_NEW_PRINT_INFORMATION("Add new print Information"),
    PRINT_INFORMATION("Print Information"),
    //
    NEW_REASON("New Reason"),
    ADD_NEW_REASON("Add new Reason"),
    TITLE("Title"),
    //
    NEW_OTHER_CHARGE("New Other Charge"),
    ADD_NEW_OTHER_CHARGE("Add new Other Charge"),
    //
    NEW_ADVANCE_TITLE("New Advance Title"),
    ADD_NEW_ADVANCE_TITLE("Add new Advance Title"),
    //
    NEW_PROFESSION("New profession"),
    ADD_NEW_PROESSION("Add new profession"),
    PROFESSION_TITLE("Profession Title"),
    //
    NEW_TYPE("New Type"),
    ADD_NEW_TYPE("Add New Type"),
    //
    NEW_LANGUAGE("New Language"),
    ADD_NEW_LANGUAGE("Add new Language"),
    //
    NEW_TITLE("New Title"),
    ADD_NEW_TITLE("Add new Title"),
    //
    NEW_REMARK("New Remark"),
    ADD_NEW_REMARK("Add new remark"),
    REMARK("Remark"),
    //
    NEW_EXTRA_TYPE("New extra type"),
    ADD_NEW_EXTRA_TYPE("Add new extra type"),
    EXTRA_TYPE("Extra type"),
    //
    NEW_DESCRIPTION("New description"),
    ADD_NEW_DESCRIPTION("Add new description"),
    DESCRIPTION("description"),
    //
    NEW_ROOM_CATEGORY("New Room Category"),
    ADD_NEW_ROOM_CATEGORY("Add new Room Category"),
    //
    NEW_ROOM_LOCATION("New Room Location"),
    ADD_NEW_ROOM_LOCATION("Add new Room Location"),
    //
    NEW_MARKET("New Market"),
    ADD_NEW_MARKET("Add new Market"),
    //
    NEW_CATEGORY("New category"),
    ADD_NEW_CATEGORY("Add new category"),
    CATEGORY("category"),
    //
    NEW_ACCOUNT_NO("New Account No"),
    ADD_NEW_ACCOUNT_NO("Add new Account No"),
    ACCOUNTNO("Account No"),
    //
    NEW("New "),
    ADD_NEW("Add new ");
      
      
      private final String val;

    private OptionalMessage(String val) {

        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    public static OptionalMessage fromString(String val) {
        if (val != null) {
            for (OptionalMessage b : OptionalMessage.values()) {
                if (val.equalsIgnoreCase(b.val)) {

                    return b;
                }
            }
        }
        return null;
    }
}
