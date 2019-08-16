package com.ecommerce.microcommerce.web.controller;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.repository.ProductRepository;
import com.ecommerce.microcommerce.web.exceptions.ProduitIntrouvableException;
import com.ecommerce.microcommerce.web.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api( description="API pour es opérations CRUD sur les produits.")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    //Récupérer la liste des produits
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/Produits", method = RequestMethod.GET)
    public List<Product> listeProduits() {
        List<Product> produits = productRepository.findAll();

        //SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        //FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        //MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
        //produitsFiltres.setFilters(listDeNosFiltres);
        //return produitsFiltres;

        return produits;
    }

    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    //Récupérer un produit par son Id
    @GetMapping(value = "/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        Product product = productRepository.findById(id);

        if(product==null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE. Écran Bleu si je pouvais.");

        return product;
    }


    @GetMapping(value = "test/produits/{prixLimit}")
    public List<Product> testeDeRequetes(@PathVariable int prixLimit) {
        return productRepository.findByPrixGreaterThan(400);
    }

    @GetMapping(value = "tes/produits/{recherche}")
    public List<Product> testeDeRequetes(@PathVariable String recherche) {
        return productRepository.findByNomLike("%"+recherche+"%");
    }


    //Récupérer un produit par son Id
    @GetMapping(value = "/example")
    public String example() {
        return "ah";
    }

    //ajouter un produit
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {

        Product productAdded =  productRepository.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    //TODO : why doesn't work ??
//    @DeleteMapping (value = "/Produits/{id}")
//    public void supprimerProduit(@PathVariable int id) {
//
//        productDao.delete(id);
//    }

    @GetMapping(value = "/test")
    public int testInt(@PathVariable int intege)
    {
        return intege;
    }

    @PutMapping (value = "/Produits")
    public void updateProduit(@RequestBody Product product) {

        productRepository.save(product);
    }

    @GetMapping(value = "/test/sql/{prix}")
    public List<Product> GetProduitCher(@PathVariable int prix)
    {
        return productRepository.chercherUnProduitCher(prix);
    }


    @GetMapping(value = "/Produits/money/max")
    public Product GetProductWithMoreMoney()
    {
        List<Product> products = productRepository.findAll();

        return productService.GetProductWithMoreMoney(products);
    }
}
