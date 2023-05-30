CREATE TABLE "orders" (
                         "id" SERIAL PRIMARY KEY,
                         "hotel_id" INT NOT NULL,
                         "room_id" INT NOT NULL,
                         "customer_id" INT NOT NULL,
                         "from_date" DATE NOT NULL,
                         "to_date" DATE NOT NULL
);

