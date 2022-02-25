package com.ucll.smarthome.controller;

import com.ucll.smarthome.persistence.entities.House;
import com.ucll.smarthome.persistence.entities.House_User;
import com.ucll.smarthome.persistence.entities.User;
import com.ucll.smarthome.persistence.repository.HouseDAO;
import com.ucll.smarthome.persistence.repository.House_UserDAO;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@Transactional
public class House_UserController {

    private final House_UserDAO dao;
    private final HouseDAO houseDAO;



    public House_UserController(House_UserDAO dao, HouseDAO houseDAO) {
        this.dao = dao;
        this.houseDAO = houseDAO;
    }

    /**
     * @param h house that needs to get registered to a user
     * @param user that needs to get registered to a house
     * @throws IllegalArgumentException if something goes wrong with the info what went wrong
     */
    public void createRegistratieHouseUser(House h , User user) throws IllegalArgumentException{

        if (h == null ) throw new IllegalArgumentException("House is needed");
        if (h.getHouseId()<= 0L || h.getHouseId() == null) throw new IllegalArgumentException("House id is missing");



        House_User hs = new House_User.Builder().house(h).user(user).isAdmin(true).build();
        dao.save(hs);
    }
    /**
     * @param user to search house_user objects
     * @return a list of house_user(registrations) by user
     */
    public List<House_User> getByUser(User user) throws IllegalArgumentException{
        if (user == null) throw new IllegalArgumentException("Need to give the user to get list");
        return dao.findAllByUser(user);

    }

    /**
     * @param house to search house_user objects
     * @return a list of house_user(registrations) by house
     */
    public List<House_User> getByHouse(House house){
        if (house == null) throw new IllegalArgumentException("Need to give the house to get list");
        return dao.findAllByHouse(house);
    }


    /**
     * @param lst list of registrations house_user that needs to be deleted
     */
    public void deleteRegistratieHouseUser(List<House_User> lst) {
        dao.deleteAll(lst);

    }

}