package com.maxcar.stock.service.impl;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.dao.CarReviewMapper;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.service.SubmitApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("submitApplicationsService")
public class SubmitApplicationsServiceImpl implements SubmitApplicationsService {
    @Autowired
    private CarReviewMapper carReviewMapper;


    @Override
    public InterfaceResult insertRecord(CarReview record) {

        InterfaceResult interfaceResult = new InterfaceResult();
		 int count = 0;
	     count = carReviewMapper.insertSelective(record);
	     if(count!=0){
	    	 interfaceResult.InterfaceResult200("新增成功");

	     }else{
	    	 interfaceResult.InterfaceResult600("新增失败");
	     }

	        return interfaceResult;
	}

}
