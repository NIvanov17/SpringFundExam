package com.resellerapp.controller;

import com.resellerapp.model.DTOs.ViewDTO;
import com.resellerapp.service.AuthService;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final OfferService offerService;
    private final AuthService authService;

    @Autowired
    public HomeController(LoggedUser loggedUser, OfferService offerService, AuthService authService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
        this.authService = authService;
    }

    @GetMapping("/home")
    public String initHome(Model model) {
        if (loggedUser.getId() < 0) {
            return "redirect:/";
        }

        model.addAttribute("boughtOffers", this.offerService.getBoughtOffers(this.loggedUser.getId()));
        model.addAttribute("ownerOffers", this.offerService.getOffersOwnedBy(this.loggedUser.getId()));
        List<ViewDTO> availableOffers = this.offerService.getAvailableOffers(this.loggedUser.getId());

        model.addAttribute("availableOffers", availableOffers);
        model.addAttribute("totalOffers", availableOffers.size());
        return "home";
    }

    @GetMapping("/")
    public String index() {
        if (loggedUser.getId() > 0) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        if (loggedUser.getId() < 0) {
            return "redirect:/";
        }
        this.authService.logout();
        return "redirect:/";
    }
}
