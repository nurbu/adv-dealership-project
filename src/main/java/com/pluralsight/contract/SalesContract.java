package com.pluralsight.contract;

import com.pluralsight.dealership.Vehicle;

public class SalesContract extends Contract {

    /**
     * Handles all Sales contract functions
     */
    private boolean financeOption;
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, boolean financeOption, double salesTaxAmount) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.financeOption = financeOption;
        this.salesTaxAmount = 0.05;
        this.recordingFee = 100;
        processingFee = totalPrice > 10000 ? 295 : 495;
    }


    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 0;
        double interestRate = 0;
        if (financeOption) {
            if (getVehicleSold().getPrice() >= 10000) {
                numberOfPayments = 48;
                interestRate = 4.25 / 1200;
            } else {
                numberOfPayments = 24;
                interestRate = 5.25 / 1200;
            }

            double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
            monthlyPayment = Math.round(monthlyPayment * 100);
            monthlyPayment /= 100;
            return monthlyPayment;
        } else {
            return 0.0;
        }
    }
}
