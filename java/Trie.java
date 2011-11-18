import java.util.*;

public class Trie {

    public Map paths=new HashMap();
    private boolean isWord=false;

    public Trie(String data){
        this.insert(data);
    }

    public Trie(){
    }

    public void insert(String data){
        if (data == null) {
            return;
        }
        char[] chars = data.toCharArray();
        Trie current = this;
        for(char c: chars) {
            if(!current.paths.containsKey(c))  {
                Trie next = new Trie();
                current.paths.put(c, (Object) next);
                current = next;
            }
            else {
                current = (Trie) current.paths.get(c);
            }
        }
        current.setIsWord(true);
    }

    public boolean contains(String data) {
        if(data == null) {
            return false;
        }
        char[] chars = data.toCharArray();
        Trie current = this;
        for(char c: chars) {
            char up = Character.toUpperCase(c);
            char low = Character.toLowerCase(c);
            if( current != null){
                if(!current.paths.containsKey(low)) {
                    current = (Trie) current.paths.get(up);
                }
                else if(!current.paths.containsKey(up)) {
                    current = (Trie) current.paths.get(low);
                }
                else return false;
            }
            else return false;
        }
        return true;
    }

    public boolean getIsWord(){
        return this.isWord;
    }

    public void setIsWord(boolean b){
        this.isWord = b;
    }
}
