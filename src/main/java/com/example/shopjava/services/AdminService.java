package com.example.shopjava.services;

import com.example.shopjava.entities.admin.AdminHome;

import java.util.List;

public interface AdminService {
    List<AdminHome> getBannerData();
    void addNewBannerState(List<AdminHome> adminHomeList);
}
