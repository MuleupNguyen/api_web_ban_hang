package com.example.api_web_ban_hang.services;

import com.example.api_web_ban_hang.dto.ImageProductDTO_Ver1;
import com.example.api_web_ban_hang.dto.ProductDTO_Ver1;
import com.example.api_web_ban_hang.models.ImageProduct;
import com.example.api_web_ban_hang.models.Product;
import com.example.api_web_ban_hang.services.interfaces.ICountProductService;
import com.example.api_web_ban_hang.services.interfaces.IGetProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Query;

@Service
public class ProductService implements IGetProductService, ICountProductService {
    @PersistenceContext
    private EntityManager _entityManager;

    public ProductService(EntityManager entityManager) {
        this._entityManager = entityManager;
    }

    /**
     * Lấy ra danh sách sản phẩm theo loại và trạng thái
     * VD:
     * + Lấy ra danh sách áo đá banh đang có trạng thái MỚI
     * + Lấy ra danh sách áo đá banh đang có trạng thái HOT
     */
    @Override
    @Transactional
    public List<ProductDTO_Ver1> getListProductByTypeAndStatus(int type_product, int status_product, int page, int page_size) {

        String jpql = "SELECT DISTINCT p FROM Product p " +
                "WHERE p.typeProduct.id = :typeProduct AND p.idStatusProduct = :statusProduct";

        TypedQuery<Product> query = _entityManager.createQuery(jpql, Product.class);
        query.setParameter("typeProduct", type_product);
        query.setParameter("statusProduct", status_product);

        // Phân trang kết quả trả về
        query.setFirstResult((page - 1) * page_size);
        query.setMaxResults(page_size);

        List<Product> productList = query.getResultList();

        if (productList == null || productList.isEmpty()) return null;

        // convert thành DTO dùng StreamAPI trong Java 8
        return productList.stream().map(product -> {

            ProductDTO_Ver1 productDTO = ProductDTO_Ver1.builder()
                    .id_product(product.getId())
                    .name_product(product.getNameProduct())
                    .listed_price(product.getListedPrice())
                    .promotional_price(product.getPromotionalPrice())
                    .list_image(convertImageProductToDTO(product.getImageProducts())) // Gọi phương thức chuyển đổi thành DTO
                    .id_status_product(product.getIdStatusProduct())
                    .build();

            return productDTO;
        }).collect(Collectors.toList());
    }

    /**
     * Phương thức chuyển đổi từ List<ImageProduct> sang List<ImageProductDTO_Ver1>
     */
    private List<ImageProductDTO_Ver1> convertImageProductToDTO(Set<ImageProduct> imageProducts) {
        return imageProducts.stream()
                .map(img -> new ImageProductDTO_Ver1(img.getId(), img.getPath()))
                .collect(Collectors.toList());
    }

    /**
     * Đếm số lượng sản phẩm theo loại và trạng thái
     * <p>
     * VD:
     * + Đếm số lượng sản phẩm áo đá banh có trạng thái MỚI
     * + Đếm số lượng áo đá banh có trạng thái HOT
     */
    @Override
    public int countProductsByTypeAndStatus(int type_product, int status_product) {
        String jpql = "SELECT COUNT(p) FROM Product p " +
                "WHERE p.typeProduct.id = :typeProductId " +
                "AND p.idStatusProduct = :statusProductId";

        Query query = _entityManager.createQuery(jpql)
                .setParameter("typeProductId", type_product)
                .setParameter("statusProductId", status_product);

        return ((Number) query.getSingleResult()).intValue();
    }
}
