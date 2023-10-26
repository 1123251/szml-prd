package com.yt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.SneakyThrows;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = {DraftApp.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class DraftAppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DraftAppTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DraftAppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    @org.junit.jupiter.api.Test
    @SneakyThrows
    public void testApp()
    {

//        MvcResult mvcResult = mockMvc.perform(post("/product/proList")
//        ).andReturn();
//        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
//        Mockito.verify(productMapper,Mockito.times(1)).selectByRulers(Mockito.any(SelectRulers.class));


    }
}
