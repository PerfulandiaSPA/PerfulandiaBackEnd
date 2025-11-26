package com.perfuland.perfulandia.service;

import java.util.List;
import com.perfuland.perfulandia.model.Review;

public interface ReviewService {
    List<Review> getAllReviews();

    Review getReviewById(Long idReview);

    Review createReview(Review review);

    Review updateReview(Long idReview, Review review);

    void deleteReview(Long idReview);

}
