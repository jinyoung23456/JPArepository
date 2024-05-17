package com.ohgiraffers.associationmapping.section03.bidirection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BiDirectionService {

    private BiDirectionRepository biDirectionRepository;

    public BiDirectionService(BiDirectionRepository biDirectionRepository){
        this.biDirectionRepository = biDirectionRepository;
    }

    public Menu findMenu(int menuCode) {
        return biDirectionRepository.findMenu(menuCode);
    }

    @Transactional
    public Category findCategory(int categoryCode) {
        Category category = biDirectionRepository.findCategory(categoryCode);
        System.out.println(category.getMenuList());
        System.out.println(category.getMenuList().get(0).getCategory());
        return category;
    }

    @Transactional
    public void registMenu(Menu menu) {
        biDirectionRepository.saveMenu(menu);
    }

    @Transactional
    public void registCategory(Category category) {
        biDirectionRepository.saveCategory(category);
    }


}
