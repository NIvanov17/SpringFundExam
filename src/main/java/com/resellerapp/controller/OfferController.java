package com.resellerapp.controller;

import com.resellerapp.model.DTOs.AddOfferDTO;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute
    public AddOfferDTO addOfferDTOinit() {
        return new AddOfferDTO();
    }

    @GetMapping("/offers/add")
    public String addOffer() {
        if (loggedUser.getId() < 0) {
            return "redirect:/";
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.offerService.addOffer(addOfferDTO)) {
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);

            return "redirect:/offers/add";
        }
        return "redirect:/home";
    }

    @GetMapping("/offers/remove/{id}")
    public String removeOffer(@PathVariable long id) {
        if (loggedUser.getId() < 0) {
            return "redirect:/";
        }
        this.offerService.removeOffer(id);
        return "redirect:/home";
    }

    @GetMapping("/offers/buy/{id}")
    public String buyOffer(@PathVariable long id) {
        if (loggedUser.getId() < 0) {
            return "redirect:/";
        }
        this.offerService.buyOffer(id);
        return "redirect:/home";
    }
}
