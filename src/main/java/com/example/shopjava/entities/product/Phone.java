package com.example.shopjava.entities.product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "phones")
public class Phone extends Product {

    @Column(nullable = false)
    private String series;

    @Column(nullable = false)
    private String built_in_memory;

    @Column(nullable = false)
    private Boolean ram_slot;

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private String os;

    @Column(nullable = false)
    private Boolean nfc;

    @Column(nullable = false)
    private String screen_diagonal;

    private String biometric_security;

    @Column(nullable = false)
    private String main_camera;

    @Column(nullable = false)
    private String front_camera;

    @Column(nullable = false)
    private String battery;

    private Boolean wireless_charger;

    @Column(nullable = false)
    private Integer cores;

    private Integer screen_refresh;

    public Phone() {
    }

    public Phone(String image, String name, Integer price, String brand, String payment, Float rating, Integer discount,
                 String gifts, Boolean isAvailable, Integer warranty, Date addedAt, Integer sold, Category category,
                 String series, String built_in_memory, Boolean RAM_slot, String CPU, String os, Boolean NFC,
                 String sc_diagonal, String biometric_security, String main_camera, String front_camera,
                 String battery, Boolean wireless_charger, Integer cores, Integer screen_refresh) {
        super(image, name, price, brand, payment, rating, discount, gifts, isAvailable, warranty, addedAt, sold, category);
        this.series = series;
        this.built_in_memory = built_in_memory;
        this.ram_slot = RAM_slot;
        this.cpu = CPU;
        this.os = os;
        this.nfc = NFC;
        this.screen_diagonal = sc_diagonal;
        this.biometric_security = biometric_security;
        this.main_camera = main_camera;
        this.front_camera = front_camera;
        this.battery = battery;
        this.wireless_charger = wireless_charger;
        this.cores = cores;
        this.screen_refresh = screen_refresh;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getBuilt_in_memory() {
        return built_in_memory;
    }

    public void setBuilt_in_memory(String built_in_memory) {
        this.built_in_memory = built_in_memory;
    }

    public Boolean getRam_slot() {
        return ram_slot;
    }

    public void setRam_slot(Boolean ram_slot) {
        this.ram_slot = ram_slot;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }


    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Boolean getNfc() {
        return nfc;
    }

    public void setNfc(Boolean nfc) {
        this.nfc = nfc;
    }

    public String getScreen_diagonal() {
        return screen_diagonal;
    }

    public void setScreen_diagonal(String screen_diagonal) {
        this.screen_diagonal = screen_diagonal;
    }

    public String getBiometric_security() {
        return biometric_security;
    }

    public void setBiometric_security(String biometric_security) {
        this.biometric_security = biometric_security;
    }

    public String getMain_camera() {
        return main_camera;
    }

    public void setMain_camera(String main_camera) {
        this.main_camera = main_camera;
    }

    public String getFront_camera() {
        return front_camera;
    }

    public void setFront_camera(String front_camera) {
        this.front_camera = front_camera;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public Boolean getWireless_charger() {
        return wireless_charger;
    }

    public void setWireless_charger(Boolean wireless_charger) {
        this.wireless_charger = wireless_charger;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Integer getScreen_refresh() {
        return screen_refresh;
    }

    public void setScreen_refresh(Integer screen_refresh) {
        this.screen_refresh = screen_refresh;
    }
}
