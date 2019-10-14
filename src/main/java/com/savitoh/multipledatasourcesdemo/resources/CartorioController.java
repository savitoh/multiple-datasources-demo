package com.savitoh.multipledatasourcesdemo.resources;

import com.savitoh.multipledatasourcesdemo.cartorio.repository.CartorioRepository;
import com.savitoh.multipledatasourcesdemo.unidadejudicial.repository.UnidadeJudicialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartorioController {

    @Autowired
    private CartorioRepository cartorioRepository;

    @Autowired
    private UnidadeJudicialRepository unidadeJudicialRepository;

    @GetMapping
    public String getCartorio() {
        this.cartorioRepository.count();
        this.unidadeJudicialRepository.count();
        return "Teste";
    }
}