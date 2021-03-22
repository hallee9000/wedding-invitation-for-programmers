package pers.peixinyi.wedding.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-20 16:24
 */
@Service
public class WeddingServiceImpl implements WeddingService {

    private static Logger logger = LoggerFactory.getLogger(WeddingServiceImpl.class);

    /**
     * wedding:
     * leave:
     * path: wedding-leave.txt
     *
     * @return
     */
    @Value("${wedding.leave.path}")
    String weddingLeavePath;

    /**
     * 判断是否有写入过
     */
    static Boolean flag = false;

    /**
     * 写入列表
     */
    static List<String> weddingLeaveList = new ArrayList<String>();

    public List<String> getWeddingLeaveMessage() throws IOException {
        File file = new File(weddingLeavePath);
        create(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String weddingLeave;
        if (flag) {
            return weddingLeaveList;
        }
        weddingLeaveList.clear();
        while ((weddingLeave = bufferedReader.readLine()) != null) {
            weddingLeaveList.add(weddingLeave);
        }
        bufferedReader.close();
        fileReader.close();
        flag = true;
        return weddingLeaveList;
    }

    public Boolean addWeddingLeaveMessage(String WeddingLeave) throws IOException {
        flag = false;
        File file = new File(weddingLeavePath);
        create(file);
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(WeddingLeave );
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
        return true;
    }

    public void create(File file) throws IOException {
        if (!file.exists()) {
            logger.info("创建成功 婚礼请帖留言文件 ->{}", weddingLeavePath);
            file.createNewFile();
        }
    }
}
