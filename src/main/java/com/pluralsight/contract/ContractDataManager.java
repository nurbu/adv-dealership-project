package com.pluralsight.contract;

public class ContractDataManager {

    private Contract contract;

    public ContractDataManager(Contract contract) {

        this.contract = contract instanceof SalesContract ? (SalesContract) contract : (LeaseContract) contract;
    }


}
