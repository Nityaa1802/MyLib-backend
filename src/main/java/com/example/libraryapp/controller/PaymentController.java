package com.example.libraryapp.controller;

import com.example.libraryapp.requestmodels.PaymentInfoRequest;
import com.example.libraryapp.service.PaymentService;
import com.example.libraryapp.utils.ExtractJWT;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment/secure")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPayment(@RequestBody PaymentInfoRequest paymentInfoRequest)throws StripeException{

        PaymentIntent paymentIntent=paymentService.createPaymentIntent(paymentInfoRequest);
        String paymentStr=paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
    @PutMapping("/payment-complete")
    public ResponseEntity<String> stripePaymentComplete(@RequestHeader(value="Authorization") String token)throws Exception{
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (userEmail==null){
            throw new Exception("USerEmail is missing");
        }
        return paymentService.stripePayment(userEmail);
    }
}
