package com.springbootcrud.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

//    //--- For bidirectional relationship
//    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
//    private Customer customer;

    public Account(){}

    public Account(String accountType, String accountName, BigDecimal accountBalance) {
        this.accountType = accountType;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
