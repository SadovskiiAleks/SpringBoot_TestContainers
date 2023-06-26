package com.example.conditional2.beans;

public class ProductionProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
