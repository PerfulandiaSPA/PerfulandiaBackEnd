package com.perfuland.perfulandia.service.impl;

import com.perfuland.perfulandia.model.Review;
import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.model.Perfume;
import com.perfuland.perfulandia.repository.ReviewRepository;
import com.perfuland.perfulandia.repository.UserRepository; // <--- IMPORTANTE
import com.perfuland.perfulandia.repository.PerfumeRepository; // <--- IMPORTANTE
import com.perfuland.perfulandia.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewsServiceImpl implements ReviewService {

    private final ReviewRepository reviewsRepository;
    private final UserRepository userRepository; // Necesario para buscar al usuario real
    private final PerfumeRepository perfumeRepository; // Necesario para buscar el perfume real

    // Constructor con Inyección de Dependencias
    public ReviewsServiceImpl(ReviewRepository reviewsRepository,
            UserRepository userRepository,
            PerfumeRepository perfumeRepository) {
        this.reviewsRepository = reviewsRepository;
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
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
        // 1. VINCULAR USUARIO REAL
        // El frontend nos manda un objeto User solo con el userName.
        // Debemos buscar el usuario completo en la BD.
        if (review.getUser() != null && review.getUser().getUserName() != null) {
            User dbUser = userRepository.findByUserName(review.getUser().getUserName())
                    .orElseThrow(() -> new RuntimeException(
                            "Error: Usuario no encontrado con nombre " + review.getUser().getUserName()));
            review.setUser(dbUser); // Asignamos el usuario real de la BD
        } else {
            // Si quieres permitir anónimos, borra este else. Si no, lanza error.
            throw new RuntimeException("Error: La reseña debe tener un usuario.");
        }

        // 2. VINCULAR PERFUME REAL
        // El frontend manda un objeto Perfume solo con idPerfume.
        if (review.getPerfume() != null && review.getPerfume().getIdPerfume() != null) {
            Perfume dbPerfume = perfumeRepository.findById(review.getPerfume().getIdPerfume())
                    .orElseThrow(() -> new RuntimeException("Error: Perfume no encontrado"));
            review.setPerfume(dbPerfume); // Asignamos el perfume real
        }

        // 3. GUARDAR
        return reviewsRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review reviewDetails) {
        Optional<Review> existingReviewOptional = reviewsRepository.findById(id);

        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();

            // ACTUALIZAMOS LOS DATOS
            existingReview.setTitle(reviewDetails.getTitle());
            existingReview.setContent(reviewDetails.getContent());
            existingReview.setRating(reviewDetails.getRating());

            // Nota: Usualmente no permitimos cambiar el autor ni el perfume de una reseña
            // ya creada.

            return reviewsRepository.save(existingReview);
        }
        return null; // O lanzar una excepción
    }

    @Override
    public void deleteReview(Long id) {
        if (reviewsRepository.existsById(id)) {
            reviewsRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar: Reseña no encontrada");
        }
    }
}