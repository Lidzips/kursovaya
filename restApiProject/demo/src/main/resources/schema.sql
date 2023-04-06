CREATE TABLE "symbol"(
	"id" SERIAL PRIMARY KEY,
	"code" TEXT DEFAULT '' NOT NULL
);

CREATE TABLE "alphabet"(
	"id" SERIAL PRIMARY KEY,
	"name" TEXT DEFAULT '' NOT NULL,
	"symbol_id" INT REFERENCES "symbol"("id")
);

CREATE TABLE "symbol_set"(
	"id" SERIAL PRIMARY KEY,
	"symbol_id_1" INT REFERENCES "symbol"("id"),
	"symbol_id_2" INT REFERENCES "symbol"("id"),
	"symbol_id_3" INT REFERENCES "symbol"("id")
);

CREATE TABLE "rule"(
	"id" SERIAL PRIMARY KEY,
	"in_ssid" INT REFERENCES "symbol_set"("id"),
	"out_ssid" INT REFERENCES "symbol_set"("id")
);

CREATE TABLE "rule_set"(
	"id" SERIAL PRIMARY KEY,
	"alphabet_from_id" INT REFERENCES "alphabet"("id"),
	"alphabet_to_id" INT REFERENCES "alphabet"("id"),
	"rule_id" INT REFERENCES "rule"("id")
);

SELECT symbol_in.character AS input_characters, alphabet_from.name AS input_alphabet,
symbol_out.character AS output_characters, alphabet_to.name AS output_alphabet
FROM rule
JOIN symbol AS symbol_in ON symbol_in.id = rule.symbol_in_id
JOIN symbol AS symbol_out ON symbol_out.id = rule.symbol_out_id
JOIN alphabet AS alphabet_from ON alphabet_from.id = rule.alphabet_from_id
JOIN alphabet AS alphabet_to ON alphabet_to.id = rule.alphabet_to_id
WHERE alphabet_from.name = 'russian' AND alphabet_to.name = 'english';
