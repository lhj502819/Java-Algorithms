package cn.onenine.algorithms.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description：字典树
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/28 22:16
 */
public class Trie {

    public final static TrieNode wordsTree = new TrieNode();

    /**
     * 插入元素
     * @param words
     * @param explain
     */
    public void insert(String words,String explain){
        TrieNode root = wordsTree;
        //拆解元素
        char[] chars = words.toCharArray();
        for (char c : chars) {
            //- a 表示从0开始，a的ASCII码十进制表示为97，往后依次加1，因此都减a，就从0坐标开始
            int idx = c - 'a';
            if(root.slot[idx] == null){
                root.slot[idx] = new TrieNode();
            }
            root = root.slot[idx];
            root.c = c;
            root.prefix++;
        }
        root.explain = explain;//单词的注释说明信息
        root.isWord = true;//循环拆解电磁后标记
    }

    /**
     * 根据前缀查找元素，比如根据ba，模糊匹配出battle、batch
     * @param prefix
     * @return
     */
    public List<String> searchPrefix(String prefix){
        TrieNode root = wordsTree;
        char[] chars = prefix.toCharArray();
        StringBuilder cache = new StringBuilder();
        //精确匹配：根据前置精确查找
        for (char c : chars) {
            int idx = c - 'a';
            //匹配为空
            if(idx > root.slot.length || idx < 0|| root.slot[idx] == null){
                return Collections.emptyList();
            }
            cache.append(c);
            root = root.slot[idx];
        }
        //模糊匹配：根据前缀的最后一个单词，递归遍历所有的单词
        ArrayList<String> list = new ArrayList<>();
        if(root.prefix != 0){
            for (int i = 0; i < root.slot.length; i++) {
                if (root.slot[i] != null) {
                    char c = (char) (i + 'a');
                    collect(root.slot[i],String.valueOf(cache) +c,list,15 );
                    if(list.size() >= 15){
                        return list;
                    }
                }
            }
        }
        return list;
    }
    protected void collect(TrieNode trieNode, String pre, List<String> queue, int resultLimit) {
        // 找到单词
        if (trieNode.isWord) {
            trieNode.word = pre;
            // 保存检索到的单词到 queue
            queue.add(trieNode.word + " -> " + trieNode.explain);
            if (queue.size() >= resultLimit) {
                return;
            }
        }
        // 递归调用，查找单词
        for (int i = 0; i < trieNode.slot.length; i++) {
            char c = (char) ('a' + i);
            if (trieNode.slot[i] != null) {
                collect(trieNode.slot[i], pre + c, queue, resultLimit);
            }
        }
    }
}
