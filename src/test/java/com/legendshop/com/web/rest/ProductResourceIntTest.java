package com.legendshop.com.web.rest;

import com.legendshop.com.ShopApp;
import com.legendshop.com.domain.Product;
import com.legendshop.com.repository.ProductRepository;
import com.legendshop.com.service.ProductService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopApp.class)
@WebAppConfiguration
@IntegrationTest
public class ProductResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_ICON = "AAAAA";
    private static final String UPDATED_ICON = "BBBBB";

    private static final Float DEFAULT_SALE_PRICE = 0F;
    private static final Float UPDATED_SALE_PRICE = 1F;

    private static final Float DEFAULT_MARKET_PRICE = 0F;
    private static final Float UPDATED_MARKET_PRICE = 1F;

    private static final Float DEFAULT_ORIGIN_PRICE = 1F;
    private static final Float UPDATED_ORIGIN_PRICE = 2F;

    private static final Float DEFAULT_DISCOUNT = 1F;
    private static final Float UPDATED_DISCOUNT = 2F;

    private static final Integer DEFAULT_SALE_COUNT = 1;
    private static final Integer UPDATED_SALE_COUNT = 2;

    private static final Boolean DEFAULT_IS_NEW = false;
    private static final Boolean UPDATED_IS_NEW = true;

    private static final Integer DEFAULT_GOOD_REMARK = 1;
    private static final Integer UPDATED_GOOD_REMARK = 2;

    private static final Integer DEFAULT_COMMENT_COUNT = 1;
    private static final Integer UPDATED_COMMENT_COUNT = 2;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductService productService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProductMockMvc;

    private Product product;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductResource productResource = new ProductResource();
        ReflectionTestUtils.setField(productResource, "productService", productService);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        product = new Product();
        product.setName(DEFAULT_NAME);
        product.setIcon(DEFAULT_ICON);
        product.setSalePrice(DEFAULT_SALE_PRICE);
        product.setMarketPrice(DEFAULT_MARKET_PRICE);
        product.setOriginPrice(DEFAULT_ORIGIN_PRICE);
        product.setDiscount(DEFAULT_DISCOUNT);
        product.setSaleCount(DEFAULT_SALE_COUNT);
        product.setIsNew(DEFAULT_IS_NEW);
        product.setGoodRemark(DEFAULT_GOOD_REMARK);
        product.setCommentCount(DEFAULT_COMMENT_COUNT);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product

        restProductMockMvc.perform(post("/api/products")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = products.get(products.size() - 1);
        assertThat(testProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProduct.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testProduct.getSalePrice()).isEqualTo(DEFAULT_SALE_PRICE);
        assertThat(testProduct.getMarketPrice()).isEqualTo(DEFAULT_MARKET_PRICE);
        assertThat(testProduct.getOriginPrice()).isEqualTo(DEFAULT_ORIGIN_PRICE);
        assertThat(testProduct.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testProduct.getSaleCount()).isEqualTo(DEFAULT_SALE_COUNT);
        assertThat(testProduct.isIsNew()).isEqualTo(DEFAULT_IS_NEW);
        assertThat(testProduct.getGoodRemark()).isEqualTo(DEFAULT_GOOD_REMARK);
        assertThat(testProduct.getCommentCount()).isEqualTo(DEFAULT_COMMENT_COUNT);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setName(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isBadRequest());

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalePriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setSalePrice(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isBadRequest());

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMarketPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setMarketPrice(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isBadRequest());

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the products
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())))
                .andExpect(jsonPath("$.[*].salePrice").value(hasItem(DEFAULT_SALE_PRICE.doubleValue())))
                .andExpect(jsonPath("$.[*].marketPrice").value(hasItem(DEFAULT_MARKET_PRICE.doubleValue())))
                .andExpect(jsonPath("$.[*].originPrice").value(hasItem(DEFAULT_ORIGIN_PRICE.doubleValue())))
                .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].saleCount").value(hasItem(DEFAULT_SALE_COUNT)))
                .andExpect(jsonPath("$.[*].isNew").value(hasItem(DEFAULT_IS_NEW.booleanValue())))
                .andExpect(jsonPath("$.[*].goodRemark").value(hasItem(DEFAULT_GOOD_REMARK)))
                .andExpect(jsonPath("$.[*].commentCount").value(hasItem(DEFAULT_COMMENT_COUNT)));
    }

    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()))
            .andExpect(jsonPath("$.salePrice").value(DEFAULT_SALE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.marketPrice").value(DEFAULT_MARKET_PRICE.doubleValue()))
            .andExpect(jsonPath("$.originPrice").value(DEFAULT_ORIGIN_PRICE.doubleValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.saleCount").value(DEFAULT_SALE_COUNT))
            .andExpect(jsonPath("$.isNew").value(DEFAULT_IS_NEW.booleanValue()))
            .andExpect(jsonPath("$.goodRemark").value(DEFAULT_GOOD_REMARK))
            .andExpect(jsonPath("$.commentCount").value(DEFAULT_COMMENT_COUNT));
    }

    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = new Product();
        updatedProduct.setId(product.getId());
        updatedProduct.setName(UPDATED_NAME);
        updatedProduct.setIcon(UPDATED_ICON);
        updatedProduct.setSalePrice(UPDATED_SALE_PRICE);
        updatedProduct.setMarketPrice(UPDATED_MARKET_PRICE);
        updatedProduct.setOriginPrice(UPDATED_ORIGIN_PRICE);
        updatedProduct.setDiscount(UPDATED_DISCOUNT);
        updatedProduct.setSaleCount(UPDATED_SALE_COUNT);
        updatedProduct.setIsNew(UPDATED_IS_NEW);
        updatedProduct.setGoodRemark(UPDATED_GOOD_REMARK);
        updatedProduct.setCommentCount(UPDATED_COMMENT_COUNT);

        restProductMockMvc.perform(put("/api/products")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
                .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = products.get(products.size() - 1);
        assertThat(testProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProduct.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testProduct.getSalePrice()).isEqualTo(UPDATED_SALE_PRICE);
        assertThat(testProduct.getMarketPrice()).isEqualTo(UPDATED_MARKET_PRICE);
        assertThat(testProduct.getOriginPrice()).isEqualTo(UPDATED_ORIGIN_PRICE);
        assertThat(testProduct.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testProduct.getSaleCount()).isEqualTo(UPDATED_SALE_COUNT);
        assertThat(testProduct.isIsNew()).isEqualTo(UPDATED_IS_NEW);
        assertThat(testProduct.getGoodRemark()).isEqualTo(UPDATED_GOOD_REMARK);
        assertThat(testProduct.getCommentCount()).isEqualTo(UPDATED_COMMENT_COUNT);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeDelete - 1);
    }
}
