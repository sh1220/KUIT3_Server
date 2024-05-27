package kuit.server.controller;


import kuit.server.common.exception.RestaurantException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.GetTipResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.restaurant.PostRestaurantResponse;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("")
    public BaseResponse<PostRestaurantResponse> register(@Validated @RequestBody PostRestaurantRequest postRestaurantRequest, BindingResult bindingResult) {
        log.info("[UserController.signUp]");
        if (bindingResult.hasErrors()) {
            throw new RestaurantException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(restaurantService.register(postRestaurantRequest));
    }

    @GetMapping("/{restaurantId}/delivery_tip")
    public BaseResponse<GetTipResponse>  getTip(@PathVariable long restaurantId){
        return new BaseResponse<>(restaurantService.getTip(restaurantId));
    }

}
