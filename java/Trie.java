import java.util.*;

/** 
 * Implementation of the Trie data structure
 * */
public class Trie {

    public Map paths=new HashMap();
    private boolean isWord=false;

    /** 
     * Skip insert and use constructor
     * @param data String for a full word
     * */
    public Trie(String data){
        this.insert(data);
    }

    /**
     * Still need a blank constructor
     * */
    public Trie(){
    }

    /**
     * Insert a word into our structure for fast prefix matching
     * @param data String for a full word we'll check with prefixes
     * */
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

    /** 
     * Check if a prefix exists in our Trie object
     * @param data Prefix we're testing with
     * @return boolean describing if prefix exists in tree
     * */
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

    /** 
     * Get status of whether we've decided its a word or not
     * @return boolean of whether its in a word or not
     * */
    public boolean getIsWord(){
        return this.isWord;
    }

    /** 
     * Set the boolean for isWord
     * @param b boolean we're setting isWord to
     * */
    public void setIsWord(boolean b){
        this.isWord = b;
    }
}
