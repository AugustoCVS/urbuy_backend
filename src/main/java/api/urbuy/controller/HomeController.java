package api.urbuy.controller;

import java.util.List;

import api.urbuy.domain.product.Product;
import api.urbuy.domain.product.ProductRepository;
import api.urbuy.domain.product.registerProductsData;
import api.urbuy.domain.purchase.Purchase;
import api.urbuy.domain.purchase.PurchaseRepository;
import api.urbuy.domain.user.User;
import api.urbuy.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ModelAndView home() {

        return new ModelAndView();
    }

    @GetMapping("/users")
    public ModelAndView userList() {
        List<User> users = userRepository.findAll();
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", users);
        return mv;
    }

    @GetMapping("/purchaseList")
    public ModelAndView purchaseList() {

        List<Purchase> purchases = purchaseRepository.findAll();
        ModelAndView mv = new ModelAndView("purchaseList");
        mv.addObject("purchases", purchases);
        return mv;
    }

    @GetMapping("/products")
    public ModelAndView products() {

        List<Product> products = productRepository.findAll();
        ModelAndView mv = new ModelAndView("products");
        mv.addObject("products", products);
        return mv;
    }

    @GetMapping("/newProduct/form")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("newProduct/form");
        mv.addObject("registerProductsData", new registerProductsData(
                "",
                "",
                "",
                0,
                "",
                "",
                ""
        ));
        return mv;
    }

    @PostMapping("/newProduct")
    public ModelAndView novo(registerProductsData req, BindingResult result) {
        ModelAndView mv;
        if (result.hasErrors()) {
            mv = new ModelAndView("/newProduct/form");
            return mv;
        }
        Product product = new Product(req);
        productRepository.save(product);
        mv = new ModelAndView("redirect:/home");
        return mv;
    }

}
