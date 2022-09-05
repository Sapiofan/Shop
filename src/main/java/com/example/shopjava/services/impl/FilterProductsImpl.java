package com.example.shopjava.services.impl;

import com.example.shopjava.entities.product.*;
import com.example.shopjava.repos.*;
import com.example.shopjava.services.FilterProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilterProductsImpl implements FilterProducts {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private PhoneRepo phoneRepository;

    @Autowired
    private LaptopRepo laptopRepository;

    @Autowired
    private WatchRepo watchRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    @Transactional
    @Override
    public List<? extends Product> searchProducts(String keyword) {
        if (keyword != null) {
            return productRepository.searchProducts(keyword);
        }
        return phoneRepository.findAll();
    }

    @Override
    public List<Product> searchUncertainProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Map<String, List<String>> getDescTable(Product product) {
        if (product.getCategory().getId() == 1) {
            Filters filters = new Filters();
            filters.initPhoneChars();
            return filters.descriptionTablePhone;
        } else if (product.getCategory().getId() == 2) {
            Filters filters = new Filters();
            filters.initLaptopChars();
            return filters.descriptionTableLaptop;
        } else if (product.getCategory().getId() == 3) {
            Filters filters = new Filters();
            filters.initWatchChars();
            return filters.descriptionTableWatch;
        }
        return null;
    }

    @Override
    public List<? extends Product> sort(List<? extends Product> products, String sortType) {
        switch (sortType) {
            case "From cheap to expensive":
                return sortFromCheapToExp(products);
            case "From expensive to cheap":
                return sortFromExpToCheap(products);
            case "By popularity":
                return sortByPopularity(products);
            case "Novelties":
                return sortByNovelties(products);
            case "By name":
                return sortByName(products);
        }
        return null;
    }

    @Override
    @Transactional
    public Page<Product> getAllProducts(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, 10, Sort.by("name").ascending());
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getProductsWithDiscount() {
        return productRepository.discounts();
    }

    @Override
    public List<Product> getBestsellers() {
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Product::getSold).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategory(String category) {
        return categoryRepository.getCategoryByName(category);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Product> bestsellers() {
        List<Product> products = productRepository.findAll();
        return products.stream().sorted(Comparator.comparingInt(Product::getSold)).limit(40).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> descData(Product product) {
        Map<String, String> data = new HashMap<>();
        if (product.getCategory().getId() == 1) {
            Phone phone = phoneRepository.getPhoneById(product.getId());
            data.put("RAM slot", phone.getRam_slot() ? "RAM slot present" : "RAM slot doesn't present");
            data.put("Built-in memory", phone.getBuilt_in_memory());
            data.put("Cores", phone.getCores().toString());
            data.put("Processor", phone.getCpu());
            data.put("Screen refresh", phone.getScreen_refresh().toString());
            data.put("OS", phone.getOs());
            data.put("Main camera", phone.getMain_camera());
            data.put("Front camera", phone.getFront_camera());
            data.put("NFC", phone.getNfc() ? "NFC present" : "NFC doesn't present");
            data.put("Biometric security", phone.getBiometric_security());
            data.put("Wireless charger", phone.getWireless_charger() ? "Wireless charger present" : "Wireless charger doesn't present");
            data.put("Capacity", phone.getBattery());
        } else if (product.getCategory().getId() == 2) {
            Laptop laptop = laptopRepository.getById(product.getId());
            data.put("Screen diagonal", laptop.getScreen_diagonal());
            data.put("Matrix type", laptop.getMatrix_type());
            data.put("Screen resolution", laptop.getSc_resolution());
            data.put("Cores", laptop.getCores().toString());
            data.put("Processor series", laptop.getProcessor_series());
            data.put("Processor manufacturer", laptop.getProcessor_manufacturer());
            data.put("Storage", laptop.getStorage());
            data.put("Drive type", laptop.getDrive_type());
            data.put("Discrete graphics", laptop.getDiscrete_graphics());
            data.put("Video size", laptop.getVideo_size());
            data.put("Optical drive", laptop.getOptical_drive());
            data.put("Touch screen", laptop.getTouch_screen() ? "Touch screen present" : "Touch screen doesn't present");
            data.put("Color", laptop.getColor());
            data.put("Weight", laptop.getWeight().toString());
            data.put("OS", laptop.getInst_os());
        } else if (product.getCategory().getId() == 3) {
            Watch watch = watchRepository.getById(product.getId());
            data.put("Purpose", watch.getPurpose());
            data.put("Waterproof", watch.getWaterproof() ? "Waterproof present" : "Waterproof doesn't present");
            data.put("Touch screen", watch.getTouch_screen() ? "Touch screen present" : "Touch screen doesn't present");
            data.put("Call support", watch.getCall_support() ? "Call support present" : "Call support doesn't present");
            data.put("Music control", watch.getMusic_control() ? "Music control present" : "Music control doesn't present");
            data.put("Pulse measurement", watch.getPulse_measurement() ? "Pulse measurement present" : "Pulse measurement doesn't present");
            data.put("Sleep monitoring", watch.getSleep_monitoring() ? "Sleep monitoring present" : "Sleep monitoring doesn't present");
            data.put("Display shape", watch.getDisplay_shape());
            data.put("Display diagonal", watch.getDisplay_diagonal());
            data.put("Color", watch.getColor());
            data.put("Working hours", watch.getWorking_hours());
        }
        return data;
    }

    public List<? extends Product> sortFromCheapToExp(List<? extends Product> products) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public List<? extends Product> sortFromExpToCheap(List<? extends Product> products) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<? extends Product> sortByPopularity(List<? extends Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getSold)).collect(Collectors.toList());
    }

    public List<? extends Product> sortByNovelties(List<? extends Product> products) {
        return null;
    }

    public List<? extends Product> sortByName(List<? extends Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }
}
