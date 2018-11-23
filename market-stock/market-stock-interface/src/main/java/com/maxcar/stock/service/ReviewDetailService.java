package com.maxcar.stock.service;

import com.maxcar.stock.pojo.ReviewDetail;

import java.util.List;

public interface ReviewDetailService {
    List<ReviewDetail> getReviewDetail(ReviewDetail reviewDetail);

    boolean saveReviewDetail(ReviewDetail reviewDetail);


}
