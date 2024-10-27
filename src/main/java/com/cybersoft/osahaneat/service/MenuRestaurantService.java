package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.MenuRestaurant;
import com.cybersoft.osahaneat.entity.keys.KeyMenuRestaurant;
import com.cybersoft.osahaneat.repository.MenuRestaurantRepository;
import com.cybersoft.osahaneat.service.imp.MenuRestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuRestaurantService implements MenuRestaurantServiceImp {
    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;


    @Override
    public boolean createMenuRestaurant(int categoryId, int restaurantId) {
        boolean isInserted = false;
        try {
            MenuRestaurant menuRestaurant = new MenuRestaurant();

            // set create date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = dateFormat.parse(new Date().toString());
            menuRestaurant.setCreateDate(date);

            // set key
            KeyMenuRestaurant keyMenuRestaurant = new KeyMenuRestaurant();
            keyMenuRestaurant.setCateId(categoryId);
            keyMenuRestaurant.setResId(restaurantId);
            menuRestaurant.setKeys(keyMenuRestaurant);

            // insert into database
            menuRestaurantRepository.save(menuRestaurant);

            isInserted = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isInserted;
    }
}