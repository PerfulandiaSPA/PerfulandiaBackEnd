package com.perfuland.perfulandia.service.impl;

import com.perfuland.perfulandia.model.Review;
import com.perfuland.perfulandia.repository.ReviewRepository;
import com.perfuland.perfulandia.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewsServiceImpl implements ReviewService {

    private final ReviewRepository reviewsRepository;

    public ReviewsServiceImpl(ReviewRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewsRepository.findAll();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewsRepository.findById(id).orElse(null);
    }

    @Override
    public Review createReview(Review review) {
        return reviewsRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review reviewDetails) {
        Optional<Review> existingReviewOptional = reviewsRepository.findById(id);
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            
            // TODO: Actualiza los campos de tu modelo Review aquí
            // Ejemplo:
            // existingReview.setRating(reviewDetails.getRating());
            // existingReview.setComment(reviewDetails.getComment());
            // existingReview.setUser(reviewDetails.getUser());
            // existingReview.setPerfume(reviewDetails.getPerfume());

            return reviewsRepository.save(existingReview);
        }
        return null;
    }

    @Override
    public void deleteReview(Long id) {
        reviewsRepository.deleteById(id);
    }
}