import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Trie {
    private int SIZE=26;
    private TrieNode root;
    class TrieNode{
        private int num;
        private TrieNode[] son;
        private char val;
        private boolean isEnd;
        TrieNode(){
            num=1;
            son=new TrieNode[SIZE];
            isEnd=false;
        }
    }
    Trie(){
        root=new TrieNode();
    }

    public void insert(String str){
        if(str==null||str.length()==0){
            return;
        }
        TrieNode node=root;
        char[] letters=str.toCharArray();
        int len=str.length();
        for(int i=0;i<len;i++){
            int pos=letters[i]-'a';
            if(node.son[pos]==null){
                node.son[pos]=new TrieNode();
                node.son[pos].val=letters[i];
            }
            else{
                node.son[pos].num++;
            }
            node=node.son[pos];
        }
        node.isEnd=true;

    }

    public int countPrefix(String prefix){
        if(prefix==null||prefix.length()==0){
            return -1;
        }
        TrieNode node=root;
        char[] letters=prefix.toCharArray();
        for(int i=0,len=prefix.length();i<len;i++){
            int pos=letters[i]-'a';
            if(node.son[pos]==null){
                return 0;
            }
            else {
                node=node.son[pos];
            }
        }
        return node.num;
    }

    public String hasPrefix(String prefix){
        if(prefix==null||prefix.length()==0){
            return null;
        }
        TrieNode node=root;
        char[] letters=prefix.toCharArray();
        for(int i=0,len=prefix.length();i<len;i++){
            int pos=letters[i]-'a';
            if(node.son[pos]==null){
                return null;
            }
            else {
                node=node.son[pos];
            }
        }
        preTraverse(node,prefix);
        return null;
    }
    public void preTraverse(TrieNode node,String prefix){
        if(!node.isEnd){
            for(TrieNode child:node.son){
                if(child!=null){
                    preTraverse(child,prefix+child.val);
                }
            }
            return;
        }
        System.out.println(prefix);
    }
    public boolean has(String str){
        if(str==null||str.length()==0){
            return false;
        }
        TrieNode node=root;
        char[] letters=str.toCharArray();
        for(int i=0,len=str.length();i<len;i++){
            int pos=letters[i]-'a';
            if(node.son[pos]!=null){
                node=node.son[pos];
            }
            else {
                return false;
            }
        }
        return node.isEnd;
    }

    public void preTraverse(TrieNode node){
        if(node!=null){
            System.out.println(node.val+"-");
            for(TrieNode child:node.son){
                preTraverse(child);
            }
        }
    }
    private TrieNode getRoot(){
        return this.root;
    }

    public static void main(String[] args)throws IOException {
        Trie tree=new Trie();
        String[] dictionaryData={"hello","student","dingziyi","cuiyangjing"};

        for(String str:dictionaryData){
            tree.insert(str);
        }
        String filePath="C:\\Users\\lenovo\\Desktop\\sourceFile.txt";
        File file=new File(filePath);
        if(file.isFile()&&file.exists()){
            InputStreamReader read=new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader=new BufferedReader(read);
            String LineTxt=null;
            Map<String,Integer> conutMap=new HashMap<String,Integer>();
            while ((LineTxt=bufferedReader.readLine())!=null){
                if(tree.has(LineTxt)){
                    if(conutMap.containsKey(LineTxt)){
                        conutMap.put(LineTxt,conutMap.get(LineTxt)+1);
                    }
                    else {
                        conutMap.put(LineTxt,1);
                    }
                }
                else {
                    System.out.println(LineTxt+" not existed");
                }
            }
            for(String s:conutMap.keySet()){
                System.out.println(s+"出现次数"+conutMap.get(s));
            }
            read.close();
        }
    }
}
