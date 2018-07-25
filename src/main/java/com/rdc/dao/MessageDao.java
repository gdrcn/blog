package com.rdc.dao;

import org.apache.ibatis.annotations.Param;

public interface MessageDao {

    /**
     * 发送信息
     * @param from_user_id
     * @param to_user_id
     * @param time
     * @param content
     * @return
     */
    int postMessage(@Param("from_user_id") int from_user_id,@Param("to_user_id")int to_user_id,@Param("time")String time,@Param("content")String content);
}
