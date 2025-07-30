package com.tenant.test.service;

import com.tenant.test.entity.TestData;
import com.tenant.test.repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataService {
    
    @Autowired(required = false)
    private TestDataRepository testDataRepository;
    
    public List<TestData> findAll() {
        return testDataRepository.findAll();
    }
    
    public TestData findById(Long id) {
        return testDataRepository.findById(id).orElse(null);
    }
    
    public TestData save(TestData testData) {
        return testDataRepository.save(testData);
    }
    
    public void deleteById(Long id) {
        testDataRepository.deleteById(id);
    }
}