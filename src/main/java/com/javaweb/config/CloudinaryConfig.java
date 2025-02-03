package com.javaweb.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> map = new HashMap<>();
        map.put("cloud_name", "diju35umx");
        map.put("api_key", "126973877632524");
        map.put("api_secret", "Jn81qsBQIs0GBygOQyIwmsj0R0I");
        return new Cloudinary(map);
    }
}