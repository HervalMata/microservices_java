package com.herval.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PCODE", nullable = false)
    private String productCode;

    @Column(name = "NAME", nullable = false)
    private String productName;

    @Column(name = "SHORT_DESCRIPTION", nullable = false)
    private String shortDescription;

    @Column(name = "LONG_DESCRIPTION", nullable = false)
    private String longDescription;

    @Column(name = "CANDISPLAY", nullable = false)
    private boolean canDisplay;

    @Column(name = "ISDELETED", nullable = false)
    private boolean isDeleted;

    @OneToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String toString() {
        return "Produto [id =" + id + ", Código=" + productCode + ", Nome=" + productName + ", Descrição Curta=" + shortDescription + ", Descrição Longa=" + longDescription
                + " Mostrar=" + canDisplay + ", Pode Remover=" + isDeleted + ", SubCategoria="
                + parentCategory + ", Categoria=" + category + "]";
    }
}
