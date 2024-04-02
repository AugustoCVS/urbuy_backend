package api.urbuy.controller;

import java.util.List;

import api.urbuy.domain.product.Product;
import api.urbuy.domain.product.ProductRepository;
import api.urbuy.domain.purchase.Purchase;
import api.urbuy.domain.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("")
    public ModelAndView home() {

        return new ModelAndView();
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

}
