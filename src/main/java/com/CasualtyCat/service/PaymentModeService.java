package com.CasualtyCat.service;

import com.CasualtyCat.entity.PaymentMode;

import java.util.Optional;

public interface PaymentModeService {

    Optional<PaymentMode> findByName(String nmae);


}
