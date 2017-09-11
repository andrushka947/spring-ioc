package com.epam.ioc;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ContextTest {

    @Test(expected = NoSuchBeanException.class)
    public void getBeanWithEmptyContext() throws Exception {
        Context context = new ApplicationContext();
        context.getBean("abc");
    }

    @Test
    public void getBeanDefinitionNamesWithEmptyContext() throws Exception {
        Context context = new ApplicationContext();

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithOneBeanDefinition() throws Exception {
        String beanName = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put(beanName,
                            new HashMap<String, Object>(){{
                                put("type", beanType);
                            }}
                        );
                }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {beanName};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithEmptyBeanDefinition() throws Exception {
        Map<String, Map<String, Object>> beanDescriptions = Collections.emptyMap();
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanDefinitionNamesWithSeveralBeanDefinition() throws Exception {
        String beanNameFirst = "FirstBean";
        String beanNameSecond = "SecondBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put(beanNameFirst, new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
                    put(beanNameSecond, new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
                }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        String[] actual = context.getBeanDefinitionNames();

        String[] expected = {beanNameFirst, beanNameSecond};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBeanWithOneBeanDefinitionIsNotNull() throws Exception {
        String beanName = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put(beanName, new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
                }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        Object bean = context.getBean(beanName);

        assertNotNull(bean);
    }

    @Test
    public void getBeanWithOneBeanDefinition() throws Exception {
        String beanName = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put(beanName, new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
                }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean = (TestBean) context.getBean(beanName);

        assertNotNull(bean);
    }

    @Test
    public void getBeanIsSingleton() throws Exception {
        String beanName = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put(beanName, new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
                }};
        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean bean1 = (TestBean) context.getBean(beanName);
        TestBean bean2 = (TestBean) context.getBean(beanName);

        assertSame(bean1, bean2);
    }

    @Test
    public void getBeanDifferentBeansWithSameClass() throws Exception{
        String beanName1 = "FirstBean";
        String beanName2 = "SecondBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put(beanName1,
                            new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
                    put(beanName2,
                            new HashMap<String, Object>() {{
                        put("type", beanType);
                    }});
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean tb1 = (TestBean) context.getBean("FirstBean");
        TestBean tb2 = (TestBean) context.getBean("SecondBean");
        assertNotSame(tb1, tb2);
    }

    @Test
    public void getBeanIsPrototype() throws Exception{
        String beanName1 = "FirstBean";
        Class<TestBean> beanType = TestBean.class;
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put(beanName1,
                            new HashMap<String, Object>() {{
                                put("type", beanType);
                                put("isPrototype", true);
                            }});
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBean tb1 = (TestBean) context.getBean("FirstBean");
        TestBean tb2 = (TestBean) context.getBean("FirstBean");

        assertNotSame(tb1, tb2);
    }

    @Test
    public void getBeanWithDependentBeans() throws Exception{
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put("testBean",
                            new HashMap<String, Object>() {{
                                put("type", TestBean.class);
                                put("isPrototype", true);
                    }});
                    put("testBeanWithConstructorWithTwoParams",
                            new HashMap<String, Object>() {{
                                put("type", TestBeanWithConstructorWithTwoParams.class);
                                put("isPrototype", true);
                            }});
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TestBeanWithConstructorWithTwoParams tb1 =
                (TestBeanWithConstructorWithTwoParams) context.getBean("testBeanWithConstructorWithTwoParams");

        assertNotNull(tb1);
    }



    public static class TestBean{}
    public static class TestBeanWithConstructor{
        private final TestBean testBean;

        public TestBeanWithConstructor(TestBean testBean) {
            this.testBean = testBean;
        }
    }
    public static class TestBeanWithConstructorWithTwoParams{
        private final TestBean testBean1;
        private final TestBean testBean2;

        public TestBeanWithConstructorWithTwoParams(TestBean testBean1, TestBean testBean2) {
            this.testBean1 = testBean1;
            this.testBean2 = testBean2;
        }
    }
}