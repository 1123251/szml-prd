package com.yt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yt.controller.ApprovalController;
import com.yt.entity.Approval;
import com.yt.mapper.ApprovalMapper;
import com.yt.service.ApprovalService;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.jboss.resteasy.mock.MockHttpRequest.post;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class ApprovalAppTest
    extends TestCase
{
    @MockBean
    ApprovalMapper approvalMapper;
    @MockBean
    ApprovalService approvalService;
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ApprovalController())
            .build();

    @Test
    void testApprove() throws Exception {
        Approval approval = new Approval();
        // set approval fields here

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform((RequestBuilder) post("/approval").contentType(String.valueOf(MediaType.APPLICATION_JSON)).content(objectMapper.writeValueAsString(approval).getBytes()))
                .andExpect(status().isOk());

        verify(approvalService).approval(approval, Integer.valueOf(1));
    }
    @Test
    public  void testService(){


    }
}
