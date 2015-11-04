/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.ui;

import java.awt.Container;
import java.util.*;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JRViewer;

public class ReportGenerator extends JFrame {

    JasperPrint print;

    public ReportGenerator(String fileName) {
        this(fileName, null);
    }

    public ReportGenerator(String fileName, HashMap parameter) {

        super("Report");

        try {
                print = JasperFillManager.fillReport(fileName, parameter);
                JRViewer jRViewer = new JRViewer(print);            
               

                Container c = getContentPane();
                c.add(jRViewer);

        } catch (Exception e) {
            e.getMessage();

        }
        setBounds(180, 30, 1000, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
   

    
}
