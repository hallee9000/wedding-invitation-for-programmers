package pers.peixinyi.wedding.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-20 16:22
 */
public interface WeddingService {
    /**
     * 获取婚礼留言
     *
     * @return
     */
    List<String> getWeddingLeaveMessage() throws IOException;

    /**
     * 添加婚礼留言
     *
     * @return
     */
    Boolean addWeddingLeaveMessage(String WeddingLeave) throws IOException;
}
