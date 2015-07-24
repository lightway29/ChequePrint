/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.Report;

/**
 *
 * @author lightway
 */
import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;

import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;

import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.PenBuilder;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;

import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;

import net.sf.dynamicreports.report.constant.Rotation;

import net.sf.dynamicreports.report.constant.VerticalAlignment;

import net.sf.dynamicreports.report.datasource.DRDataSource;

import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;

public class BandReport {

    public StyleBuilder boldCenteredStyle;

    public BandReport() {

        build();

    }

    private void build() {

        StyleBuilder labelStyle = stl.style()
                .bold()
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setUnderline(true);
//                .setLeftPadding(207)
//                .setTopPadding(5);

        StyleBuilder LabelbackgroundStyle = stl.style(boldCenteredStyle)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setLeftPadding(207);

        StyleBuilder dateStyle = stl.style()
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setLeftPadding(400)
                .setTopPadding(0);

        StyleBuilder accountStyle = stl.style()
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setLeftPadding(41)
                .setTopPadding(0);

        StyleBuilder amountWordStyle = stl.style()
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setLeftPadding(53)
                .setTopPadding(0);

        StyleBuilder amountStyle = stl.style()
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setLeftPadding(100)
                .setTopPadding(0);

        StyleBuilder dateBackgroundStyle = stl.style(boldCenteredStyle)
//                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);

        boldCenteredStyle = stl.style()
                .bold()
                .setHorizontalAlignment(HorizontalAlignment.CENTER);

        StyleBuilder backgroundStyle = stl.style(boldCenteredStyle)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setRotation(Rotation.LEFT);

        try {

            report()//create new report design

                    
                    .summary(createTextField("A/C PAYEE ONLY")
                            .setStyle(LabelbackgroundStyle))
                    .summary(createTextField("2015-10-10")
                            .setStyle(dateStyle))
                    .summary(createTextField("**CASH**")
                            .setStyle(accountStyle)
                            .setFixedDimension(444, 20))
                    .summary(createTextField(
                                    "**Five Hundred and sixty sevent thousand "
                                    + "two hundred and fifty only**")
                            .
                            setStyle(amountWordStyle)
                            .setFixedDimension(257, 72)
                            )
                    .summary(createTextField(
                                    "0.00")
                            .
                            setStyle(amountStyle))
                    .setDataSource(createDataSource())//set datasource

                    .setPageFormat(252, 595, PageOrientation.LANDSCAPE).show();//create and show report

        } catch (DRException e) {

            e.printStackTrace();

        }

    }

    private TextFieldBuilder<String> createTextField(String label) {

        return cmp.text(label);

    }

    private TextFieldBuilder<String> createLabel(String label) {

        return cmp.text(label).setStyle(boldCenteredStyle);

    }

    private JRDataSource createDataSource() {

        DRDataSource dataSource = new DRDataSource("column1", "column2");

        int row = 1;

        for (int i = 1; i <= 2; i++) {

            for (int j = 0; j < 50; j++) {

                dataSource.add("group" + i, "row " + row++);

            }

        }

        return dataSource;

    }

    public static void main(String[] args) {

        new BandReport();

    }

}
