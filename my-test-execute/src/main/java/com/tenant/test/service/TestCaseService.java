package com.tenant.test.service;

import com.tenant.test.entity.TestCase;
import com.tenant.test.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {
    
    @Autowired(required = false)
    private TestCaseRepository testCaseRepository;
    
    public List<TestCase> findAll() {
        return testCaseRepository.findAll();
    }
    
    public TestCase findById(Long id) {
        return testCaseRepository.findById(id).orElse(null);
    }
    
    public TestCase save(TestCase testCase) {
        return testCaseRepository.save(testCase);
    }
    
    public void deleteById(Long id) {
        testCaseRepository.deleteById(id);
    }
}