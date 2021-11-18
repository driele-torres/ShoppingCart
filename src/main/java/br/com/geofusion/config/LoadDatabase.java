package br.com.geofusion.config;

import br.com.geofusion.ShoppingCart.model.ProductSynthetic;
import br.com.geofusion.ShoppingCart.repository.ProductSyntheticRepository;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    CommandLineRunner initDatabase(ProductSyntheticRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save( new ProductSynthetic("Cobertura Chocolate", new BigDecimal(456), new BigDecimal("4.56")) ));
            log.info("Preloading " + repository.save( new ProductSynthetic("Forminha Lisa", new BigDecimal(200), new BigDecimal("6.31")) ));
            log.info("Preloading " + repository.save( new ProductSynthetic("Prato Descartável", new BigDecimal(154), new BigDecimal("2.10")) ));
            log.info("Preloading " + repository.save( new ProductSynthetic("Wafer Hersheys", new BigDecimal(789), new BigDecimal("9.23")) ));
        };
    }
}