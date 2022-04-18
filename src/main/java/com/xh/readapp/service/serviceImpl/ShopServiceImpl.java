package com.xh.readapp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xh.readapp.dao.ShopDao;
import com.xh.readapp.domain.Shop;
import com.xh.readapp.service.ShopService;
import com.xh.readapp.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public ResultJson getLabel(String userId) {
        return ResultJson.success(getShop(userId).getLabel());
    }

    @Override
    public String getUserLabelByUserId(String userId) {
        return getShop(userId).getLabel();
    }

    private Shop getShop(String userId){
        LambdaQueryWrapper<Shop> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Shop::getUserShopId,userId);
        return shopDao.selectOne(lambdaQueryWrapper);
    }
}
