package com.common.redis.cache.aspect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.common.redis.cache.manager.ListCacheManager;
import com.common.redis.cache.service.CacheKeyGenerator;
import com.common.redis.cache.service.CacheSerializer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ListCacheAspect 单元测试
 * 
 * @author Kiro
 * @version 1.0.0
 */
class ListCacheAspectTest {
    
    @Mock
    private ListCacheManager cacheManager;
    
    @Mock
    private CacheKeyGenerator keyGenerator;
    
    @Mock
    private CacheSerializer cacheSerializer;
    
    @InjectMocks
    private ListCacheAspect listCacheAspect;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testExtractContentFromResult_DirectList() throws Exception {
        // 测试直接返回List的情况
        List<String> directList = Arrays.asList("item1", "item2", "item3");
        
        Method method = ListCacheAspect.class.getDeclaredMethod("extractContentFromResult", Object.class);
        method.setAccessible(true);
        
        List<?> result = (List<?>) method.invoke(listCacheAspect, directList);
        
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("item1", result.get(0));
    }
    
    @Test
    void testExtractContentFromResult_BodyContentStructure() throws Exception {
        // 测试 result -> body -> content 结构
        TestResultWithBodyContent testResult = new TestResultWithBodyContent();
        testResult.body = new TestBody();
        testResult.body.content = Arrays.asList("item1", "item2", "item3");
        
        Method method = ListCacheAspect.class.getDeclaredMethod("extractContentFromResult", Object.class);
        method.setAccessible(true);
        
        List<?> result = (List<?>) method.invoke(listCacheAspect, testResult);
        
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("item1", result.get(0));
    }
    
    @Test
    void testExtractContentFromResult_DirectContentField() throws Exception {
        // 测试直接包含content字段的情况
        TestResultWithDirectContent testResult = new TestResultWithDirectContent();
        testResult.content = Arrays.asList("item1", "item2");
        
        Method method = ListCacheAspect.class.getDeclaredMethod("extractContentFromResult", Object.class);
        method.setAccessible(true);
        
        List<?> result = (List<?>) method.invoke(listCacheAspect, testResult);
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("item1", result.get(0));
    }
    
    @Test
    void testExtractContentFromResult_DataField() throws Exception {
        // 测试包含data字段的情况
        TestResultWithDataField testResult = new TestResultWithDataField();
        testResult.data = Arrays.asList("data1", "data2");
        
        Method method = ListCacheAspect.class.getDeclaredMethod("extractContentFromResult", Object.class);
        method.setAccessible(true);
        
        List<?> result = (List<?>) method.invoke(listCacheAspect, testResult);
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("data1", result.get(0));
    }
    
    @Test
    void testExtractContentFromResult_NullResult() throws Exception {
        // 测试null结果
        Method method = ListCacheAspect.class.getDeclaredMethod("extractContentFromResult", Object.class);
        method.setAccessible(true);
        
        List<?> result = (List<?>) method.invoke(listCacheAspect, (Object) null);
        
        assertNull(result);
    }
    
    @Test
    void testExtractContentFromResult_NoValidField() throws Exception {
        // 测试没有有效字段的情况
        TestResultWithoutValidField testResult = new TestResultWithoutValidField();
        testResult.someField = "not a list";
        
        Method method = ListCacheAspect.class.getDeclaredMethod("extractContentFromResult", Object.class);
        method.setAccessible(true);
        
        List<?> result = (List<?>) method.invoke(listCacheAspect, testResult);
        
        assertNull(result);
    }
    
    // 测试用的内部类
    public static class TestResultWithBodyContent {
        public TestBody body;
    }
    
    public static class TestBody {
        public List<String> content;
    }
    
    public static class TestResultWithDirectContent {
        public List<String> content;
    }
    
    public static class TestResultWithDataField {
        public List<String> data;
    }
    
    public static class TestResultWithoutValidField {
        public String someField;
    }
}