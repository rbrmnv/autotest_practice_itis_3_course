package com.example.UntitledTestSuite.model;

public class OrderAdressData {
    private String city;
    private String address;
    private String phoneNumber;
    private String zipPostalCode;

    public OrderAdressData(String city, String address, String phoneNumber, String zipPostalCode) {
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.zipPostalCode = zipPostalCode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }
}
