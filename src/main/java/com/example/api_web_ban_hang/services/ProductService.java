package com.example.api_web_ban_hang.services;

import com.example.api_web_ban_hang.dto.ImageProductDTO_Ver1;
import com.example.api_web_ban_hang.dto.ProductDTO_Ver1;
import com.example.api_web_ban_hang.models.entities.ImageProduct;
import com.example.api_web_ban_hang.models.entities.Product;
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
     * Phương thức chuyển đổi từ List<ImageProduct> sang List<ImageProductDTO_Ver1>
     */
    private List<ImageProductDTO_Ver1> convertImageProductToDTO(Set<ImageProduct> imageProducts) {
        return imageProducts.stream()
                .map(img -> new ImageProductDTO_Ver1(img.getId(), img.getPath()))
                .collect(Collectors.toList());
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
                "WHERE p.typeProduct.id = :idTypeProduct " +
                "AND p.idStatusProduct = :idStatusProduct";

        TypedQuery<Product> query = _entityManager.createQuery(jpql, Product.class);
        query.setParameter("idTypeProduct", type_product);
        query.setParameter("idStatusProduct", status_product);

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
     * Đếm số lượng sản phẩm theo loại và trạng thái
     * <p>
     * VD:
     * + Đếm số lượng sản phẩm áo đá banh có trạng thái MỚI
     * + Đếm số lượng áo đá banh có trạng thái HOT
     */
    @Override
    public int countProductsByTypeAndStatus(int type_product, int status_product) {
        String jpql = "SELECT COUNT(p) FROM Product p " +
                "WHERE p.typeProduct.id = :idTypeProduct " +
                "AND p.idStatusProduct = :idStatusProduct";

        Query query = _entityManager.createQuery(jpql)
                .setParameter("idTypeProduct", type_product)
                .setParameter("idStatusProduct", status_product);

        return ((Number) query.getSingleResult()).intValue();
    }

    /**
     * Lấy ra danh sách sản phẩm theo loại,thương hiệu và giới tính
     * <p>
     * VD:
     * + Lấy ra danh sách áo đá banh của hãng Nike dành cho Nam
     * + Lấy ra danh sách áo đá banh của hãng Adidas dành cho Nữ
     *
     * @param type_product : loại sản phẩm
     * @param brand        : thương hiệu
     * @param sex          : giới tính
     */
    @Override
    public List<ProductDTO_Ver1> getListProductBy_TypeAndBrandAndSex(int type_product, int brand, int sex, int page, int page_size) {

        String jpql = "SELECT DISTINCT p FROM Product p " +
                "WHERE p.typeProduct.id = :idTypeProduct " +
                "AND p.brand.id = :idBrandProduct " +
                "AND p.idSex= :idSex";

        TypedQuery<Product> query = _entityManager.createQuery(jpql, Product.class);
        query.setParameter("idTypeProduct", type_product);
        query.setParameter("idBrandProduct", (long) brand); //=> cần phải ép kiểu về long tại idBrand trong entity Brand là long
        query.setParameter("idSex", sex);

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
     * Đếm số lượng sản phẩm theo loại,thương hiệu và giới tính
     * <p>
     * VD:
     * + Đếm số lượng sản phẩm áo đá banh của hãng Nike dành cho Nam
     * + Đếm số lượng sản phẩm áo đá banh của hãng Adidas dành cho Nữ
     *
     * @param type_product : loại sản phẩm
     * @param brand        : thương hiệu
     * @param sex          : giới tính
     */
    @Override
    public int countProductsBy_TypeAndBrandAndSex(int type_product, int brand, int sex) {
        String jpql = "SELECT COUNT(p) FROM Product p " +
                "WHERE p.typeProduct.id = :idTypeProduct " +
                "AND p.brand.id = :idBrandProduct " +
                "AND p.idSex= :idSex";

        Query query = _entityManager.createQuery(jpql)
                .setParameter("idTypeProduct", type_product)
                .setParameter("idBrandProduct", (long) brand) //=> cần phải ép kiểu về long tại idBrand trong entity Brand là long
                .setParameter("idSex", sex);

        return ((Number) query.getSingleResult()).intValue();
    }
}
