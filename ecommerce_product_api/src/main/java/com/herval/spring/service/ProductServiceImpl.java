package com.herval.spring.service;

import com.herval.spring.dao.ProductDaoRepository;
import com.herval.spring.model.Category;
import com.herval.spring.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDaoRepository productDaoRepository;

    @Override
    public Product save(Product product) {

        Category category = null;
        Category parentCategory = null;

        if (product.getCategory() == null) {
            logger.info("A categoria do produto é nula :");
            //throw new HTTP400Exception("Categoria não pode estar em branco");
        } else {
            logger.info("A categoria do produto não está nula : " + product.getCategory());
            logger.info("A categoria do produto não está nula ID : " + product.getCategory().getId());
        }

        if (product.getParentCategory() == null) {
            logger.info("A categoria do produto é nula :");
            //throw new HTTP400Exception("Categoria não pode estar em branco");
        } else {
            logger.info("A categoria do produto não está nula : " + product.getParentCategory());
            logger.info("A categoria do produto não está nula ID : " + product.getParentCategory().getId());
        }
        return productDaoRepository.save(product);
    }

    @Override
    public Optional<Product> get(long id) {
        return productDaoRepository.findById(id);
    }

    @Override
    public Page<Product> getProductsByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productCode").descending());
        return productDaoRepository.findAll(pageable);
    }

    @Override
    public void update(long id, Product product) {
        productDaoRepository.save(product);
    }

    @Override
    public void delete(long id) {
        productDaoRepository.deleteById(id);
    }
}
