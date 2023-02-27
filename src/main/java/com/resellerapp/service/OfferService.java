package com.resellerapp.service;


import com.resellerapp.model.DTOs.ViewDTO;
import com.resellerapp.model.User;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.model.DTOs.AddOfferDTO;
import com.resellerapp.model.Offer;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final ModelMapper modelMapper;
    private final ConditionService conditionService;
    private final UserService userService;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;


    public OfferService(ModelMapper modelMapper, ConditionService conditionService, UserService userService, OfferRepository offerRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.conditionService = conditionService;
        this.userService = userService;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }

    public boolean addOffer(AddOfferDTO addOfferDTO) {
        Offer mapped = this.modelMapper.map(addOfferDTO, Offer.class);
        ConditionName conditionName = ConditionName.valueOf(addOfferDTO.getCondition());
        mapped.setCondition(this.conditionService.getConditionName(conditionName));

        mapped.setSeller(this.userService.getLoggedUser());

        this.offerRepository.save(mapped);
        return true;
    }

    public Offer getOfferById(long id) {
        return this.offerRepository.findById(id).get();
    }

    public List<ViewDTO> getBoughtOffers(long id) {
        User user = this.userService.getById(id).get();
        return user.getBoughtOffers()
                .stream()
                .map(ViewDTO::new)
                .collect(Collectors.toList());
    }

    public List<ViewDTO> getOffersOwnedBy(long id) {
        User user = this.userService.getById(id).get();
        return user.getOffers()
                .stream()
                .map(ViewDTO::new)
                .collect(Collectors.toList());
    }

    public List<ViewDTO> getAvailableOffers(long id) {
        List<Offer> offers = new ArrayList<>();

        List<User> allOtherUsers = this.userService.getAllOtherUsers(id);
       allOtherUsers.forEach(user -> offers.addAll(user.getOffers()) );

        List<ViewDTO> collect = offers
                .stream()
                .map(ViewDTO::new)
                .collect(Collectors.toList());

        return collect;
    }

    public void buyOffer(long id){
        Offer offer = this.getOfferById(id);
        User seller = offer.getSeller();

        User user = this.userService.getById(this.userService.getLoggedUser().getId()).get();
        List<Offer> boughtOffers = user.getBoughtOffers();
        boughtOffers.add(offer);
        user.setBoughtOffers(boughtOffers);

        List<Offer> sellerOffers = seller.getOffers();
        sellerOffers.remove(offer);
        seller.setOffers(sellerOffers);

        this.userRepository.save(user);
        this.userRepository.save(seller);
        offer.setSeller(null);
        this.offerRepository.save(offer);
    }

    public void removeOffer(long id) {
        Offer offer = this.offerRepository.findById(id).get();
        this.offerRepository.delete(offer);
    }
}
