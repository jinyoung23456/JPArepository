package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButton;
import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model) {
        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);
        model.addAttribute("menu", resultMenu);

        return "menu/detail";
    }

    @GetMapping("/list")
    public String findMenuList(Model model, @PageableDefault Pageable pageable) {

        /* 페이징 처리 이전 */
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menuList", menuList);

        /* 페이징 처리 이후 */
        log.info("pageable: {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);

        log.info("{}", menuList.getContent());
        log.info("{}", menuList.getTotalElements());
        log.info("{}", menuList.getTotalPages());
        log.info("{}", menuList.getSize());
        log.info("{}", menuList.getNumberOfElements());
        log.info("{}", menuList.isFirst());
        log.info("{}", menuList.isLast());
        log.info("{}", menuList.getSort());
        log.info("{}", menuList.getNumber());

        PagingButton paging = Pagenation.getPagingButtonInfo(menuList);

        model.addAttribute("menuList", menuList);
        model.addAttribute("paging", paging);

        return "menu/list";

    }

    @GetMapping("/querymethod")
    public void querymethodPage(){}

    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam Integer menuPrice, Model model){

        List<MenuDTO> menuList = menuService.findMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);

        return "menu/searchResult";
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping("/category")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }


    @PostMapping("/regist")
    public String registNewMenu(@ModelAttribute MenuDTO menuDTO) {

        menuService.registMenu(menuDTO);

        return "redirect:/menu/list";
    }

    @GetMapping("/modify")
    public void modifyPage(){}

    @PostMapping("/modify")
    public String modifyMenu(@ModelAttribute MenuDTO menuDTO) {
        menuService.modifyMenu(menuDTO);
        return "redirect:/menu/" + menuDTO.getMenuCode();
    }

    @GetMapping("/delete")
    public void deletePage(){}

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam Integer menuCode) {
        menuService.deleteMenu(menuCode);
        return "redirect:/menu/list";
    }


}
