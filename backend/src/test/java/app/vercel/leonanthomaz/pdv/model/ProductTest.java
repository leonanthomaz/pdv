package app.vercel.leonanthomaz.pdv.model;

import app.vercel.leonanthomaz.pdv.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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


