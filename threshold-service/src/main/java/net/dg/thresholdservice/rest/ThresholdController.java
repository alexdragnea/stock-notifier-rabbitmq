package net.dg.thresholdservice.rest;

import lombok.AllArgsConstructor;
import net.dg.thresholdservice.entity.TresholdData;
import net.dg.thresholdservice.service.impl.ThresholdServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/threshold")
@AllArgsConstructor
public class ThresholdController {

    private ThresholdServiceImpl tresholdService;

    @PostMapping("/set")
    public ResponseEntity<TresholdData> setThreshold(
            @RequestParam String email,
            @RequestParam String stockSymbol,
            @RequestParam BigDecimal threshold) {
        TresholdData tresholdData = tresholdService.setThreshold(email, stockSymbol, threshold);

        return new ResponseEntity<>(
                tresholdData, HttpStatus.OK);
    }
}
