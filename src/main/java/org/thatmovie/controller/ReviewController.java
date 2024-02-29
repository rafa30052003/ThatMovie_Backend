package org.thatmovie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thatmovie.model.Review;
import org.thatmovie.service.ReviewService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    /**
     * Obtiene todas las reseñas
     *
     * @return ResponseEntity con la lista de reseñas y el código de estado HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    /**
     * Obtiene una reseña por su ID
     *
     * @param id ID de la reseña a buscar
     * @return ResponseEntity con la reseña encontrada y el código de estado HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") int id){
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    /**
     * Crea o actualiza una reseña
     *
     * @param review Objeto Review que contiene la información de la reseña a crear o actualizar
     * @return ResponseEntity con la reseña creada o actualizada y el código de estado HTTP 200 (OK)
     */
    @PostMapping
    public ResponseEntity<Review> createOrUpdateReview(@RequestBody Review review){
        Review end = reviewService.createOrUpdateReview(review);
        return ResponseEntity.ok(end);
    }

    /**
     * Elimina una reseña por su ID
     *
     * @param id ID de la reseña a eliminar
     */
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable("id") int id){
        reviewService.deleteReview(id);
    }
}