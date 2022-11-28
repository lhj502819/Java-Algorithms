package cn.onenine.algorithms.trie.test;


import cn.onenine.algorithms.trie.Trie;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 11:42
 */
public class TrieTest {

    private static Logger logger = LoggerFactory.getLogger(TrieTest.class);

    @Test
    public void test_trie() {
        Trie trie = new Trie();
        // 存入
        trie.insert("bat","大厂");
        trie.insert("batch", "批量");
        trie.insert("bitch", "彪子");
        trie.insert("battle", "战斗");
        logger.info(trie.toString());
        // 检索
        List<String> trieNodes = trie.searchPrefix("bt");
        logger.info("测试结果：{}", JSONObject.toJSONString(trieNodes));
    }

}

