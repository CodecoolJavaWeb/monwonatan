package com.codecool.queststore.backend.webControllers.studentController;

import com.codecool.queststore.backend.dao.BackpackDAO;
import com.codecool.queststore.backend.model.Backpack;
import com.codecool.queststore.backend.webControllers.AbstractHandler;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.MockitoJUnit;


import static org.junit.jupiter.api.Assertions.*;


class StudentStoreTest extends AbstractHandler {

    @Mock
    private BackpackDAO mockBackPackDao;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    @Test
    public void testUpdateBackPack() {
        MockitoAnnotations.initMocks(this);
        Backpack backpack = new Backpack("login");
        when(mockBackPackDao.removeBackpack(backpack)).thenReturn(false);
        when(mockBackPackDao.addBackpack(backpack)).thenReturn(true);

        boolean temp = mockBackPackDao.updateBackpack(backpack);
        assertFalse(temp);
    }

}