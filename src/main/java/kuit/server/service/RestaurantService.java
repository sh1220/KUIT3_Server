package kuit.server.service;

import kuit.server.common.exception.RestaurantException;
import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.restaurant.PostRestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_STORENAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public PostRestaurantResponse register(PostRestaurantRequest postRestaurantRequest) {

        // category,delivery_area는 enum으로 검사셋 만들어서 체크해야하나?

        validateStoreName(postRestaurantRequest.getStore_name());

        long storeId = restaurantDao.createRestaurant(postRestaurantRequest);

        return new PostRestaurantResponse(storeId);
    }

    private void validateStoreName(String storeName) {
        if(restaurantDao.hasDuplicateStoreName(storeName)){
            throw new RestaurantException(DUPLICATE_STORENAME);
        }
    }
}
