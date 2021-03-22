import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pers.peixinyi.wedding.WeddingApplication;
import pers.peixinyi.wedding.service.WeddingService;

import java.io.IOException;
import java.util.List;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-20 16:58
 */
@SpringBootTest(classes = WeddingApplication.class)
public class WeddingApplicationTest {

    @Autowired
    WeddingService weddingService;

    @Test
    public void getWeddingLeaveMessageTest() throws IOException {
        List<String> leaveMessage = weddingService.getWeddingLeaveMessage();
        Assert.notEmpty(leaveMessage, "leaveMessage 为空");
    }

    @Test
    public void addWeddingLeaveMessage() throws IOException {
        Assert.isTrue(weddingService.addWeddingLeaveMessage("123"), "添加留言成功");
    }

}
