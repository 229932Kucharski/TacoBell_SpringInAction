package pl.study.TacoBell.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import pl.study.TacoBell.Order;
import pl.study.TacoBell.User;
import pl.study.TacoBell.data.OrderRepository;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return "orderForm";
    }

    @PostMapping
    public String proccessOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                                @AuthenticationPrincipal User user) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();
        log.info("Zamowienie zostalo zlozone " + order);
        return "redirect:/";
    }

}
