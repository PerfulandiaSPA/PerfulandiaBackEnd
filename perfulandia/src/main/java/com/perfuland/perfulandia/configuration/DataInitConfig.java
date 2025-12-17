package com.perfuland.perfulandia.configuration;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.CategoryRepository;
import com.perfuland.perfulandia.repository.PerfumeRepository;
import com.perfuland.perfulandia.repository.UserRepository;
import com.perfuland.perfulandia.model.Category;
import com.perfuland.perfulandia.model.Perfume;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitConfig {

    private final CategoryRepository categoryRepository;

    DataInitConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CategoryRepository categoryRepository,
            PerfumeRepository perfumeRepository) {
        return args -> {

            // --- USUARIOS ---
            if (userRepository.count() == 0) {
                // ADMIN
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@perfulandia.com");
                admin.setAddress("Central");
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println(">>> Usuario ADMIN creado: admin / admin123");

                // USER
                User user = new User();
                user.setUserName("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setEmail("user@perfulandia.com");
                user.setAddress("Domicilio");
                user.setRole("ROLE_USER");
                userRepository.save(user);
                System.out.println(">>> Usuario USER creado: user / user123");
            }

            // --- CATEGORÍAS ---
            if (categoryRepository.count() == 0) {

                // HOMBRE
                Category catHombre = new Category();
                catHombre.setGender("Hombre");
                // Guardamos y recuperamos la instancia guardada (que ya tiene ID)
                catHombre = categoryRepository.save(catHombre);

                // MUJER
                Category catMujer = new Category();
                catMujer.setGender("Mujer");
                catMujer = categoryRepository.save(catMujer);

                System.out.println(">>> Categorias iniciales creadas");

                // --- PERFUMES ---

                Perfume p1 = new Perfume();
                p1.setProductName("N°.5");
                p1.setBrand("CHANEL");
                p1.setPrice(120000L);
                p1.setStock(20);
                p1.setDescPerfume("Un bouquet floral aldehído en un frasco icónico y atemporal.");
                p1.setImage("https://fimgs.net/mdimg/perfume/o.40069.jpg");
                p1.setSize("100ml");
                p1.setIsActive(false);
                p1.setCategoryGender(catMujer);

                Perfume p2 = new Perfume();
                p2.setProductName("BLACK OPIUM");
                p2.setBrand("YVES SAINT LAURENT");
                p2.setPrice(95000L);
                p2.setStock(40);
                p2.setDescPerfume("Adictiva y moderna: café negro con vainilla y flores blancas.");
                p2.setImage("https://fimgs.net/mdimg/perfume-thumbs/375x500.25324.jpg");
                p2.setSize("90ml");
                p2.setIsActive(false);
                p2.setCategoryGender(catMujer);

                Perfume p3 = new Perfume();
                p3.setProductName("LA VIE EST BELLE");
                p3.setBrand("LANCÔME");
                p3.setPrice(105000L);
                p3.setStock(30);
                p3.setDescPerfume("La felicidad hecha fragancia: iris gourmand con vainilla y praliné.");
                p3.setImage("https://fimgs.net/mdimg/perfume/o.14982.jpg");
                p3.setSize("100ml");
                p3.setIsActive(false);
                p3.setCategoryGender(catMujer);

                Perfume p4 = new Perfume();
                p4.setProductName("BLOOM");
                p4.setBrand("GUCCI");
                p4.setPrice(105000L);
                p4.setStock(20);
                p4.setDescPerfume("Ramo blanco exuberante con jazmín y nardos; floral auténtico y luminoso.");
                p4.setImage("https://fimgs.net/mdimg/perfume-thumbs/375x500.44894.jpg");
                p4.setSize("100ml");
                p4.setIsActive(false);
                p4.setCategoryGender(catMujer);

                Perfume p5 = new Perfume();
                p5.setProductName("ACQUA DI GIOIA");
                p5.setBrand("GIORGIO ARMANI");
                p5.setPrice(54990L);
                p5.setStock(10);
                p5.setDescPerfume("Acuático-cítrico fresco con menta y limón; limpio y revitalizante.");
                p5.setImage("https://fimgs.net/mdimg/perfume-thumbs/375x500.8442.avif");
                p5.setSize("100ml");
                p5.setIsActive(true);
                p5.setCategoryGender(catMujer);

                Perfume p6 = new Perfume();
                p6.setProductName("BRIGHT CRYSTAL");
                p6.setBrand("VERSACE");
                p6.setPrice(49990L);
                p6.setStock(40);
                p6.setDescPerfume("Floral-frutal cristalino con granada y peonía; femenino y brillante.");
                p6.setImage("https://fimgs.net/mdimg/perfume/o.632.jpg");
                p6.setSize("90ml");
                p6.setIsActive(true);
                p6.setCategoryGender(catMujer);

                Perfume p7 = new Perfume();
                p7.setProductName("BLACK ORCHID");
                p7.setBrand("TOM FORD");
                p7.setPrice(130000L);
                p7.setStock(20);
                p7.setDescPerfume("Oriental oscuro con trufa, orquídea y chocolate; lujoso y envolvente.");
                p7.setImage("https://fimgs.net/mdimg/perfume/o.1018.jpg");
                p7.setSize("100ml");
                p7.setIsActive(false);
                p7.setCategoryGender(catHombre);

                Perfume p8 = new Perfume();
                p8.setProductName("LE MALE");
                p8.setBrand("JEAN PAUL GAULTIER");
                p8.setPrice(45990L);
                p8.setStock(10);
                p8.setDescPerfume("Aromático dulce con menta, lavanda y vainilla; icónico y sensual.");
                p8.setImage("https://fimgs.net/mdimg/perfume-thumbs/375x500.430.jpg");
                p8.setSize("125ml");
                p8.setIsActive(true);
                p8.setCategoryGender(catHombre);

                Perfume p9 = new Perfume();
                p9.setProductName("HOMME INTENSE");
                p9.setBrand("DIOR");
                p9.setPrice(115000L);
                p9.setStock(30);
                p9.setDescPerfume("Amaderado-ambarado con iris y cacao; elegante, cremoso y duradero.");
                p9.setImage("https://home.ripley.cl/store/Attachment/WOP/D327/2000316913627/2000316913627_2.jpg");
                p9.setSize("100ml");
                p9.setIsActive(false);
                p9.setCategoryGender(catHombre);

                Perfume p10 = new Perfume();
                p10.setProductName("BOTTLED");
                p10.setBrand("HUGO BOSS");
                p10.setPrice(98000L);
                p10.setStock(30);
                p10.setDescPerfume("Manzana y canela sobre maderas cálidas; versátil para el día a día.");
                p10.setImage("https://fimgs.net/mdimg/perfume-thumbs/375x500.75183.jpg");
                p10.setSize("100ml");
                p10.setIsActive(false);
                p10.setCategoryGender(catHombre);

                Perfume p11 = new Perfume();
                p11.setProductName("COLONIA");
                p11.setBrand("ACQUA DI PARMA");
                p11.setPrice(160000L);
                p11.setStock(50);
                p11.setDescPerfume("Cítrico clásico italiano con neroli y lavanda; limpio y sofisticado.");
                p11.setImage("https://fimgs.net/mdimg/perfume/o.1681.jpg");
                p11.setSize("100ml");
                p11.setIsActive(false);
                p11.setCategoryGender(catHombre);

                Perfume p12 = new Perfume();
                p12.setProductName("1 MILLION");
                p12.setBrand("PACO RABANNE");
                p12.setPrice(105000L);
                p12.setStock(10);
                p12.setDescPerfume("Dulce especiado con pomelo, canela y cuero; llamativo y nocturno.");
                p12.setImage("https://fimgs.net/mdimg/perfume-thumbs/375x500.60035.jpg");
                p12.setSize("100ml");
                p12.setIsActive(false);
                p12.setCategoryGender(catHombre);

                // Guardamos todos los perfumes
                perfumeRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
                System.out.println(">>> Perfumes iniciales creados y vinculados.");
            }
        };
    }
}