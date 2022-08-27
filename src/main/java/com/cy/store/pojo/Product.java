package com.cy.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends basePojo implements Serializable {
        private Integer id;
        private Integer categoryId;
        private String itemType;
        private String title;
        private String sellPoint;
        private Long price;
        private Integer num;
        private String image;
        private Integer status;
        private Integer priority;
}
