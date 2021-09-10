package com.tang.jedis;

import com.tang.jedis.utils.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class JedisDemo {
    public static void main(String[] args) {
     /*   Jedis jedis = new Jedis("192.168.149.128", 6379);
        String str1 = jedis.get("str1");
        System.out.println(str1);
        Long count = jedis.sadd("set1", "java", "php", "c");
        jedis.close();*/

       //获取连接池对象
        JedisPool jedisPool = RedisUtils.open("192.168.149.128", 6379);
        //获取jedis对象
        Jedis jedis = jedisPool.getResource();
        //开启事务
        Transaction transaction = jedis.multi();
        transaction.flushDB();
        transaction.set("str1", "for");
        transaction.set("str2", "doyou");
        List<Object> list = transaction.exec();
        for(Object o:list){
            System.out.println(o);
        }

        RedisUtils.close();
    }
}
