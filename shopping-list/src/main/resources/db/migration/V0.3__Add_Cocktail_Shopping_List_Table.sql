create table COCKTAIL_SHOPPING_LIST (
                                        COCKTAIL_ID UUID not null,
                                        SHOPPING_LIST_ID UUID not null
);

ALTER TABLE COCKTAIL_SHOPPING_LIST
    ADD FOREIGN KEY (cocktail_id)
        REFERENCES COCKTAIL (ID);


ALTER TABLE COCKTAIL_SHOPPING_LIST
    ADD FOREIGN KEY (shopping_list_id)
        REFERENCES SHOPPING_LIST (ID);