package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.DigitaldocumentDao;
import com.example.transactionmanagementdemo.domain.entity.Digitaldocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DigitaldocumentService {
    private final DigitaldocumentDao digitaldocumentDao;

    @Autowired
    public DigitaldocumentService(DigitaldocumentDao digitaldocumentDao) {
        this.digitaldocumentDao = digitaldocumentDao;
    }

//    @Transactional
    public List<Digitaldocument> getAllDigitaldocument(){return digitaldocumentDao.getAllDigitaldocument();};

}

