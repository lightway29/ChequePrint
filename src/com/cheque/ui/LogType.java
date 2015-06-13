/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.ui;

/**
 *
 * @author Sumudu De Zoysa
 */
public enum LogType {
    INSERT("insert"), DELETE("delete"), UPDATE("update");
    private final String val;

    private LogType(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

}
