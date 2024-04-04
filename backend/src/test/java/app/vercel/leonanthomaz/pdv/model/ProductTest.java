package app.vercel.leonanthomaz.pdv.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductTest {
    public static final Product PRODUCT_VALID =
            Product.builder()
                    .name("PRODUTO A")
                    .price(20.0)
                    .codeBar("78545454754547")
                    .build();
}


