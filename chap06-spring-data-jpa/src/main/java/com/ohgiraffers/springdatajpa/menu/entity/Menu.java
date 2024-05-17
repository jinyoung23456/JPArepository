package com.ohgiraffers.springdatajpa.menu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_menu")
@Getter
/* @Setter 지양 -> 객체를 언제든지 변경할 수 있으면 객체의 안정성 보장 X
* 값 변경이 필요한 경우에는 해당 기능을 위한 메소드 별도 생성 필요 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* @AllArgsConstructor */
/* @ToString : 사용 가능하나 연관 관계 매핑 필드는 제거하고 사용 */
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

    public void modifyMenuName(String menuName) {

        this.menuName = menuName;
    }
}
