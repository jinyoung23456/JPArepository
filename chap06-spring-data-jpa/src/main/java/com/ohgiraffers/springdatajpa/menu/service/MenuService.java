package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    /* 1. findById */
    public MenuDTO findMenuByMenuCode(int menuCode) {
        Menu foundMenu = menuRepository.findById(menuCode).orElseThrow(IllegalAccessError::new);

        return modelMapper.map(foundMenu, MenuDTO.class);
    }

    /* 1. findAll : Sort */
    public List<MenuDTO> findMenuList() {
        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());
        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());

    }

    /* 3. findAll : Pageable */

    public Page<MenuDTO> findMenuList(Pageable pageable) {
        pageable = PageRequest.of(
                // 몇개의 페이지 사이즈
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending()
        );
        Page<Menu> menuList = menuRepository.findAll(pageable);
        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
    }


    /* 4. Query Method */
    public List<MenuDTO> findMenuPrice(Integer menuPrice) {

        List<Menu> menuList
                = menuRepository.findByMenuPriceGreaterThan(
                menuPrice,
                Sort.by("menuPrice").descending()
        );

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());

    }

    /* 5. JPQL or Native Query */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());

    }

    /* 6. Save */
    @Transactional
    public void registMenu(MenuDTO menuDTO) {

        menuRepository.save(modelMapper.map(menuDTO, Menu.class));


                /* 여러개를 저장해야 할떄 */
//        menuRepository.saveAll()
    }


    /* 7.  수정 (엔티티 객체 필드값 변경) */
    @Transactional
    public void modifyMenu(MenuDTO menuDTO) {


        Menu foundMenu = menuRepository.findById(menuDTO.getMenuCode())
                .orElseThrow(IllegalArgumentException::new);
        /* .orElseThrow = 찾지 못했을 경우 를 생각해서 작성해줘야 함 */


        /* setter 사용 (지양)
         * 이름 수정 메서드를 정의하여 사용 */
        foundMenu.modifyMenuName(menuDTO.getMenuName());


        /* 메소드가 종료 되면 변경 감지를 통해 업데이트 구문이 작동 됨 */

    }

    /* 8. deleteById */

    @Transactional
    public void deleteMenu(Integer menuCode) {

        /* 메뉴 코드를 전달 해서 삭제 */
        menuRepository.deleteById(menuCode);
    }
}
