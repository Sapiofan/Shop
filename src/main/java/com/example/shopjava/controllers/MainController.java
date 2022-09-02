package com.example.shopjava.controllers;

import com.example.shopjava.configs.security.CustomUserDetailsService;
import com.example.shopjava.entities.another.Career;
import com.example.shopjava.entities.another.FAQ;
import com.example.shopjava.entities.product.*;
import com.example.shopjava.entities.user.Transaction;
import com.example.shopjava.entities.contacts.Contact;
import com.example.shopjava.entities.user.User;
import com.example.shopjava.services.FindMaxProduct;
import com.example.shopjava.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private FilterProducts filterProducts;

    @Autowired
    private CareerService careerService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private FaqService faqService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private WatchService watchService;

    @Autowired
    private FindMaxProduct utils;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String getHomePage(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);

        AdminController.bannersModel(model, adminService);

        List<Product> bestsellers = filterProducts.bestsellers().stream().limit(5).collect(Collectors.toList());
        model.addAttribute("bestsellers", bestsellers);

        return "home";
    }

    @PostMapping(value = "/", params = "registration")
    public String registration(@RequestParam("email") String email,
                               @RequestParam("psw") String psw,
                               @RequestParam("psw-repeat") String pswRepeat,
                               HttpServletRequest request,
                               Model model
    ) {
        String result = userDetailsService.signUp(email, psw, pswRepeat, request);
        if (!result.isEmpty()) {
            model.addAttribute("error", result);
            return getHomePage(model, null);
        }
        return login(email, psw, request, model);
    }

    @PostMapping(value = "/", params = "login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("psw") String psw,
                        HttpServletRequest request,
                        Model model
    ) {
        String res = userDetailsService.signIn(email, psw, request);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(!res.isEmpty()){
            getUserPreferences(model, SecurityContextHolder.getContext().getAuthentication());
        }
        model.addAttribute("error", res);
        return getHomePage(model, securityContext.getAuthentication());
    }

    @PostMapping(value = "/", params = "logout")
    public String logout(Model model) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return getHomePage(model, null);
    }

    @GetMapping("/searching")
    public String filtersPage(Model model, @RequestParam("search") String searchKey, Authentication authentication) {
        if (utils.checkAuth(authentication))
            model.addAttribute("isAuthenticated", true);
        getUserPreferences(model, authentication);
        List<? extends Product> products = filterProducts.searchProducts(searchKey);
        model.addAttribute("products", products);
        model.addAttribute("searchKey", searchKey);

        model.addAttribute("seacrhBool", true);
        return "filters";
    }

    @GetMapping("/phones")
    public String phoneFilters(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<Phone> phones = phoneService.getAllPhones();
        Map<String, List<String>> phoneFilters = phoneService.getPhoneCharacteristics();
        Set<String> keys = phoneFilters.keySet();
        LinkedHashSet<String> filters = new LinkedHashSet<>();
        int max = 0;
        Phone phone = utils.max(phoneService.getAllPhones());
        if(phone != null)
            max = utils.max(phones).getPrice();

        filterPostModel(model, "Phones", phoneFilters, keys, filters, 0, max, "From expensive to cheap");
        model.addAttribute("products", phones);
        model.addAttribute("max", max);
        return "filters";
    }

    @PostMapping("/phones")
    public String phoneFiltersPost(Model model, Authentication authentication,
                                   @RequestParam(value = "filter-name", required = false) LinkedHashSet<String> filters,
                                   @RequestParam("input-min") Integer minValue, @RequestParam("input-max") Integer maxValue,
                                   @RequestParam("sort") String sortType) {
        getUserPreferences(model, authentication);
        Map<String, List<String>> phoneFilters = phoneService.getPhoneCharacteristics();
        Set<String> keys = phoneFilters.keySet();
        List<? extends Product> phones = phoneService.phones(filters, phoneFilters, minValue, maxValue);
        phones = filterProducts.sort(phones, sortType);
        Phone phone = utils.max(phoneService.getAllPhones());
        if(phone != null)
            model.addAttribute("max", phone.getPrice());
        filterPostModel(model, "Phones", phoneFilters, keys, filters, minValue, maxValue, sortType);
        model.addAttribute("products", phones);
        model.addAttribute("maxValue", maxValue);
        return "filters";
    }

    @GetMapping("/laptops")
    public String laptopFilters(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<Laptop> laptops = laptopService.getAllLaptops();
        Map<String, List<String>> laptopFilters = laptopService.getLaptopCharacteristics();
        Set<String> keys = laptopFilters.keySet();
        LinkedHashSet<String> filters = new LinkedHashSet<>();
        Laptop laptop = utils.maxLaptop(laptops);
        int max = 0;
        if(laptop != null)
            max = utils.maxLaptop(laptops).getPrice();

        filterPostModel(model, "Laptops", laptopFilters, keys, filters, 0, max, "From expensive to cheap");
        model.addAttribute("products", laptops);
        return "filters";
    }

    @PostMapping("/laptops")
    public String laptopFiltersPost(Model model, Authentication authentication,
                                    @RequestParam(value = "filter-name", required = false) LinkedHashSet<String> filters,
                                    @RequestParam("input-min") Integer minValue, @RequestParam("input-max") Integer maxValue,
                                    @RequestParam("sort") String sortType) {
        getUserPreferences(model, authentication);
        Map<String, List<String>> laptopFilters = laptopService.getLaptopCharacteristics();
        Set<String> keys = laptopFilters.keySet();
        List<Laptop> laptops = laptopService.laptops(filters, laptopFilters, minValue, maxValue);
        Laptop laptop = utils.maxLaptop(laptops);
        int max = 0;
        if(laptop != null)
            max = laptop.getPrice();
        filterPostModel(model, "Laptops", laptopFilters, keys, filters, minValue, maxValue, sortType);
        model.addAttribute("products", laptops);
        model.addAttribute("max", max);
        return "filters";
    }

    @GetMapping("/product/{id}")
    public String getDescription(Model model, Authentication authentication,
                                 @PathVariable("id") Long id) {
        getUserPreferences(model, authentication);
        List<Review> reviews = reviewService.findReviewsByProduct(id);
        Integer rec = reviewService.calculateRecommended(reviews);
        Product product = filterProducts.getProductById(id);
        Map<String, List<String>> descTable = filterProducts.getDescTable(product);
        Set<String> descTableKeys = descTable.keySet();
        if (authentication != null)
            model.addAttribute("user", userDetailsService.getUserByEmail(authentication.getName()));
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("recommended", rec);
        model.addAttribute("descTable", descTable);
        model.addAttribute("descTableKeys", descTableKeys);
        model.addAttribute("descData", filterProducts.descData(product));
        return "description";
    }

    private void getUserPreferences(Model model, Authentication authentication) {
        if (utils.checkAuth(authentication)) {
            model.addAttribute("isAuthenticated", true);
            User user = userDetailsService.getUserByEmail(authentication.getName());
            model.addAttribute("favoriteProducts", user.getFavorite().getFavoriteProducts());
            model.addAttribute("cartProducts", user.getCart().getCartProducts());
            model.addAttribute("total", user.getCart().getTotalPrice());
        }
    }

    @PostMapping(value = "/product/{id}", params = "reviewSend")
    public String sendReview(Model model, Authentication authentication,
                             @PathVariable("id") Long id, @RequestParam("name") String name,
                             @RequestParam("rate") Integer rate, @RequestParam("review") String review,
                             @RequestParam("recommend") Boolean recommend) {
        getUserPreferences(model, authentication);
        if (authentication != null) {
            User user = userDetailsService.getUserByEmail(authentication.getName());
            user.setName(name);
            userDetailsService.saveUser(user);
        }
        Phone phone = phoneService.getPhoneById(id);
        boolean flag = reviewService.addReview(rate, review, recommend, authentication, id);
        List<Review> reviews = reviewService.findReviewsByProduct(id);

        model.addAttribute("product", phone);
        model.addAttribute("reviewExists", flag);
        model.addAttribute("reviews", reviews);
        model.addAttribute("recommended", reviewService.calculateRecommended(reviews));
        return "description";
    }

    @GetMapping("/watches")
    public String watchesFilters(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<Watch> watches = watchService.getAllWatches();
        Map<String, List<String>> watchFilters = watchService.getWatchCharacteristics();
        Set<String> keys = watchFilters.keySet();
        LinkedHashSet<String> filters = new LinkedHashSet<>();
        Watch watch = utils.maxWatch(watches);
        int max = 0;
        if(watch != null)
            max = watch.getPrice();

        filterPostModel(model, "Watches", watchFilters, keys, filters, 0, max, "From expensive to cheap");
        model.addAttribute("products", watches);
        model.addAttribute("max", max);
        return "filters";
    }

    @PostMapping("/watches")
    public String watchesFiltersPost(Model model, Authentication authentication,
                                     @RequestParam(value = "filter-name", required = false) LinkedHashSet<String> filters,
                                     @RequestParam("input-min") Integer minValue, @RequestParam("input-max") Integer maxValue,
                                     @RequestParam("sort") String sortType) {
        getUserPreferences(model, authentication);
        Map<String, List<String>> watchFilters = watchService.getWatchCharacteristics();
        Set<String> keys = watchFilters.keySet();
        List<Watch> watches = watchService.watches(filters, watchFilters, minValue, maxValue);
        Watch watch = utils.maxWatch(watches);
        int max = 0;
        if(watch != null)
            max = watch.getPrice();

        filterPostModel(model, "Watches", watchFilters, keys, filters, minValue, maxValue, sortType);
        model.addAttribute("products", watches);
        model.addAttribute("max", max);
        return "filters";
    }

    @GetMapping("/discounts")
    public String discounts(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<Product> products = filterProducts.getProductsWithDiscount();
        model.addAttribute("products", products);
        model.addAttribute("seacrhBool", true);
        model.addAttribute("category", "Discounts");
        return "filters";
    }

    @PostMapping("/discounts")
    public String sortedDiscounts(Model model, Authentication authentication, @RequestParam("sort") String sortType) {
        getUserPreferences(model, authentication);
        List<? extends Product> products = filterProducts.getProductsWithDiscount();
        products = filterProducts.sort(products, sortType);
        model.addAttribute("products", products);
        model.addAttribute("seacrhBool", true);
        model.addAttribute("category", "Discounts");
        return "filters";
    }

    @GetMapping("/bestsellers")
    public String bestsellers(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<Product> products = filterProducts.getBestsellers();
        model.addAttribute("products", products);
        model.addAttribute("seacrhBool", true);
        model.addAttribute("sortType", "By popularity");
        model.addAttribute("category", "Bestsellers");
        return "filters";
    }

    @PostMapping("/bestsellers")
    public String sortedBestsellers(Model model, Authentication authentication, @RequestParam("sort") String sortType) {
        getUserPreferences(model, authentication);
        List<? extends Product> products = filterProducts.getBestsellers();
        products = filterProducts.sort(products, sortType);
        model.addAttribute("products", products);
        model.addAttribute("seacrhBool", true);
        model.addAttribute("sortType", sortType);
        model.addAttribute("category", "Bestsellers");
        return "filters";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        return "about";
    }

    @PostMapping("/about")
    public String filledCareer(Model model, Authentication authentication,
                               @RequestParam("fname") String fname,
                               @RequestParam("lname") String lname,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("pos") String pos,
                               @RequestParam("link") String link) {
        getUserPreferences(model, authentication);
        Career career = new Career(fname, lname, email, phone, pos, link);
        String result = careerService.addCareerUser(career);
        model.addAttribute("result", result);
        return "about";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model, Authentication authentication) {
        if (utils.checkAuth(authentication)) {
            model.addAttribute("isAuthenticated", true);
            User user = userDetailsService.getUserByEmail(authentication.getName());
            model.addAttribute("cartProducts", user.getCart().getCartProducts());
            model.addAttribute("total", user.getCart().getTotalPrice());
        }
        return "checkout";
    }

    @GetMapping("/checkout/{id}")
    public String buyProduct(Model model, @PathVariable("id") Long productId){
        Product product = filterProducts.getProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("total", product.getPrice());
        return "checkout";
    }

    @PostMapping(value = {"/checkout", "/checkout/{id}"})
    public String pay(Model model,
                      @RequestParam("name") String name,
                      @RequestParam("phone1") String phone1, @RequestParam("phone2") String phone2, @RequestParam("phone3") String phone3,
                      @RequestParam("email") String email,
                      @RequestParam("city") String city,
                      @RequestParam("part1") String card1, @RequestParam("part2") String card2,
                      @RequestParam("part3") String card3, @RequestParam("part4") String card4,
                      @RequestParam("prefix") String date1, @RequestParam("suffix") String date2,
                      @RequestParam("cvv") String cvv,
                      @RequestParam("total") String total
    ) {
        String phone = phone1 + phone2 + phone3;
        String card = card1 + card2 + card3 + card4;
        String date = date1 + "/" + date2;
        Transaction transaction = transactionService.addNewTransaction(name, phone, email, city, card,
                date, cvv, Integer.valueOf(total.substring(0, total.length()-1)));
        model.addAttribute("transaction", transaction);
        return "success";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        return "contact";
    }

    @PostMapping(value = "/contact", params = "form")
    public String filledContactForm(Model model, Authentication authentication,
                                    @RequestParam("fname") String fname,
                                    @RequestParam("lname") String lname,
                                    @RequestParam("email") String email,
                                    @RequestParam("subject") String subject,
                                    @RequestParam("message") String message
    ) {
        getUserPreferences(model, authentication);
        Contact contact = new Contact(fname, lname, email);
        String result = contactService.addContactMessage(contact, subject, message);
        model.addAttribute("result", result);
        return "contact";
    }

    @PostMapping(value = "/contact", params = "subs")
    public String filledEmail(Model model, Authentication authentication,
                              @RequestParam("email") String email) {
        getUserPreferences(model, authentication);
        String result = contactService.subs(email);
        model.addAttribute("result", result);
        return "contact";
    }

    @GetMapping("/help")
    public String getHelpPage(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<FAQ> faqs = faqService.getFaqs();
        model.addAttribute("faqs", faqs);
        return "help";
    }

    private Model filterPostModel(Model model, String category, Map<String, List<String>> mapFilters,
                                  Set<String> keys, Set<String> filters,
                                  Integer minValue, Integer maxValue, String sortType) {
        model.addAttribute("category", category);
        model.addAttribute("filters", mapFilters);
        model.addAttribute("filtersKeys", keys);
        model.addAttribute("filterName", filters);
        model.addAttribute("minValue", minValue);
        model.addAttribute("maxValue", maxValue);
        model.addAttribute("sortType", sortType);
        return model;
    }
}
