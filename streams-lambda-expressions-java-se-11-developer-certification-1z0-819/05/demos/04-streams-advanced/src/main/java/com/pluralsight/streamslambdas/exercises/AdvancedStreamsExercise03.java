package com.pluralsight.streamslambdas.exercises;

import com.pluralsight.streamslambdas.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdvancedStreamsExercise03 {

    public enum PriceRange {
        CHEAP, MEDIUM, EXPENSIVE
    }

    /**
     * Exercise 3: Group products by price range.
     *
     * @param products       A list of products.
     * @param cheapLimit     Products cheaper than this are considered cheap.
     * @param expensiveLimit Products more expensive than this are considered expensive.
     * @return A map containing the cheap, medium and expensive products in separate lists.
     */
    public Map<PriceRange, List<Product>> groupProductsByPriceRange(List<Product> products, BigDecimal cheapLimit, BigDecimal expensiveLimit) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        product -> mapToPriceRange(product.getPrice(), cheapLimit, expensiveLimit),
                        Collectors.mapping(Function.identity(), Collectors.toList())
                ));
    }
    private PriceRange mapToPriceRange(BigDecimal productPrice, BigDecimal cheapLimit, BigDecimal expensiveLimit) {
        if (productPrice.compareTo(cheapLimit) < 0) {
            return PriceRange.CHEAP;
        } else if (productPrice.compareTo(expensiveLimit) > 0) {
            return PriceRange.EXPENSIVE;
        } else {
            return PriceRange.MEDIUM;
        }
    }
}
