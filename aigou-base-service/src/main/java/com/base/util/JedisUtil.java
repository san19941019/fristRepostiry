package com.base.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    static final String redisHost = "127.0.0.1";
    static final int redisPort = 6379;
    static Jedis jd;
    static {
    	JedisPoolConfig jpf=new JedisPoolConfig();
    	jpf.setMaxTotal(10);
    	jpf.setMaxIdle(5);
    	jpf.setMaxWaitMillis(2*1000);
    	JedisPool jp=new JedisPool(jpf,redisHost,redisPort);
    	 jd=jp.getResource();
    }
    public void putJedis(String key,String value){  
    	jd.setex(key, 10, value);
    	jd.close();
    }
    
    public String getJedis(String key){  
    	String value=jd.get(key);
    	jd.close();
    	return value;
    }
}
