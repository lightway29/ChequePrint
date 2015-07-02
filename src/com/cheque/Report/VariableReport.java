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

import java.math.BigDecimal;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;

import net.sf.dynamicreports.report.builder.VariableBuilder;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;

import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;

import net.sf.dynamicreports.report.constant.Calculation;

import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;

import net.sf.dynamicreports.report.datasource.DRDataSource;

import net.sf.dynamicreports.report.definition.ReportParameters;

import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;

public class VariableReport {

    public VariableReport(
            int accountWidth, int accountRow,
            int dateWidth, int dateRow,
            int cashWidth, int cashRow,
            int amountWordWidth, int amountWordRow,
            int amountWidth, int amountRow) {

        try {
            build(accountWidth, accountRow,
                    dateWidth, dateRow,
                    cashWidth, cashRow,
                    amountWordWidth, amountWordRow,
                    amountWidth, amountRow);
        } catch (DRException ex) {
            ex.printStackTrace();
        }

    }

//private void build() throws DRException {
    private void build(
            int accountWidth, int accountRow,
            int dateWidth, int dateRow,
            int cashWidth, int cashRow,
            int amountWordWidth, int amountWordRow,
            int amountWidth, int amountRow
    ) throws DRException {

        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.
                stringType());
//
        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity",
                "quantity", type.integerType());
//
        TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price",
                "unitprice", type.bigDecimalType());

        VariableBuilder<Integer> itemCount = variable(itemColumn,
                Calculation.COUNT);

        VariableBuilder<Integer> quantitySum = variable("quantitySum",
                quantityColumn, Calculation.SUM);

        VariableBuilder<Integer> priceSum = variable(new PriceExpression(
                quantityColumn, unitPriceColumn), Calculation.SUM);

        
        

 

 

 

 

        
        report()
                .variables(quantitySum)
                .title(
                        cmp.horizontalList(cmp.text("").setFixedWidth(accountWidth).
                                setFixedRows(accountRow), cmp.text("A/C PAYEE ONLY").
                                setFixedWidth(100)),
                        cmp.horizontalList(cmp.text("").setFixedWidth(dateWidth).
                                setFixedRows(dateRow), cmp.text("2015-10-10").
                                setFixedWidth(100)),
                        cmp.horizontalList(cmp.text("").setFixedWidth(cashWidth).
                                setFixedRows(cashRow), cmp.text("**CASH**").
                                setFixedWidth(100)),
                        cmp.horizontalList(cmp.text("").setFixedWidth(amountWordWidth).
                                setFixedRows(amountWordRow), cmp.text("**Zero**").
                                setFixedWidth(100)),
                        cmp.horizontalList(cmp.text("").setFixedWidth(amountWidth).
                                setFixedRows(amountRow), cmp.text("0.00").setFixedWidth(
                                        100)))
                //                   cmp.text(new QuantitySumTextExpression()).setEvaluationTime(Evaluation.REPORT),
                //                   
                //                   cmp.text(new UnitPriceSumTextExpression(unitPriceColumn)),
                //                   
                //                   cmp.horizontalList(cmp.text("SUM(quantity * unit price) =").setFixedWidth(150), cmp.text(priceSum).setPattern("#,###.00")))

                //           .pageFooter(Templates.footerComponent)

                //           .setDataSource(createDataSource())

                .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE).show();

    }

    private JRDataSource createDataSource() {

        DRDataSource dataSource = new DRDataSource("item", "quantity",
                "unitprice");

        for (int i = 0; i < 1; i++) {

            dataSource.add("Book", (int) (Math.random() * 10) + 1,
                    new BigDecimal(Math.random() * 100 + 1));

        }

        return dataSource;

    }

    private class QuantitySumTextExpression extends AbstractSimpleExpression<String> {

        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {

            Integer quantitySum = reportParameters.getValue("quantitySum");

            return "Quantity sum = " + quantitySum;

        }

    }

    private class UnitPriceSumTextExpression extends AbstractComplexExpression<String> {

        private static final long serialVersionUID = 1L;

        public UnitPriceSumTextExpression(
                TextColumnBuilder<BigDecimal> unitPriceColumn) {

            addExpression(variable(unitPriceColumn, Calculation.SUM));

        }

        @Override
        public String evaluate(List<?> values, ReportParameters reportParameters) {

            BigDecimal unitPriceSum = (BigDecimal) values.get(0);

            return "Unit price sum = " + type.bigDecimalType().valueToString(
                    unitPriceSum, reportParameters.getLocale());

        }

    }

    private class PriceExpression extends AbstractSimpleExpression<BigDecimal> {

        private static final long serialVersionUID = 1L;

        private TextColumnBuilder<Integer> quantityColumn;

        private TextColumnBuilder<BigDecimal> unitPriceColumn;

        public PriceExpression(TextColumnBuilder<Integer> quantityColumn,
                TextColumnBuilder<BigDecimal> unitPriceColumn) {

            this.quantityColumn = quantityColumn;

            this.unitPriceColumn = unitPriceColumn;

        }

        @Override
        public BigDecimal evaluate(ReportParameters reportParameters) {

            Integer quantity = reportParameters.getValue(quantityColumn);

            BigDecimal unitPrice = reportParameters.getValue(unitPriceColumn);

            return unitPrice.multiply(new BigDecimal(quantity));

        }

    }

}
