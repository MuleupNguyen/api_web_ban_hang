package com.example.api_web_ban_hang.services;

import com.example.api_web_ban_hang.dto.ImageProductDTO_Ver1;
import com.example.api_web_ban_hang.dto.ProductDTO_Ver1;
import com.example.api_web_ban_hang.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IGetProductService {
    @PersistenceContext
    private EntityManager _entityManager;

    public ProductService(EntityManager entityManager) {
        this._entityManager = entityManager;
    }

    /**
     * Lấy ra danh sách sản phẩm theo loại và trạng thái
     * <p>
     * VD:
     * + Lấy ra danh sách áo đá banh đang có trạng thái MỚI
     * + Lấy ra danh sách áo đá banh đang có trạng thái HOT
     */
    @Override
    @Transactional
    public List<ProductDTO_Ver1> getListProductByTypeAndStatus(int type_product, int status_product, int page, int page_size) {

        // Truy vấn JPQL để lấy danh sách sản phẩm và hình ảnh sản phẩm
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.typeProduct.id = :typeProduct AND p.idStatusProduct = :statusProduct";

        TypedQuery<Product> query = _entityManager.createQuery(jpql, Product.class);
        query.setParameter("typeProduct", type_product);
        query.setParameter("statusProduct", status_product);

        query.setFirstResult((page - 1) * page_size);
        query.setMaxResults(page_size);

        List<Product> productList = query.getResultList();

        if (productList == null || productList.isEmpty()) return null;

        // Chuyển đổi danh sách sản phẩm thành DTO
        return productList.stream().map(product -> {

            ProductDTO_Ver1 productDTO = ProductDTO_Ver1.builder()
                    .id_product(product.getId())
                    .name_product(product.getNameProduct())
                    .listed_price(product.getListedPrice())
                    .promotional_price(product.getPromotionalPrice())

                  /*  // Chuyển đổi danh sách hình ảnh sản phẩm thành DTO
                    .list_image(product.getImageProducts().stream().map(img -> {
                        return new ImageProductDTO_Ver1(img.getId(), img.getPath());
                    }).collect(Collectors.toList()))*/

                    .build();

            return productDTO;
        }).collect(Collectors.toList());
    }
}
