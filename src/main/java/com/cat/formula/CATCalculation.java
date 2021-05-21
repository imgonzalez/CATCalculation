package com.cat.formula;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.functions.*;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.springframework.boot.SpringApplication;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.formula.functions.Irr.irr;

public class CATCalculation {

    Double amount;
    Integer payments;
    Double monthlyPayment;
    Double commission;
    Integer annualPeriods;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Integer getAnnualPeriods() {
        return annualPeriods;
    }

    public void setAnnualPeriods(Integer annualPeriods) {
        this.annualPeriods = annualPeriods;
    }

    public CATCalculation(Double amount, Integer payments, Double monthlyPayment, Double commission, Integer annualPeriods) {
        this.amount = amount;
        this.payments = payments;
        this.monthlyPayment = monthlyPayment;
        this.commission = commission;
        this.annualPeriods = annualPeriods;
    }

    private static DecimalFormat df2 = new DecimalFormat("#.#");


    public static void main(String[] args) {


        final double cat = cat(15000d, 24, 962.33d, 100d, 12);
        System.out.println("cat: "+ cat);


        System.out.println("cat : " + df2.format(cat) + "%");
    }

    private static Double evaluateIrr(final Double amount, final Integer payments, final Double monthlyPayment, final Double commission){
        try {
            final double[] values= new double[payments+1];

            values[0] = (amount-commission)*-1;
            for( int i = 1; i < values.length; i++){
                values[i] = monthlyPayment;
            }

            Irr irrClass = new Irr();

            double result = irrClass.irr(values);

            System.out.println("irr: "+result);

            return result;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static Double calculateCAT(final Double monthlyIrr, final Integer annualPeriods){
        try {

            return Math.pow(1d + monthlyIrr, annualPeriods) - 1d;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Double cat(final Double amount, final Integer payments, final Double monthlyPayment, final Double commission, final Integer annualPeriods){
        try {

            final Double monthlyIrr = evaluateIrr(amount, payments, monthlyPayment, commission);

//            final Double simpleAnnualIrr = monthlyIrr * 12;
//            System.out.println("simpleAnnualIrr: "+simpleAnnualIrr);

            double cat = calculateCAT(monthlyIrr,annualPeriods)*100;

            return cat;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Double cat(final CATCalculation data){
        try {

            final Double monthlyIrr = evaluateIrr(data.getAmount(), data.getPayments(), data.getMonthlyPayment(), data.getCommission());

//            final Double simpleAnnualIrr = monthlyIrr * 12;
//            System.out.println("simpleAnnualIrr: "+simpleAnnualIrr);

            double cat = calculateCAT(monthlyIrr,data.getAnnualPeriods())*100;

            return cat;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public  String getCat(){
        try {

            final Double monthlyIrr = evaluateIrr(this.getAmount(), this.getPayments(), this.getMonthlyPayment(), this.getCommission());

            double cat = calculateCAT(monthlyIrr,this.getAnnualPeriods())*100;

            return df2.format(cat) + "%";

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
