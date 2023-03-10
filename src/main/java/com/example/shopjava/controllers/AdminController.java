package com.example.shopjava.controllers;

import com.example.shopjava.entities.admin.AdminHome;
import com.example.shopjava.entities.another.Career;
import com.example.shopjava.entities.another.FAQ;
import com.example.shopjava.entities.contacts.Message;
import com.example.shopjava.entities.product.*;
import com.example.shopjava.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.*;

@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    public static final String EDIT_MODE = "editMode";
    public static final String FAQS = "faqs";
    public static final String ID = "id";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String TOTAL_ITEMS = "totalItems";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String KEYS = "keys";
    public static final String AVAILABLE_FUNCTION = "Present?";

    @Autowired
    private FaqService faqService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FilterProducts filterProducts;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private WatchService watchService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/home")
    public String getAdminHome(Model model) {
        bannersModel(model, adminService);
        return "admin-home";
    }

    @PostMapping(value = "/admin/home", params = "see")
    public String getHomeChanges(Model model,
                                 @RequestParam("link1") String link1, @RequestParam("link2") String link2,
                                 @RequestParam("banner11") String banner11, @RequestParam("banner12") String banner12,
                                 @RequestParam("banner13") String banner13, @RequestParam("banner21") String banner21,
                                 @RequestParam("banner22") String banner22, @RequestParam("banner23") String banner23,
                                 @RequestParam("productId1") Long productId1, @RequestParam("productId2") Long productId2) {
        return addBannersProps(model, link1, link2, banner11, banner12, banner13, banner21, banner22, banner23, productId1, productId2);
    }

    @PostMapping(value = "/admin/home", params = "save")
    public String saveHomeChanges(Model model,
                                  @RequestParam("link1") String link1, @RequestParam("link2") String link2,
                                  @RequestParam("banner11") String banner11, @RequestParam("banner12") String banner12,
                                  @RequestParam("banner13") String banner13, @RequestParam("banner21") String banner21,
                                  @RequestParam("banner22") String banner22, @RequestParam("banner23") String banner23,
                                  @RequestParam("productId1") Long productId1, @RequestParam("productId2") Long productId2) {

        List<AdminHome> adminHomeList = new ArrayList<>();
        String banner1Text = banner11 + "#" + banner12 + "#" + banner13;
        String banner2Text = banner21 + "#" + banner22 + "#" + banner23;
        adminHomeList.add(new AdminHome(1, link1, banner1Text, productId1));
        adminHomeList.add(new AdminHome(2, link2, banner2Text, productId2));
        adminService.addNewBannerState(adminHomeList);

        return addBannersProps(model, link1, link2, banner11, banner12, banner13, banner21,
                banner22, banner23, productId1, productId2);
    }

    @GetMapping("/admin/career")
    public String getAdminCareerFirst(Model model) {
        return getAdminCareer(model, 1);
    }

    @GetMapping("/admin/career/{pageNum}")
    public String getAdminCareer(Model model, @PathVariable("pageNum") int pageNum) {
        Page<Career> pageCareers = careerService.getCareers(pageNum);

        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pageCareers.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pageCareers.getTotalElements());
        model.addAttribute("careers", pageCareers.getContent());

        return "admin-career";
    }

    @GetMapping("/admin/career/{pageNum}/delete/{id}")
    public String deleteCareer(Model model, @PathVariable(ID) Long id, @PathVariable("pageNum") int pageNum) {
        careerService.deleteCareerUserById(id);
        Page<Career> pageCareers = careerService.getCareers(pageNum);
        List<Career> careers = pageCareers.getContent();

        if (careers.isEmpty() && pageNum != 1) {
            pageCareers = careerService.getCareers(pageNum - 1);
            careers = pageCareers.getContent();
        }

        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pageCareers.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pageCareers.getTotalElements());
        model.addAttribute("careers", careers);
        model.addAttribute("deletePath", true);

        return "admin-career";
    }

    @GetMapping("/admin/faq")
    public String getAdminFaq(Model model) {
        model.addAttribute(FAQS, faqService.getFaqs());
        model.addAttribute(EDIT_MODE, false);
        return "admin-faq";
    }

    @PostMapping(value = "/admin/faq", params = "addFaq")
    public String addFaq(Model model,
                         @RequestParam("question") String question,
                         @RequestParam("answer") String answer) {

        faqService.addFaq(new FAQ(question, answer));

        model.addAttribute(FAQS, faqService.getFaqs());
        model.addAttribute(EDIT_MODE, false);

        return "admin-faq";
    }

    @GetMapping(value = "/admin/faq/{id}")
    public String getEditFaq(Model model,
                             @RequestParam("ex-question") String question,
                             @RequestParam("ex-answer") String answer,
                             @PathVariable(ID) Long id) {

        model.addAttribute(FAQS, faqService.getFaqs());
        model.addAttribute(EDIT_MODE, true);
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        model.addAttribute(ID, id);

        return "admin-faq";
    }

    @PostMapping(value = "/admin/faq", params = "edit")
    public String postEditFaq(Model model,
                              @RequestParam("editQuestion") String question,
                              @RequestParam("editAnswer") String answer,
                              @RequestParam(ID) Long id) {

        faqService.editFaq(id, question, answer);

        log.info(question);
        log.info(answer);
        log.info("" + id);

        model.addAttribute(FAQS, faqService.getFaqs());
        model.addAttribute(EDIT_MODE, false);
        model.addAttribute(ID, id);

        return "admin-faq";
    }

    @GetMapping(value = "/admin/faq/delete/{id}")
    public String deleteFaq(Model model, @PathVariable(ID) Long id) {
        faqService.deleteById(id);
        log.info(id + "");

        model.addAttribute(FAQS, faqService.getFaqs());
        model.addAttribute(EDIT_MODE, false);
        model.addAttribute("deletePath", true);

        return "admin-faq";
    }

    @GetMapping("/admin/feedback")
    public String getFirstAdminFeedback(Model model) {
        return getAdminFeedback(model, 1);
    }

    @GetMapping("/admin/feedback/{pageNum}")
    public String getAdminFeedback(Model model, @PathVariable("pageNum") int pageNum) {
        Page<Message> pageContacts = contactService.contacts(pageNum);

        model.addAttribute("messages", pageContacts.getContent());
        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pageContacts.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pageContacts.getTotalElements());

        return "admin-feedback";
    }

    @GetMapping("/admin/feedback/{pageNum}/delete/{id}")
    public String deleteContact(Model model, @PathVariable(ID) Long id, @PathVariable("pageNum") int pageNum) {
        contactService.deleteById(id);

        Page<Message> pageContacts = contactService.contacts(pageNum);
        List<Message> messages = pageContacts.getContent();

        if (messages.isEmpty() && pageNum != 1) {
            pageContacts = contactService.contacts(pageNum - 1);
            messages = pageContacts.getContent();
        }

        model.addAttribute("messages", messages);
        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pageContacts.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pageContacts.getTotalElements());
        model.addAttribute("deletePath", true);

        return "admin-feedback";
    }

    @GetMapping("/admin/products")
    public String getAdminFirstProducts(Model model) {
        return getAdminProducts(model, 1);
    }

    @GetMapping("/admin/products/{pageNum}")
    public String getAdminProducts(Model model, @PathVariable("pageNum") int pageNum) {
        Page<Product> pageProducts = filterProducts.getAllProducts(pageNum);

        model.addAttribute("products", pageProducts.getContent());
        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pageProducts.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pageProducts.getTotalElements());

        return "admin-products";
    }

    @PostMapping(value = "/admin/products")
    public String findProducts(Model model, @RequestParam("searchKey") String search) {
        List<Product> products = new ArrayList<>();
        try {
            Product product = filterProducts.getProductById(Long.valueOf(search));
            products.add(product);
        } catch (NumberFormatException e) {
            products = filterProducts.searchUncertainProducts(search);
        }

        model.addAttribute("products", products);
        model.addAttribute(CURRENT_PAGE, 1);

        return "admin-products";
    }

    @GetMapping("/admin/products/{pageNum}/delete/{id}")
    public String deleteProduct(Model model, @PathVariable(ID) Long id, @PathVariable("pageNum") int pageNum) {
        filterProducts.deleteById(id);

        Page<Product> pageProducts = filterProducts.getAllProducts(pageNum);
        List<Product> products = pageProducts.getContent();

        if (products.isEmpty() && pageNum != 1) {
            pageProducts = filterProducts.getAllProducts(pageNum - 1);
            products = pageProducts.getContent();
        }

        model.addAttribute("products", products);
        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pageProducts.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pageProducts.getTotalElements());
        model.addAttribute("deletePath", true);

        return "admin-products";
    }

    @GetMapping("/admin/reviews")
    public String getAdminFirstReviews(Model model) {
        return getAdminReviews(model, 1);
    }

    @GetMapping("/admin/reviews/{pageNum}")
    public String getAdminReviews(Model model, @PathVariable("pageNum") int pageNum) {
        Page<Review> pagesReviews = reviewService.getAll(pageNum);

        model.addAttribute("reviews", pagesReviews.getContent());
        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pagesReviews.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pagesReviews.getTotalElements());

        return "admin-reviews";
    }

    @GetMapping("/admin/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("generalProduct", new Product());
        return "add-product";
    }

    @GetMapping("/admin/editProduct/{id}")
    public String editProduct(Model model, @PathVariable(ID) Long id, HttpServletRequest request) {
        Product product = filterProducts.getProductById(id);
        request.getSession().setAttribute("productEdit", product);

        model.addAttribute("product", product);
        model.addAttribute(EDIT_MODE, true);

        return "add-product";
    }

    @PostMapping(value = "/admin/products", params = "edit")
    public String postEdit(Model model, HttpServletRequest request,
                           @RequestParam("name") String name,
                           @RequestParam("category") String category,
                           @RequestParam("brand") String brand,
                           @RequestParam("price") String stringPrice,
                           @RequestParam("payment") String payment,
                           @RequestParam("warranty") Integer warranty,
                           @RequestParam(value = "available", required = false) boolean available,
                           @RequestParam(value = "gifts", required = false) String gifts,
                           @RequestParam(value = "discount", required = false) int discount,
                           @RequestParam("link") String link) {

        Product product = (Product) request.getSession().getAttribute("productEdit");
        product.setName(name);
        product.setCategory(filterProducts.getCategory(category));
        product.setBrand(brand);
        product.setPrice(Integer.valueOf(stringPrice));
        product.setPayment(payment);
        product.setWarranty(warranty);
        product.setAvailable(available);
        product.setGifts(gifts);
        product.setDiscount(discount);
        product.setImage(link);
        filterProducts.saveProduct(product);
        request.removeAttribute("productEdit");
        return getAdminFirstProducts(model);
    }

    @PostMapping("/admin/addProduct")
    public String generalProduct(Model model, HttpServletRequest request,
                                 @RequestParam("name") String name,
                                 @RequestParam("category") String category,
                                 @RequestParam("brand") String brand,
                                 @RequestParam("price") String stringPrice,
                                 @RequestParam("payment") String payment,
                                 @RequestParam("warranty") Integer warranty,
                                 @RequestParam(value = "available", required = false) boolean available,
                                 @RequestParam(value = "gifts", required = false) String gifts,
                                 @RequestParam(value = "discount", required = false) int discount,
                                 @RequestParam("link") String link
    ) {

        stringPrice = stringPrice.replace(" ", "");
        stringPrice = stringPrice.replace(",", ".");
        int price;
        try {
            price = Integer.parseInt(stringPrice);
        } catch (NumberFormatException e) {
            log.info("" + e);
            price = 0;
        }
        Product product = new Product(link, name, price, brand, payment, (float) 0, discount, gifts, available, warranty,
                Date.from(Instant.now()), 0, filterProducts.getCategory(category));
        request.getSession().setAttribute("product", product);
        switch (category) {
            case "Phones": {
                Map<String, List<String>> phoneFilters = phoneService.getPhoneCharacteristics();
                model.addAttribute(KEYS, phoneFilters.keySet());
                model.addAttribute("phoneFilters", phoneFilters);
                break;
            }
            case "Laptops": {
                Map<String, List<String>> laptopFilters = laptopService.getLaptopCharacteristics();
                model.addAttribute(KEYS, laptopFilters.keySet());
                model.addAttribute("laptopFilters", laptopFilters);
                break;
            }
            case "Watches": {
                Map<String, List<String>> watchFilters = watchService.getWatchCharacteristics();
                model.addAttribute(KEYS, watchFilters.keySet());
                model.addAttribute("watchFilters", watchFilters);
                break;
            }
        }
        model.addAttribute("category", category);

        return "add-certain-product";
    }

    @PostMapping(value = "/admin/products", params = "phone")
    public String savePhone(Model model, HttpServletRequest request,
                            @RequestParam("Brand") String brand,
                            @RequestParam("Built-in memory") String built_in_memory,
                            @RequestParam("OS") String os,
                            @RequestParam("RAM slot") String ram_slot,
                            @RequestParam("CPU") String cpu,
                            @RequestParam("Screen diagonal") String screen_diagonal,
                            @RequestParam("Biometric security") String biometric_security,
                            @RequestParam("Cores") String cores,
                            @RequestParam("Wireless charger") String wireless_charger,
                            @RequestParam("NFC") String nfc,
                            @RequestParam("Series") String series,
                            @RequestParam("Screen refresh") String screen_refresh,
                            @RequestParam("mainC") String mainC,
                            @RequestParam("frontC") String frontC,
                            @RequestParam("battery") String battery
    ) {

        Product product = (Product) request.getSession().getAttribute("product");
        boolean ram_slotB = !ram_slot.contains(AVAILABLE_FUNCTION);
        boolean NFC = !nfc.contains(AVAILABLE_FUNCTION);
        boolean wc = !wireless_charger.contains(AVAILABLE_FUNCTION);
        Phone phone = new Phone(product.getImage(), product.getName(), product.getPrice(), product.getBrand(),
                product.getPayment(), product.getRating(), product.getDiscount(), product.getGifts(), product.getAvailable(),
                product.getWarranty(), Date.from(Instant.now()), product.getSold(), product.getCategory(), series,
                built_in_memory, ram_slotB, cpu, os, NFC, screen_diagonal, biometric_security, mainC, frontC,
                battery, wc, Integer.valueOf(cores), Integer.valueOf(screen_refresh));
        phoneService.savePhone(phone);
        request.removeAttribute("product");

        return getAdminFirstProducts(model);
    }

    @PostMapping(value = "/admin/products", params = "laptop")
    public String saveLaptop(Model model, HttpServletRequest request,
                             @RequestParam("Brand") String brand,
                             @RequestParam("Screen diagonal") String sc_diagonals,
                             @RequestParam("Device type") String deviceType,
                             @RequestParam("RAM") String ram,
                             @RequestParam("Cores") String core,
                             @RequestParam("Processor series") String processor_series,
                             @RequestParam("Drive type") String drive_type,
                             @RequestParam("Discrete graphics") String discrete_graphics,
                             @RequestParam("Series") String series,
                             @RequestParam("Installed OS") String inst_os,
                             @RequestParam("Storage") String storage,
                             @RequestParam("Screen refresh") String screen_refresh,
                             @RequestParam("Color") String color,
                             @RequestParam("Video size") String video_size,
                             @RequestParam("Processor manufacturer") String processorManufacturers,
                             @RequestParam("weight") String weight) {
        Product product = (Product) request.getSession().getAttribute("product");

        Laptop laptop = new Laptop(product.getImage(), product.getName(), product.getPrice(), product.getBrand(),
                product.getPayment(), product.getRating(), product.getDiscount(), product.getGifts(), product.getAvailable(),
                product.getWarranty(), Date.from(Instant.now()), product.getSold(), product.getCategory(),
                deviceType, sc_diagonals, processorManufacturers, Integer.valueOf(core), processor_series,
                ram, drive_type, discrete_graphics, series, inst_os, storage, Integer.valueOf(screen_refresh),
                "", color, video_size, "", "", Float.valueOf(weight), false);
        laptopService.saveLaptop(laptop);
        request.removeAttribute("product");

        return getAdminFirstProducts(model);
    }

    @PostMapping(value = "/admin/products", params = "watch")
    public String saveWatch(Model model, HttpServletRequest request,
                            @RequestParam("Brand") String brand,
                            @RequestParam("Shape") String shape,
                            @RequestParam("Waterproof") String waterproof,
                            @RequestParam("Call support") String call_support,
                            @RequestParam("Music control") String music_control,
                            @RequestParam("Pulse measurement") String pulse_measurement,
                            @RequestParam("Step counting") String step_counting,
                            @RequestParam("Sleep monitoring") String sleep_monitoring,
                            @RequestParam("Color") String color,
                            @RequestParam("Working hours") String working_hours,
                            @RequestParam("Display diagonal") String display_diagonal,
                            @RequestParam("Touch screen") String touch_sc,
                            @RequestParam("purpose") String purpose) {
        Product product = (Product) request.getSession().getAttribute("product");

        boolean touch_screen = touch_sc.contains(AVAILABLE_FUNCTION);
        boolean wp = !waterproof.contains(AVAILABLE_FUNCTION);
        boolean cs = !call_support.contains(AVAILABLE_FUNCTION);
        boolean mc = !music_control.contains(AVAILABLE_FUNCTION);
        boolean pm = !pulse_measurement.contains(AVAILABLE_FUNCTION);
        boolean sc = !step_counting.contains(AVAILABLE_FUNCTION);
        boolean sm = !sleep_monitoring.contains(AVAILABLE_FUNCTION);

        Watch watch = new Watch(product.getImage(), product.getName(), product.getPrice(), product.getBrand(),
                product.getPayment(), product.getRating(), product.getDiscount(), product.getGifts(), product.getAvailable(),
                product.getWarranty(), Date.from(Instant.now()), product.getSold(), product.getCategory(),
                purpose, shape, touch_screen, wp, cs, mc, pm, sc, sm, color, working_hours, display_diagonal);

        watchService.saveWatch(watch);
        request.removeAttribute("product");

        return getAdminFirstProducts(model);
    }

    @GetMapping(value = "/admin/reviews/{pageNum}/delete/{id}")
    public String deleteReview(Model model, @PathVariable(ID) Long id, @PathVariable("pageNum") int pageNum) {
        reviewService.deleteById(id);

        Page<Review> pagesReviews = reviewService.getAll(pageNum);
        List<Review> reviews = pagesReviews.getContent();

        if (reviews.isEmpty() && pageNum != 1) {
            pagesReviews = reviewService.getAll(pageNum - 1);
            reviews = pagesReviews.getContent();
        }

        model.addAttribute("reviews", reviews);
        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, pagesReviews.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, pagesReviews.getTotalElements());
        model.addAttribute("deletePath", true);

        return "admin-reviews";
    }

    @PostMapping("/admin/reviews")
    public String searchReviews(Model model, @RequestParam("search") String searchKey) {
        List<Review> reviews;
        try {
            reviews = reviewService.findReviewsByProduct(Long.valueOf(searchKey));
        } catch (NumberFormatException e) {
            reviews = reviewService.findReviewsByUser(searchKey);
        }
        model.addAttribute("reviews", reviews);

        return "admin-reviews";
    }

    static void bannersModel(Model model, AdminService adminService) {
        List<AdminHome> banners = adminService.getBannerData();

        model.addAttribute("link1", banners.get(0).getLink());
        String[] banner1Text = banners.get(0).getText().split("#");
        model.addAttribute("banner11", banner1Text[0]);
        model.addAttribute("banner12", banner1Text[1]);
        model.addAttribute("banner13", banner1Text[2]);
        model.addAttribute("productId1", banners.get(0).getProductId());

        model.addAttribute("link2", banners.get(1).getLink());
        String[] banner2Text = banners.get(1).getText().split("#");
        model.addAttribute("banner21", banner2Text[0]);
        model.addAttribute("banner22", banner2Text[1]);
        model.addAttribute("banner23", banner2Text[2]);
        model.addAttribute("productId2", banners.get(1).getProductId());
    }

    private String addBannersProps(Model model, @RequestParam("link1") String link1,
                                   @RequestParam("link2") String link2, @RequestParam("banner11") String banner11,
                                   @RequestParam("banner12") String banner12, @RequestParam("banner13") String banner13,
                                   @RequestParam("banner21") String banner21, @RequestParam("banner22") String banner22,
                                   @RequestParam("banner23") String banner23,
                                   @RequestParam("productId1") Long productId1, @RequestParam("productId2") Long productId2) {

        model.addAttribute("link1", link1);
        model.addAttribute("link2", link2);
        model.addAttribute("banner11", banner11);
        model.addAttribute("banner12", banner12);
        model.addAttribute("banner13", banner13);
        model.addAttribute("banner21", banner21);
        model.addAttribute("banner22", banner22);
        model.addAttribute("banner23", banner23);
        model.addAttribute("productId1", productId1);
        model.addAttribute("productId2", productId2);

        return "admin-home";
    }
}
