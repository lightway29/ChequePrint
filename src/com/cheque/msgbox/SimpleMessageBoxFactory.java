package com.cheque.msgbox;

/**
 *
 * @author Bhathi
 */
public class SimpleMessageBoxFactory {

    /**
     * create an object of a concrete MessageBox
     * @return {@link MessageBox) object
     */
    public static MessageBox createMessageBox() {
        return new JavaFxMessageBox();
    }
}
