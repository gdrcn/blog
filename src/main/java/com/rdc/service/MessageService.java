package com.rdc.service;

import com.rdc.dao.MessageDao;
import com.rdc.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    /**
     * 发送信息
     * @param from_user_id
     * @param to_user_id
     * @param content
     * @author chen
     * @return
     */
    @Transactional
    public boolean postMessage(int from_user_id,int to_user_id,String content){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(day);
        if(messageDao.postMessage(from_user_id,to_user_id,time,content)>0)
            return true;
        else
         return false;
    }
}
