package org.thatmovie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.exception.RecordNotFoundException;
import org.thatmovie.model.Review;
import org.thatmovie.model.User;
import org.thatmovie.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepo;

    public List<Review> getAllReviews() {
        List<Review> reviews = reviewRepo.findAll();
        return reviews;
    }

    public Review getReviewById(int id) {
        Optional<Review> review = reviewRepo.findById(id);
        if(review.isPresent()){
            return review.get();
        }else{
            throw new RecordNotFoundException("No review found with id : " + id);
        }
    }

    public Review createOrUpdateReview(Review review) {
        Review end;
        if(review.getId() != -1){
            Optional<Review> result = reviewRepo.findById(review.getId());
            if(result.isPresent()){
                Review existing = result.get();
                existing.setContent(review.getContent());
                existing.setRating(review.getRating());
                end = reviewRepo.save(existing);
            }else{
                throw new RecordNotFoundException("No review found with id : " + review.getId());
            }
        }else{
            end=reviewRepo.save(review);
        }
        return end;
    }

    public void deleteReview(int id) {
        Optional<Review> result = reviewRepo.findById(id);
        if(result.isPresent()){
            reviewRepo.deleteById(id);
        }else{
            throw new RecordNotFoundException("No review found with id : " + id);
        }
    }
}
