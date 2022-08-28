package com.example.shopjava.services.impl;

import com.example.shopjava.entities.admin.AdminHome;
import com.example.shopjava.repos.AdminRepo;
import com.example.shopjava.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    @Transactional
    public List<AdminHome> getBannerData() {
        List<AdminHome> banners = new ArrayList<>();
        List<AdminHome> adminHomeList = adminRepo.findAll(Sort.by("date").descending());
        for (int i = 1; i <= 2; i++) {
            for (AdminHome adminHome : adminHomeList) {
                if(adminHome.getImageNumber() == i){
                    banners.add(adminHome);
                    break;
                }
            }
        }
        return banners;
    }

    @Override
    @Transactional
    public void addNewBannerState(List<AdminHome> adminHomeList) {
        List<AdminHome> dbAdminHomeList = adminRepo.findAll(Sort.by("date").descending());
        boolean flag = false;
        for (AdminHome adminHome : adminHomeList) {
            for (AdminHome home : dbAdminHomeList) {
                if(adminHome.equals(home)){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                adminRepo.save(adminHome);
            }
        }
    }
}
