CREATE TABLE IF NOT EXISTS room (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      price DECIMAL(10, 2),
                      available BOOLEAN,
                      SIZE DECIMAL(2,0),
                      ROOM_PICTURE_NAME VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS hotel_user (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255),
                        email VARCHAR(255),
                        password VARCHAR(255)
                        );

INSERT INTO room (name, price, available, room_picture_name, SIZE) VALUES
                                                                 ('Deluxe Room', 3000.00, true, '1.jpg', 4),
                                                                 ('Standard Room', 1500.00, true, '2.jpg', 3),
                                                                 ('Standard Room', 1200.00, true, '2.jpg', 2),
                                                                 ('Suite Room', 3800.00, true, '3.jpg', 5),
                                                                 ('Suite Room', 3500.00, true, '3.jpg', 4);

-- hotel_user tablosuna örnek kullanıcı ekleme
INSERT INTO hotel_user (name, email, password) VALUES
                                                   ('nisasilaaltun', 'nisasilaaltun@test.com', '1234'),
                                                   ('test', 'test@test.com', '1234');
