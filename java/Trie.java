package MergePrefix;
import java.util.*;

public class Trie {

    private Map paths=new HashMap();
    private boolean isWord=false;

    public void insert(String data) {
        if (data==null) {
            return;
        }
        byte [] bytes=data.getBytes();
        Trie current=this;
        for (byte b: bytes) {
            if (!current.paths.containsKey(b))  {
                Trie next=new Trie();
                current.paths.put(b, next);
                current=next;
            } else {
                current=current.paths.get(b);
            }
        }
        current.isWord=true;
    }

    public boolean contains(String data) {
        if (data==null) {
            return false;
        }
        byte [] bytes=data.getBytes();
        Trie current=this;
        for (byte b: bytes) {
            if (!current.paths.containsKey(b))  {
                return false;
            } else {
                current=current.paths.get(b);
            }
        }
        return current.isWord;
    }
}
