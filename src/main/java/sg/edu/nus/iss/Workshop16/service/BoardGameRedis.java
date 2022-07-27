package sg.edu.nus.iss.Workshop16.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Workshop16.model.Mastermind;

@Service
public class BoardGameRedis implements BoardGameRepo {
    private static final Logger logger = LoggerFactory.getLogger(BoardGameRedis.class);

    @Autowired
    @Qualifier("games")
    RedisTemplate<String, Mastermind> redisTemplate;

    @Override
    public int save(final Mastermind ctc) {
        logger.info("Save mastermind > " + logger);
        redisTemplate.opsForValue().set(ctc.getId(), ctc);
        Mastermind result = (Mastermind) redisTemplate.opsForValue().get(ctc.getId());
        if (result != null)
            return 1;
        return 0;
    }

    @Override
    public Mastermind findById(final String msId) {
        logger.info("find mastermind by id> " + msId);
        Mastermind result = (Mastermind) redisTemplate.opsForValue().get(msId);
        return result;
    }

    @Override
    public int update(final Mastermind ctc) {
        logger.info("Save mastermind > " + logger);
        if (ctc.isUpsert())
            redisTemplate.opsForValue().setIfAbsent(ctc.getId(), ctc);
        else
            redisTemplate.opsForValue().setIfPresent(ctc.getId(), ctc);
        Mastermind result = (Mastermind) redisTemplate.opsForValue().get(ctc.getId());
        if (result != null)
            return 1;
        return 0;
    }

    public Set<String> searchKeys(String index) {
        String pattern = "*%s*".formatted(index);
        return redisTemplate.keys(pattern);
    }
}
