package cn.onenine.algorithms.trie;

/**
 * Description：树枝节点
 *      字典树的节点需要包括此节点内嵌的关联节点，之后是节点的字母、到此字母是否为单词、单词的前缀、单词字符串和当前单词的非必要注释
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/28 22:12
 */
public class TrieNode {

    /**
     * 形成一个链
     */
    public  TrieNode[] slot = new TrieNode[26];

    /**
     * 字母
     */
    public char c;

    /**
     * 单词：数量 > 0 表示一个单词
     */
    public boolean isWord;

    /**
     * 前缀
     */
    public int prefix;

    /**
     * 单词，具体的一个单词字符串
     */
    public String word;

    /**
     * 解释：单词的注释说明
     */
    public String explain;



    public TrieNode[] getSlot() {
        return slot;
    }

    public void setSlot(TrieNode[] slot) {
        this.slot = slot;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
