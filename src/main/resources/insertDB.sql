
INSERT INTO `brands` VALUES (1, 'NIKE');
INSERT INTO `brands` VALUES (2, 'ADIDAS');
INSERT INTO `brands` VALUES (3, 'PUMA');

INSERT INTO `price_ranges` VALUES ('Dưới 100 Nghìn', 0.0, 99000.0);
INSERT INTO `price_ranges` VALUES ('Từ 100 đến 200 Nghìn', 100000.0, 200000.0);
INSERT INTO `price_ranges` VALUES ('Từ 200 đến 300 Nghìn', 200000.0, 300000.0);
INSERT INTO `price_ranges` VALUES ('Từ 300 đến 400 Nghìn', 300000.0, 400000.0);
INSERT INTO `price_ranges` VALUES ('Trên 400 Nghìn', 400000.0, 10000000.0);

INSERT INTO `sizes` VALUES ('XS');
INSERT INTO `sizes` VALUES ('S');
INSERT INTO `sizes` VALUES ('M');
INSERT INTO `sizes` VALUES ('L');
INSERT INTO `sizes` VALUES ('XL');
INSERT INTO `sizes` VALUES ('XXL');
INSERT INTO `sizes` VALUES ('XXXL');

INSERT INTO `type_products` VALUES (1, 'ÁO ĐẤU');
INSERT INTO `type_products` VALUES (2, 'ÁO HUẤN LUYỆN');
INSERT INTO `type_products` VALUES (3, 'ÁO THỦ MÔN');
INSERT INTO `type_products` VALUES (4, 'ÁO FAN');

DELIMITER //
CREATE PROCEDURE GenerateProducts()
BEGIN
  DECLARE i INT DEFAULT 1;
  DECLARE teams VARCHAR(255);

  WHILE i < 5000 DO
    -- Chọn một đội bóng châu Âu ngẫu nhiên
    SET teams = CASE
      WHEN RAND() < 0.04 THEN 'Real Madrid'
      WHEN RAND() < 0.08 THEN 'Barcelona'
      WHEN RAND() < 0.12 THEN 'Atletico Madrid'
      WHEN RAND() < 0.16 THEN 'Sevilla'
      WHEN RAND() < 0.20 THEN 'Valencia'
      WHEN RAND() < 0.24 THEN 'Manchester United'
      WHEN RAND() < 0.28 THEN 'Liverpool'
      WHEN RAND() < 0.32 THEN 'Manchester City'
      WHEN RAND() < 0.36 THEN 'Chelsea'
      WHEN RAND() < 0.40 THEN 'Arsenal'
      WHEN RAND() < 0.44 THEN 'Juventus'
      WHEN RAND() < 0.48 THEN 'AC Milan'
      WHEN RAND() < 0.52 THEN 'Inter Milan'
      WHEN RAND() < 0.56 THEN 'AS Roma'
      WHEN RAND() < 0.60 THEN 'Napoli'
      WHEN RAND() < 0.64 THEN 'Bayern Munich'
      WHEN RAND() < 0.68 THEN 'Borussia Dortmund'
      WHEN RAND() < 0.72 THEN 'RB Leipzig'
      WHEN RAND() < 0.76 THEN 'Bayer Leverkusen'
      WHEN RAND() < 0.80 THEN 'Schalke 04'
      ELSE 'Unknown Team'
END;

INSERT INTO `products` (
    `id_product`,
    `name_product`,
    `star_review`,
    `id_status_product`,
    `listed_price`,
    `promotional_price`,
    `id_brand`,
    `id_type_product`,
    `id_sex`,
    `time_created`
) VALUES (
             i,
             teams,
             FLOOR(RAND() * 5) + 1, -- Đánh giá từ 1 đến 5
             1, -- Trạng thái sản phẩm
             ROUND(RAND() * 500000 + 50000, 2), -- Giá niêm yết từ 50000 đến 550000
             ROUND(RAND() * 5000 + 50000, 2), -- Giá khuyến mãi từ 0 đến 50000
             FLOOR(RAND() * 3) + 1, -- ID thương hiệu từ 1 đến 3
             FLOOR(RAND() * 4) + 1, -- ID loại sản phẩm từ 1 đến 4
             FLOOR(RAND() * 2), -- Giới tính từ 0 (Nam) hoặc 1 (Nữ)
             NOW() - INTERVAL FLOOR(RAND() * 365) DAY -- Ngày tạo từ 0 đến 365 ngày trước
         );

SET i = i + 1;
END WHILE;
END //
DELIMITER ;

-- Gọi thủ tục để tạo sản phẩm
CALL GenerateProducts();

DELETE FROM size_products;
DROP PROCEDURE IF EXISTS GenerateSizeProducts;
-- Tạo 5000 bản ghi ngẫu nhiên cho bảng size_products
DELIMITER //
CREATE PROCEDURE GenerateSizeProducts()
BEGIN
    DECLARE counter INT DEFAULT 1;

    WHILE counter < 5000 DO
        -- Chọn một sản phẩm và size ngẫu nhiên
        SET @product_id = counter;
        SET @size = CASE ROUND(RAND() * 5)
            WHEN 0 THEN 'XS'
            WHEN 1 THEN 'S'
            WHEN 2 THEN 'M'
            WHEN 3 THEN 'L'
            WHEN 4 THEN 'XL'
            WHEN 5 THEN 'XXL'
END;
        SET @quantity = FLOOR(1 + RAND() * (1000 - 1));

        -- Thêm bản ghi vào bảng size_products
INSERT INTO size_products (id_product, name_size, quantity_available)
VALUES (@product_id, @size, @quantity);

-- Tăng biến đếm
SET counter = counter + 1;
END WHILE;
END //
DELIMITER ;

-- Gọi stored procedure để tạo dữ liệu
CALL GenerateSizeProducts();


DELETE FROM image_products;
DROP PROCEDURE IF EXISTS GenerateImageProducts;
DELIMITER //
CREATE PROCEDURE GenerateImageProducts()
BEGIN
    DECLARE counter INT DEFAULT 1;

    WHILE counter < 5000 DO
        -- Chọn một sản phẩm và size ngẫu nhiên
        SET @product_id = counter;
        SET @path = CASE ROUND(RAND() * 17)
            WHEN 0 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/a7b5c3cb771f48afab17c251870ee2cb_9366/figc-og-3s-tee.jpg'
            WHEN 1 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/7b822c63da25483185708ba17bff3cdc_9366/%C3%A1o-thun-ba-l%C3%A1-essentials-fc-bayern.jpg'
            WHEN 2 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/bea3f22215fc4afb9bdb6c17a726515b_9366/%C3%A1o-%C4%91%E1%BA%A5u-s%C3%A2n-kh%C3%A1ch-real-madrid-23-24-tr%E1%BA%BB-em.jpg'
            WHEN 3 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/95041589bf7e4dd9addb7d93cbc1a89c_9366/%C3%A1o-%C4%91%E1%BA%A5u-s%C3%A2n-nh%C3%A0-authentic-real-madrid-23-24.jpg'
            WHEN 4 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/4121b1eea17a43b4959d397a48669597_9366/%C3%A1o-jersey-tr%C6%B0%E1%BB%9Bc-tr%E1%BA%ADn-real-madrid.jpg'
            WHEN 5 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/003187a257d04620ab877e3e07c1490e_9366/%C3%A1o-jersey-tr%C6%B0%E1%BB%9Bc-tr%E1%BA%ADn-arsenal.jpg'
            WHEN 6 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/207e17d357ef43d3be2882680806294e_9366/%C3%A1o-%C4%91%E1%BA%A5u-s%C3%A2n-nh%C3%A0-authentic-fc-bayern-23-24.jpg'
            WHEN 7 THEN 'https://assets.adidas.com/images/w_383,h_383,f_auto,q_auto,fl_lossy,c_fill,g_auto/dc17056aa13a474081bce25e47c64c6c_9366/%C3%A1o-jersey-tr%C6%B0%E1%BB%9Bc-tr%E1%BA%ADn-fc-bayern.jpg'
            WHEN 8 THEN 'https://p-vn.ipricegroup.com/d9ae0c4bb49a5962747333f4c531da9d541496b2_0.jpg'
            WHEN 9 THEN 'https://p-vn.ipricegroup.com/7d068125f625b78080f09dd6239ef5b0c36dfddc_0.jpg'
            WHEN 10 THEN 'https://p-vn.ipricegroup.com/9f7cd35e39568de6d0daf3e400d41de179132066_0.jpg'
            WHEN 11 THEN 'https://product.hstatic.net/1000385420/product/puma_pitch_blue_1_d33fbc226cbe4c539fb9fcb0e8d51fe1.jpg'
            WHEN 12 THEN 'https://cdn.vatgia.com/pictures/fullsize/2014/10/29/qds1414595351.jpg'
            WHEN 13 THEN 'https://cdn.vatgia.com/pictures/thumb/0x0/2014/10/cfu1414595351.jpg'
            WHEN 14 THEN 'https://product.hstatic.net/200000601263/product/276a48d6c03149a68f003235eb050997_00482d221df44feeb78e268de495ba37_1024x1024.jpg'
            WHEN 15 THEN 'https://product.hstatic.net/200000601263/product/ca8619562b794ec3a1161ef250e5d557_b3c72fa260c747d4b235d4e99f8f7e33_1024x1024.jpg'
         	WHEN 16 THEN 'https://product.hstatic.net/200000601263/product/2acdfbb8c3b74be09858d761c9a2c8ca_1d292fcbe5aa464d8d39dd8f5466e610_1024x1024.jpg'
		  		WHEN 17 THEN 'https://product.hstatic.net/200000601263/product/6b85942bf7344ad3add124e11692df34_8ed3425b9bc54167ba4b50c7d28e0dba_1024x1024.jpg'
END;
        SET @quantity = FLOOR(1 + RAND() * (1000 - 1));

        -- Thêm bản ghi vào bảng size_products
INSERT INTO image_products (path, id_product)
VALUES (@path, @product_id);

-- Tăng biến đếm
SET counter = counter + 1;
END WHILE;
END //
DELIMITER ;

-- Gọi stored procedure để tạo dữ liệu
CALL GenerateImageProducts();


DELETE FROM history_price_products;
DROP PROCEDURE IF EXISTS GenerateHistoryPriceProducts;
DELIMITER //
CREATE PROCEDURE GenerateHistoryPriceProducts()
BEGIN
    DECLARE counter INT DEFAULT 1;

    WHILE counter < 5000 DO
        -- Chọn một sản phẩm và size ngẫu nhiên
        SET @product_id = counter;
        -- Thêm bản ghi vào bảng size_products
INSERT INTO history_price_products (id_price_product, id_product, `listed_price`,`promotional_price`)
VALUES (
           @product_id,
           @product_id,
           ROUND(RAND() * 500000 + 50000, 2), -- Giá niêm yết từ 50000 đến 550000
           ROUND(RAND() * 5000 + 50000, 2) -- Giá khuyến mãi từ 0 đến 50000
       );

-- Tăng biến đếm
SET counter = counter + 1;
END WHILE;
END //
DELIMITER ;

-- Gọi stored procedure để tạo dữ liệu
CALL GenerateHistoryPriceProducts();