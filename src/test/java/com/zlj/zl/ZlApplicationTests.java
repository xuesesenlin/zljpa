package com.zlj.zl;

import com.zlj.zl.user.PMC.model.PMCModel;
import com.zlj.zl.user.PMC.service.PMCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZlApplicationTests {

    @Autowired
    private PMCService service;

    @Test
    public void contextLoads() {
        List<PMCModel> list = service.findByCityName("唐山市");
    }

}
