package com.example.conditional2.beans;

public class DevProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
