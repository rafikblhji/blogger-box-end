package com.dauphine.blogger.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // Cette route repond a l'URL /hello-world et renvoie un message fixe
    @GetMapping("hello-world")
    @Tag(
            name="Hello World API",
            description="My first endpoint api "
    )
    public String helloWorld() {
        return "Hello World!";
    }

    // Ici on utilise un parametre de requete (ex: /hello?name=Jean)
    @GetMapping("hello")
    public String helloByName(@RequestParam String name) {
        return "Hello " + name;
    }

    // C'est la partie encadree en rouge : on recupere la variable directement dans l'URL (ex: /hello/Jean)
    @GetMapping("hello/{name}")
    @Operation(
            summary="Hello by name endpoint",
            description="Returns Hello {name} by path variable"
    )
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }
}