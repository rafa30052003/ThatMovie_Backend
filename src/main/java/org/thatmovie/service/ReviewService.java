package org.thatmovie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.exception.RecordNotFoundException;
import org.thatmovie.model.Movie;
import org.thatmovie.model.Review;
import org.thatmovie.repository.MovieRepository;
import org.thatmovie.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepo;
    @Autowired
    MovieRepository movieRepository;

    /**
     * Obtiene todas las reseñas
     *
     * @return Lista de todas las reseñas
     */
    public List<Review> getAllReviews() {
        List<Review> reviews = reviewRepo.findAll();
        return reviews;
    }

    /**
     * Obtiene una reseña por su ID
     *
     * @param id ID de la reseña a buscar
     * @return La reseña encontrada
     * @throws RecordNotFoundException si no se encuentra la reseña con el ID especificado
     */
    public Review getReviewById(int id) {
        Optional<Review> review = reviewRepo.findById(id);
        if(review.isPresent()){
            return review.get();
        }else{
            throw new RecordNotFoundException("No review found with ID: " + id);
        }
    }

    /**
     * Crea o actualiza una reseña
     *
     * @param review La reseña a crear o actualizar
     * @return La reseña creada o actualizada
     * @throws RecordNotFoundException si no se encuentra la reseña con el ID especificado al actualizar
     */
    public Review createOrUpdateReview(Review review) {
        Review end;
        if (review.getId() > 0) {
            Optional<Review> result = reviewRepo.findById(review.getId());
            if (result.isPresent()) {
                Review existing = result.get();
                existing.setContent(review.getContent());
                existing.setRating(review.getRating());
                end = reviewRepo.save(existing);
            } else {
                throw new RecordNotFoundException("No review found with ID: " + review.getId());
            }
        } else {
            Movie movie = review.getMovie();
            Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
            if (existingMovie.isPresent()) {
                review.setMovie(existingMovie.get());
            } else {
                Movie savedMovie = movieRepository.save(movie);
                review.setMovie(savedMovie);
            }
            end = reviewRepo.save(review);
        }
        return end;
    }

    /**
     * Elimina una reseña por su ID
     *
     * @param id ID de la reseña a eliminar
     * @throws RecordNotFoundException si no se encuentra la reseña con el ID especificado
     */
    public void deleteReview(int id) {
        Optional<Review> result = reviewRepo.findById(id);
        if(result.isPresent()){
            reviewRepo.deleteById(id);
        }else{
            throw new RecordNotFoundException("No review found with ID: " + id);
        }
    }
}