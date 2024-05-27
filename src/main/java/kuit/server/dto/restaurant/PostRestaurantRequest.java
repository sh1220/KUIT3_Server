package kuit.server.dto.restaurant;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRestaurantRequest {

    private String category;
    private String store_name;
    private String delivery_area;

}
