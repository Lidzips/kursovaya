CREATE TABLE "alphabet"(
	"id" SERIAL PRIMARY KEY,
	"name" TEXT DEFAULT '' NOT NULL
);

CREATE TABLE "symbol"(
	"id" SERIAL PRIMARY KEY,
	"character" TEXT DEFAULT '' NOT NULL,
	"alphabet_id" INT REFERENCES "alphabet"("id")
);

CREATE TABLE "rule"(
	"id" SERIAL PRIMARY KEY,
	"alphabet_from_id" INT REFERENCES "alphabet"("id"),
	"alphabet_to_id" INT REFERENCES "alphabet"("id"),
	"symbol_in_id" INT REFERENCES "symbol"("id"),
    "symbol_out_id" INT REFERENCES "symbol"("id")
);