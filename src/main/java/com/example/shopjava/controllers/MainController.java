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

    public static final String PRODUCTS = "products";
    public static final String SEARCH_KEY = "searchKey";
    public static final String HAS_ASIDE_MENU = "hasAsideMenu";
    public static final String IS_AUTHENTICATED = "isAuthenticated";
    public static final String MAX = "max";
    public static final String PHONES = "Phones";
    public static final String LAPTOPS = "Laptops";
    public static final String FILTERS_PAGE = "filters";
    public static final String PRODUCT = "product";
    public static final String CATEGORY = "category";
    public static final String RESULT = "result";
    public static final String SORT_TYPE = "sortType";
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
    private CartService cartService;

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
                               Model model) {

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
                        Model model) {

        String res = userDetailsService.signIn(email, psw, request);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (!res.isEmpty()) {
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
        if (utils.checkAuth(authentication)) {
            model.addAttribute(IS_AUTHENTICATED, true);
        }

        getUserPreferences(model, authentication);

        model.addAttribute(PRODUCTS, filterProducts.searchProducts(searchKey));
        model.addAttribute(SEARCH_KEY, searchKey);
        model.addAttribute(HAS_ASIDE_MENU, true);

        return FILTERS_PAGE;
    }

    @GetMapping("/phones")
    public String phoneFilters(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);

        List<Phone> phones = phoneService.getAllPhones();
        Map<String, List<String>> phoneFilters = phoneService.getPhoneCharacteristics();

        int max = 0;
        Phone phoneWithMaxPrice = utils.max(phoneService.getAllPhones());
        if (phoneWithMaxPrice != null) {
            max = utils.max(phones).getPrice();
        }

        filterPostModel(model, PHONES, phoneFilters, phoneFilters.keySet(), new LinkedHashSet<>(), 0,
                max, "From expensive to cheap");
        model.addAttribute(PRODUCTS, phones);
        model.addAttribute(MAX, max);

        return FILTERS_PAGE;
    }

    @PostMapping("/phones")
    public String phoneFiltersPost(Model model, Authentication authentication,
                                   @RequestParam(value = "filter-name", required = false) LinkedHashSet<String> filters,
                                   @RequestParam("input-min") Integer minValue, @RequestParam("input-max") Integer maxValue,
                                   @RequestParam("sort") String sortType) {

        getUserPreferences(model, authentication);
        Map<String, List<String>> phoneFilters = phoneService.getPhoneCharacteristics();
        List<? extends Product> phones = phoneService.phones(filters, phoneFilters, minValue, maxValue);
        phones = filterProducts.sort(phones, sortType);

        Phone phoneWithMaxPrice = utils.max(phoneService.getAllPhones());
        if (phoneWithMaxPrice != null) {
            model.addAttribute(MAX, phoneWithMaxPrice.getPrice());
        }

        filterPostModel(model, PHONES, phoneFilters, phoneFilters.keySet(), filters, minValue, maxValue, sortType);
        model.addAttribute(PRODUCTS, phones);
        model.addAttribute("maxValue", maxValue);

        return FILTERS_PAGE;
    }

    @GetMapping("/laptops")
    public String laptopFilters(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<Laptop> laptops = laptopService.getAllLaptops();
        Map<String, List<String>> laptopFilters = laptopService.getLaptopCharacteristics();

        Laptop laptopWithMaxPrice = utils.maxLaptop(laptops);
        int max = 0;
        if (laptopWithMaxPrice != null) {
            max = utils.maxLaptop(laptops).getPrice();
        }

        filterPostModel(model, LAPTOPS, laptopFilters, laptopFilters.keySet(),
                new LinkedHashSet<>(), 0, max, "From expensive to cheap");
        model.addAttribute(PRODUCTS, laptops);

        return FILTERS_PAGE;
    }

    @PostMapping("/laptops")
    public String laptopFiltersPost(Model model, Authentication authentication,
                                    @RequestParam(value = "filter-name", required = false) LinkedHashSet<String> filters,
                                    @RequestParam("input-min") Integer minValue, @RequestParam("input-max") Integer maxValue,
                                    @RequestParam("sort") String sortType) {

        getUserPreferences(model, authentication);
        Map<String, List<String>> laptopFilters = laptopService.getLaptopCharacteristics();
        List<Laptop> laptops = laptopService.laptops(filters, laptopFilters, minValue, maxValue);

        Laptop laptopWithMaxPrice = utils.maxLaptop(laptops);
        int max = 0;
        if (laptopWithMaxPrice != null) {
            max = laptopWithMaxPrice.getPrice();
        }

        filterPostModel(model, LAPTOPS, laptopFilters, laptopFilters.keySet(), filters, minValue, maxValue, sortType);
        model.addAttribute(PRODUCTS, laptops);
        model.addAttribute(MAX, max);

        return FILTERS_PAGE;
    }

    @GetMapping("/product/{id}")
    public String getDescription(Model model, Authentication authentication,
                                 @PathVariable("id") Long id) {

        getUserPreferences(model, authentication);
        List<Review> reviews = reviewService.findReviewsByProduct(id);
        Product product = filterProducts.getProductById(id);
        Map<String, List<String>> descTable = filterProducts.getDescTable(product);

        if (authentication != null) {
            model.addAttribute("user", userDetailsService.getUserByEmail(authentication.getName()));
        }

        model.addAttribute(PRODUCT, product);

        return getProductDetails(model, product, reviews, descTable);
    }

    private void getUserPreferences(Model model, Authentication authentication) {
        if (utils.checkAuth(authentication)) {
            model.addAttribute(IS_AUTHENTICATED, true);
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

        Product product = filterProducts.getProductById(id);

        model.addAttribute(PRODUCT, product);
        model.addAttribute("reviewExists", reviewService.addReview(rate, review, recommend, authentication, id));

        return getProductDetails(model, product,
                reviewService.findReviewsByProduct(id), filterProducts.getDescTable(product));
    }

    private String getProductDetails(Model model, Product product, List<Review> reviews, Map<String, List<String>> descTable) {
        model.addAttribute("reviews", reviews);
        model.addAttribute("recommended", reviewService.calculateRecommended(reviews));
        model.addAttribute("descTable", descTable);
        model.addAttribute("descTableKeys", descTable.keySet());
        model.addAttribute("descData", filterProducts.descData(product));

        return "description";
    }

    @GetMapping("/watches")
    public String watchesFilters(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        List<Watch> watches = watchService.getAllWatches();
        Map<String, List<String>> watchFilters = watchService.getWatchCharacteristics();

        Watch watchWithMaxPrice = utils.maxWatch(watches);
        int max = 0;
        if (watchWithMaxPrice != null) {
            max = watchWithMaxPrice.getPrice();
        }

        filterPostModel(model, "Watches", watchFilters, watchFilters.keySet(),
                new LinkedHashSet<>(), 0, max, "From expensive to cheap");
        model.addAttribute(PRODUCTS, watches);
        model.addAttribute(MAX, max);

        return FILTERS_PAGE;
    }

    @PostMapping("/watches")
    public String watchesFiltersPost(Model model, Authentication authentication,
                                     @RequestParam(value = "filter-name", required = false) LinkedHashSet<String> filters,
                                     @RequestParam("input-min") Integer minValue, @RequestParam("input-max") Integer maxValue,
                                     @RequestParam("sort") String sortType) {

        getUserPreferences(model, authentication);
        Map<String, List<String>> watchFilters = watchService.getWatchCharacteristics();
        List<Watch> watches = watchService.watches(filters, watchFilters, minValue, maxValue);

        Watch watchWithMaxPrice = utils.maxWatch(watches);
        int max = 0;
        if (watchWithMaxPrice != null) {
            max = watchWithMaxPrice.getPrice();
        }

        filterPostModel(model, "Watches", watchFilters, watchFilters.keySet(),
                filters, minValue, maxValue, sortType);
        model.addAttribute(PRODUCTS, watches);
        model.addAttribute(MAX, max);

        return FILTERS_PAGE;
    }

    @GetMapping("/discounts")
    public String discounts(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        model.addAttribute(PRODUCTS, filterProducts.getProductsWithDiscount());
        model.addAttribute(HAS_ASIDE_MENU, true);
        model.addAttribute(CATEGORY, "Discounts");

        return FILTERS_PAGE;
    }

    @PostMapping("/discounts")
    public String sortedDiscounts(Model model, Authentication authentication, @RequestParam("sort") String sortType) {
        getUserPreferences(model, authentication);
        model.addAttribute(PRODUCTS, filterProducts.sort(filterProducts.getProductsWithDiscount(), sortType));
        model.addAttribute(HAS_ASIDE_MENU, true);
        model.addAttribute(CATEGORY, "Discounts");

        return FILTERS_PAGE;
    }

    @GetMapping("/bestsellers")
    public String bestsellers(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        model.addAttribute(PRODUCTS, filterProducts.getBestsellers());
        model.addAttribute(HAS_ASIDE_MENU, true);
        model.addAttribute(SORT_TYPE, "By popularity");
        model.addAttribute(CATEGORY, "Bestsellers");

        return FILTERS_PAGE;
    }

    @PostMapping("/bestsellers")
    public String sortedBestsellers(Model model, Authentication authentication, @RequestParam("sort") String sortType) {
        getUserPreferences(model, authentication);
        model.addAttribute(PRODUCTS, filterProducts.sort(filterProducts.getBestsellers(), sortType));
        model.addAttribute(HAS_ASIDE_MENU, true);
        model.addAttribute(SORT_TYPE, sortType);
        model.addAttribute(CATEGORY, "Bestsellers");

        return FILTERS_PAGE;
    }

    @GetMapping("/about")
    public String getAboutPage(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        model.addAttribute("careerForm", new Career());
        return "about";
    }

    @PostMapping("/about")
    public String filledCareer(Model model, Authentication authentication, @ModelAttribute("careerForm") Career career) {
        getUserPreferences(model, authentication);
        model.addAttribute(RESULT, careerService.addCareerUser(career));

        return "about";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model, Authentication authentication) {
        if (utils.checkAuth(authentication)) {
            model.addAttribute(IS_AUTHENTICATED, true);
            User user = userDetailsService.getUserByEmail(authentication.getName());
            model.addAttribute("cartProducts", user.getCart().getCartProducts());
            model.addAttribute("total", user.getCart().getTotalPrice());
        }

        return "checkout";
    }

    @GetMapping("/checkout/{id}")
    public String buyProduct(Model model, @PathVariable("id") Long productId) {
        Product product = filterProducts.getProductById(productId);
        model.addAttribute(PRODUCT, product);
        model.addAttribute("total", product.getPrice());

        return "checkout";
    }

    @PostMapping(value = {"/checkout", "/checkout/{id}"})
    public String pay(Model model, Authentication authentication,
                      @RequestParam("name") String name,
                      @RequestParam("phone1") String phone1,
                      @RequestParam("phone2") String phone2,
                      @RequestParam("phone3") String phone3,
                      @RequestParam("email") String email,
                      @RequestParam("city") String city,
                      @RequestParam("part1") String card1,
                      @RequestParam("part2") String card2,
                      @RequestParam("part3") String card3,
                      @RequestParam("part4") String card4,
                      @RequestParam("prefix") String date1,
                      @RequestParam("suffix") String date2,
                      @RequestParam("cvv") String cvv,
                      @RequestParam("total") String total) {

        String phone = phone1 + phone2 + phone3;
        String card = card1 + card2 + card3 + card4;
        String date = date1 + "/" + date2;
        Transaction transaction = transactionService.addNewTransaction(name, phone, email, city, card,
                date, cvv, Integer.valueOf(total.substring(0, total.length() - 1)));

        cartService.cleanCart(authentication);

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
                                    @RequestParam("message") String message) {

        getUserPreferences(model, authentication);
        Contact contact = new Contact(fname, lname, email);
        String result = contactService.addContactMessage(contact, subject, message);
        model.addAttribute(RESULT, result);

        return "contact";
    }

    @PostMapping(value = "/contact", params = "subs")
    public String filledEmail(Model model, Authentication authentication,
                              @RequestParam("email") String email) {
        getUserPreferences(model, authentication);
        model.addAttribute(RESULT, contactService.subs(email));

        return "contact";
    }

    @GetMapping("/help")
    public String getHelpPage(Model model, Authentication authentication) {
        getUserPreferences(model, authentication);
        model.addAttribute("faqs", faqService.getFaqs());

        return "help";
    }

    private Model filterPostModel(Model model, String category, Map<String, List<String>> mapFilters,
                                  Set<String> keys, Set<String> filters,
                                  Integer minValue, Integer maxValue, String sortType) {

        model.addAttribute(CATEGORY, category);
        model.addAttribute(FILTERS_PAGE, mapFilters);
        model.addAttribute("filtersKeys", keys);
        model.addAttribute("filterName", filters);
        model.addAttribute("minValue", minValue);
        model.addAttribute("maxValue", maxValue);
        model.addAttribute(SORT_TYPE, sortType);

        return model;
    }
}
