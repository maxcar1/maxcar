package com.maxcar.stock.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.stock.dao.WishListMapper;
import com.maxcar.stock.pojo.WishList;
import com.maxcar.stock.pojo.WxWishListCar;
import com.maxcar.stock.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wishListService")
public class WishListServiceImpl extends BaseServiceImpl<WishList,String> implements WishListService {
    @Autowired
    WishListMapper wishListMapper;
    @Override
    public BaseDao<WishList, String> getBaseMapper() {
        return wishListMapper;
    }

    @Override
    public void insertWishList(WishList wishList) {
        wishListMapper.insertWishList(wishList);
    }

    @Override
    public List<WxWishListCar> getWishListCar(WishList wishList) {
        return wishListMapper.getWishListCar(wishList);
    }
}
