//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.maxcar.controller;

import com.google.common.collect.Maps;
import com.maxcar.core.utils.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.SafeEncoder;

public  class RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);
    private static final int EXPIRE = 60000;
    private static ResourceBundle properties;
    private static RedisUtil instance;
    private static JedisPool jedisPool;
    private static ReentrantLock lock = new ReentrantLock();
    private RedisTemplate<String, Object> redisTemplate;
    private final RedisUtil.Keys keys = new RedisUtil.Keys();
    private final RedisUtil.Strings strings = new RedisUtil.Strings();
    private final RedisUtil.Lists lists = new RedisUtil.Lists();
    private final RedisUtil.Sets sets = new RedisUtil.Sets();
    private final RedisUtil.Hash hash = new RedisUtil.Hash();
    private final RedisUtil.SortSet sortset = new RedisUtil.SortSet();

    private RedisUtil() {
    }

    public static RedisUtil getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new RedisUtil();
            }

            lock.unlock();
        }

        return instance;
    }

    private void initJedisPool() {
        //properties = ResourceBundle.getBundle("redis-config");
        int maxActive = 200;
        int maxIdle = 20;
        int maxWait = 100000;
        String ip = "192.168.3.61";
        String pwd = "lmaxcar2017t";
        int port = 6881;
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis((long)maxWait);
        jedisPool = new JedisPool(config, ip, port, 60000, pwd);
    }

    private Jedis getJedis() {
        Jedis jedis = null;
        if (jedisPool == null) {
            lock.lock();
            this.initJedisPool();
            lock.unlock();
            log.info("JedisPool init success！");
        }

        try {
            jedis = jedisPool.getResource();
            return jedis;
        } catch (JedisConnectionException var4) {
            String message = StringUtils.trim(var4.getMessage());
            if ("Could not get a resource from the pool".equalsIgnoreCase(message)) {
                System.out.println("++++++++++请检查你的redis服务++++++++");
                System.out.println("|①.请检查是否安装redis服务|");
                System.out.println("|②.请检查redis 服务是否启动。|");
                System.out.println("|③.请检查redis启动是否带配置文件启动，也就是是否有密码，是否端口有变化（默认6379）。|");
                System.out.println("项目退出中....生产环境中，请删除这些东西。");
            }

            throw new JedisConnectionException(var4);
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }

    private void closeJedis(Jedis jedis) {
        jedis.close();
    }

    public RedisUtil.Keys keys() {
        return this.keys;
    }

    public RedisUtil.Strings strings() {
        return this.strings;
    }

    public RedisUtil.Lists lists() {
        return this.lists;
    }

    public RedisUtil.Sets sets() {
        return this.sets;
    }

    public RedisUtil.Hash hash() {
        return this.hash;
    }

    public RedisUtil.SortSet sortSet() {
        return this.sortset;
    }

    public boolean hasKey(String key) {
        try {
            return this.redisTemplate.hasKey(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public Object get(String key) {
        return key == null ? null : this.redisTemplate.opsForValue().get(key);
    }

    public static void main(String[] args) {
        /*Map<String, Object> map = Maps.newHashMap();
        map.put("openId", 1);*/
        //getInstance().keys().incr("key");
        System.out.println(getInstance().getJedis().get("taobao_010_sessionkey"));
    }

    public class Hash {
        public Hash() {
        }

        public long hdel(String key, String fieid) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.hdel(key, new String[]{fieid});
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long hdel(String key) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.del(key);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public boolean hexists(String key, String fieid) {
            Jedis sjedis = RedisUtil.this.getJedis();
            boolean s = sjedis.hexists(key, fieid);
            RedisUtil.this.closeJedis(sjedis);
            return s;
        }

        public String hget(String key, String fieid) {
            Jedis sjedis = RedisUtil.this.getJedis();
            String s = sjedis.hget(key, fieid);
            RedisUtil.this.closeJedis(sjedis);
            return s;
        }

        public byte[] hget(byte[] key, byte[] fieid) {
            Jedis sjedis = RedisUtil.this.getJedis();
            byte[] s = sjedis.hget(key, fieid);
            RedisUtil.this.closeJedis(sjedis);
            return s;
        }

        public Map<String, String> hgetAll(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Map<String, String> map = sjedis.hgetAll(key);
            RedisUtil.this.closeJedis(sjedis);
            return map;
        }

        public long hset(String key, String fieid, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.hset(key, fieid, value);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long hset(String key, String fieid, byte[] value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long hsetnx(String key, String fieid, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.hsetnx(key, fieid, value);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public List<String> hvals(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            List<String> list = sjedis.hvals(key);
            RedisUtil.this.closeJedis(sjedis);
            return list;
        }

        public long hincrby(String key, String fieid, long value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.hincrBy(key, fieid, value);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public Set<String> hkeys(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Set<String> set = sjedis.hkeys(key);
            RedisUtil.this.closeJedis(sjedis);
            return set;
        }

        public long hlen(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long len = sjedis.hlen(key);
            RedisUtil.this.closeJedis(sjedis);
            return len;
        }

        public List<String> hmget(String key, String... fieids) {
            Jedis sjedis = RedisUtil.this.getJedis();
            List<String> list = sjedis.hmget(key, fieids);
            RedisUtil.this.closeJedis(sjedis);
            return list;
        }

        public List<byte[]> hmget(byte[] key, byte[]... fieids) {
            Jedis sjedis = RedisUtil.this.getJedis();
            List<byte[]> list = sjedis.hmget(key, fieids);
            RedisUtil.this.closeJedis(sjedis);
            return list;
        }

        public String hmset(String key, Map<String, String> map) {
            Jedis jedis = RedisUtil.this.getJedis();
            String s = jedis.hmset(key, map);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public String hmset(byte[] key, Map<byte[], byte[]> map) {
            Jedis jedis = RedisUtil.this.getJedis();
            String s = jedis.hmset(key, map);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }
    }

    public class Keys {
        public Keys() {
        }

        public long expire(String key, int seconds) {
            if (seconds <= 0) {
                return -1L;
            } else {
                Jedis jedis = RedisUtil.this.getJedis();
                long result = jedis.expire(key, seconds);
                RedisUtil.this.closeJedis(jedis);
                return result;
            }
        }

        public long expire(String key) {
            return this.expire(key, 60000);
        }

        public long expireAt(String key, long timestamp) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.expireAt(key, timestamp);
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public Jedis getJedisPool() {
            Jedis jedis = RedisUtil.this.getJedis();
            return jedis;
        }

        public long ttl(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long len = sjedis.ttl(key);
            RedisUtil.this.closeJedis(sjedis);
            return len;
        }

        public long persist(String key) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.persist(key);
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public String flushAll() {
            Jedis jedis = RedisUtil.this.getJedis();
            String stata = jedis.flushAll();
            RedisUtil.this.closeJedis(jedis);
            return stata;
        }

        public boolean exists(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            boolean exis = sjedis.exists(key);
            RedisUtil.this.closeJedis(sjedis);
            return exis;
        }

        public String rename(String oldKey, String newKey) {
            return this.rename(SafeEncoder.encode(oldKey), SafeEncoder.encode(newKey));
        }

        public long renamenx(String oldKey, String newKey) {
            Jedis jedis = RedisUtil.this.getJedis();
            long status = jedis.renamenx(oldKey, newKey);
            RedisUtil.this.closeJedis(jedis);
            return status;
        }

        public String rename(byte[] oldKey, byte[] newKey) {
            Jedis jedis = RedisUtil.this.getJedis();
            String status = jedis.rename(oldKey, newKey);
            RedisUtil.this.closeJedis(jedis);
            return status;
        }

        public long del(String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.del(keys);
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public long incr(String keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            long id = jedis.incr(keys);
            RedisUtil.this.closeJedis(jedis);
            return id;
        }

        public long del(byte[]... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.del(keys);
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public List<String> sort(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            List<String> list = sjedis.sort(key);
            RedisUtil.this.closeJedis(sjedis);
            return list;
        }

        public List<String> sort(String key, SortingParams parame) {
            Jedis jedis = RedisUtil.this.getJedis();
            List<String> list = jedis.sort(key, parame);
            RedisUtil.this.closeJedis(jedis);
            return list;
        }

        public String type(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            String type = sjedis.type(key);
            RedisUtil.this.closeJedis(sjedis);
            return type;
        }

        public Set<String> keys(String pattern) {
            Jedis jedis = RedisUtil.this.getJedis();
            Set<String> set = jedis.keys(pattern);
            RedisUtil.this.closeJedis(jedis);
            return set;
        }
    }

    public class Lists {
        public Lists() {
        }

        public long llen(String key) {
            return this.llen(SafeEncoder.encode(key));
        }

        public long llen(byte[] key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long count = sjedis.llen(key);
            RedisUtil.this.closeJedis(sjedis);
            return count;
        }

        public String lset(byte[] key, int index, byte[] value) {
            Jedis jedis = RedisUtil.this.getJedis();
            String status = jedis.lset(key, (long)index, value);
            RedisUtil.this.closeJedis(jedis);
            return status;
        }

        public String lset(String key, int index, String value) {
            return this.lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
        }

        public String lindex(String key, int index) {
            return SafeEncoder.encode(this.lindex(SafeEncoder.encode(key), index));
        }

        public byte[] lindex(byte[] key, int index) {
            Jedis sjedis = RedisUtil.this.getJedis();
            byte[] value = sjedis.lindex(key, (long)index);
            RedisUtil.this.closeJedis(sjedis);
            return value;
        }

        public String lpop(String key) {
            return SafeEncoder.encode(this.lpop(SafeEncoder.encode(key)));
        }

        public byte[] lpop(byte[] key) {
            Jedis jedis = RedisUtil.this.getJedis();
            byte[] value = jedis.lpop(key);
            RedisUtil.this.closeJedis(jedis);
            return value;
        }

        public String rpop(String key) {
            Jedis jedis = RedisUtil.this.getJedis();
            String value = jedis.rpop(key);
            RedisUtil.this.closeJedis(jedis);
            return value;
        }

        public long lpush(String key, String value) {
            return this.lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public long rpush(String key, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.rpush(key, new String[]{value});
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public long rpush(byte[] key, byte[] value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.rpush(key, new byte[][]{value});
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public long lpush(byte[] key, byte[] value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.lpush(key, new byte[][]{value});
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public List<String> lrange(String key, long start, long end) {
            Jedis sjedis = RedisUtil.this.getJedis();
            List<String> list = sjedis.lrange(key, start, end);
            RedisUtil.this.closeJedis(sjedis);
            return list;
        }

        public List<byte[]> lrange(byte[] key, int start, int end) {
            Jedis sjedis = RedisUtil.this.getJedis();
            List<byte[]> list = sjedis.lrange(key, (long)start, (long)end);
            RedisUtil.this.closeJedis(sjedis);
            return list;
        }

        public long lrem(byte[] key, int c, byte[] value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long count = jedis.lrem(key, (long)c, value);
            RedisUtil.this.closeJedis(jedis);
            return count;
        }

        public long lrem(String key, int c, String value) {
            return this.lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
        }

        public String ltrim(byte[] key, int start, int end) {
            Jedis jedis = RedisUtil.this.getJedis();
            String str = jedis.ltrim(key, (long)start, (long)end);
            RedisUtil.this.closeJedis(jedis);
            return str;
        }

        public String ltrim(String key, int start, int end) {
            return this.ltrim(SafeEncoder.encode(key), start, end);
        }
    }

    public class Sets {
        public Sets() {
        }

        public long sadd(String key, String member) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.sadd(key, new String[]{member});
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long sadd(byte[] key, byte[] member) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.sadd(key, new byte[][]{member});
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long scard(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long len = sjedis.scard(key);
            RedisUtil.this.closeJedis(sjedis);
            return len;
        }

        public Set<String> sdiff(String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            Set<String> set = jedis.sdiff(keys);
            RedisUtil.this.closeJedis(jedis);
            return set;
        }

        public long sdiffstore(String newKey, String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.sdiffstore(newKey, keys);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public Set<String> sinter(String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            Set<String> set = jedis.sinter(keys);
            RedisUtil.this.closeJedis(jedis);
            return set;
        }

        public long sinterstore(String newKey, String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.sinterstore(newKey, keys);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public boolean sismember(String key, String member) {
            Jedis sjedis = RedisUtil.this.getJedis();
            boolean s = sjedis.sismember(key, member);
            RedisUtil.this.closeJedis(sjedis);
            return s;
        }

        public Set<String> smembers(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Set<String> set = sjedis.smembers(key);
            RedisUtil.this.closeJedis(sjedis);
            return set;
        }

        public Set<byte[]> smembers(byte[] key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Set<byte[]> set = sjedis.smembers(key);
            RedisUtil.this.closeJedis(sjedis);
            return set;
        }

        public long smove(String srckey, String dstkey, String member) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.smove(srckey, dstkey, member);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public String spop(String key) {
            Jedis jedis = RedisUtil.this.getJedis();
            String s = jedis.spop(key);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long srem(String key, String member) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.srem(key, new String[]{member});
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public Set<String> sunion(String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            Set<String> set = jedis.sunion(keys);
            RedisUtil.this.closeJedis(jedis);
            return set;
        }

        public long sunionstore(String newKey, String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.sunionstore(newKey, keys);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }
    }

    public class SortSet {
        public SortSet() {
        }

        public long zadd(String key, double score, String member) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.zadd(key, score, member);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long zcard(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long len = sjedis.zcard(key);
            RedisUtil.this.closeJedis(sjedis);
            return len;
        }

        public long zcount(String key, double min, double max) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long len = sjedis.zcount(key, min, max);
            RedisUtil.this.closeJedis(sjedis);
            return len;
        }

        public long zlength(String key) {
            long len = 0L;
            Set<String> set = this.zrange(key, 0, -1);
            len = (long)set.size();
            return len;
        }

        public double zincrby(String key, double score, String member) {
            Jedis jedis = RedisUtil.this.getJedis();
            double s = jedis.zincrby(key, score, member);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public Set<String> zrange(String key, int start, int end) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Set<String> set = sjedis.zrange(key, (long)start, (long)end);
            RedisUtil.this.closeJedis(sjedis);
            return set;
        }

        public Set<String> zrangeByScore(String key, double min, double max) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Set<String> set = sjedis.zrangeByScore(key, min, max);
            RedisUtil.this.closeJedis(sjedis);
            return set;
        }

        public long zrank(String key, String member) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long index = sjedis.zrank(key, member);
            RedisUtil.this.closeJedis(sjedis);
            return index;
        }

        public long zrevrank(String key, String member) {
            Jedis sjedis = RedisUtil.this.getJedis();
            long index = sjedis.zrevrank(key, member);
            RedisUtil.this.closeJedis(sjedis);
            return index;
        }

        public long zrem(String key, String member) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.zrem(key, new String[]{member});
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long zrem(String key) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.del(key);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long zremrangeByRank(String key, int start, int end) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.zremrangeByRank(key, (long)start, (long)end);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public long zremrangeByScore(String key, double min, double max) {
            Jedis jedis = RedisUtil.this.getJedis();
            long s = jedis.zremrangeByScore(key, min, max);
            RedisUtil.this.closeJedis(jedis);
            return s;
        }

        public Set<String> zrevrange(String key, int start, int end) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Set<String> set = sjedis.zrevrange(key, (long)start, (long)end);
            RedisUtil.this.closeJedis(sjedis);
            return set;
        }

        public double zscore(String key, String memebr) {
            Jedis sjedis = RedisUtil.this.getJedis();
            Double score = sjedis.zscore(key, memebr);
            RedisUtil.this.closeJedis(sjedis);
            return score != null ? score : 0.0D;
        }
    }

    public class Strings {
        public Strings() {
        }

        public String get(String key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            String value = sjedis.get(key);
            RedisUtil.this.closeJedis(sjedis);
            return value;
        }

        public byte[] get(byte[] key) {
            Jedis sjedis = RedisUtil.this.getJedis();
            byte[] value = sjedis.get(key);
            RedisUtil.this.closeJedis(sjedis);
            return value;
        }

        public String setEx(String key, int seconds, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            String str = jedis.setex(key, seconds, value);
            RedisUtil.this.closeJedis(jedis);
            return str;
        }

        public String setEx(byte[] key, int seconds, byte[] value) {
            Jedis jedis = RedisUtil.this.getJedis();
            String str = jedis.setex(key, seconds, value);
            RedisUtil.this.closeJedis(jedis);
            return str;
        }

        public long setnx(String key, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long str = jedis.setnx(key, value);
            RedisUtil.this.closeJedis(jedis);
            return str;
        }

        public String set(String key, String value) {
            return this.set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public String set(String key, byte[] value) {
            return this.set(SafeEncoder.encode(key), value);
        }

        public String set(byte[] key, byte[] value) {
            Jedis jedis = RedisUtil.this.getJedis();
            String status = jedis.set(key, value);
            RedisUtil.this.closeJedis(jedis);
            return status;
        }

        public long setRange(String key, long offset, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long len = jedis.setrange(key, offset, value);
            RedisUtil.this.closeJedis(jedis);
            return len;
        }

        public long append(String key, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            long len = jedis.append(key, value);
            RedisUtil.this.closeJedis(jedis);
            return len;
        }

        public long decrBy(String key, long number) {
            Jedis jedis = RedisUtil.this.getJedis();
            long len = jedis.decrBy(key, number);
            RedisUtil.this.closeJedis(jedis);
            return len;
        }

        public long incrBy(String key, long number) {
            Jedis jedis = RedisUtil.this.getJedis();
            long len = jedis.incrBy(key, number);
            RedisUtil.this.closeJedis(jedis);
            return len;
        }

        public String getrange(String key, long startOffset, long endOffset) {
            Jedis sjedis = RedisUtil.this.getJedis();
            String value = sjedis.getrange(key, startOffset, endOffset);
            RedisUtil.this.closeJedis(sjedis);
            return value;
        }

        public String getSet(String key, String value) {
            Jedis jedis = RedisUtil.this.getJedis();
            String str = jedis.getSet(key, value);
            RedisUtil.this.closeJedis(jedis);
            return str;
        }

        public List<String> mget(String... keys) {
            Jedis jedis = RedisUtil.this.getJedis();
            List<String> str = jedis.mget(keys);
            RedisUtil.this.closeJedis(jedis);
            return str;
        }

        public String mset(String... keysvalues) {
            Jedis jedis = RedisUtil.this.getJedis();
            String str = jedis.mset(keysvalues);
            RedisUtil.this.closeJedis(jedis);
            return str;
        }

        public long strlen(String key) {
            Jedis jedis = RedisUtil.this.getJedis();
            long len = jedis.strlen(key);
            RedisUtil.this.closeJedis(jedis);
            return len;
        }
    }
}
