package com.ohgiraffers.nativequery.section02.namedquery;

import jakarta.persistence.*;

// 1-1의 매핑 이름과 다른 이름을 주어야 한다.
@SqlResultSetMapping(name = "categoryCountAutoMapping2",
        entities = {@EntityResult(entityClass = Category.class)},
        columns = {@ColumnResult(name = "menu_count")}
)
@NamedNativeQueries(
        value = {
                @NamedNativeQuery(
                        name = "Category.menuCountOfCategory",
                        query = "SELECT a.category_code, a.category_name, a.ref_category_code," +
                        " COALESCE(v.menu_count, 0) menu_count" +
                        " FROM tbl_category a" +
                        " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                        " FROM tbl_menu b" +
                        " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                        " ORDER BY 1",
                        resultSetMapping = "categoryCountAutoMapping2"
                )
        }
)



@Entity(name="Section02Category")
@Table(name="tbl_category")
public class Category {

    @Id
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
