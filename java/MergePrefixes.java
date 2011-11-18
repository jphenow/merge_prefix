/* Little application that uses a Trie datatype to
 * merge a list of source words with prefixes:
 * Words with accepting prefixes
 */
import java.util.*;

public class MergePrefixes {
    private ArrayList<String> sources, prefixes, ret;
    // Simple variable to 'cache' our last match and keep checking
    // for similar matches before moving on to a new prefix
    private String still_testing = null;

    public MergePrefixes(){
        sources = new ArrayList<String>();
        prefixes = new ArrayList<String>();
        ret = new ArrayList<String>();

        // Source strings
        sources.add("bash");
        sources.add("cplusplus");
        sources.add("java");
        sources.add("javascript");
        sources.add("php");
        sources.add("python");
        sources.add("ruby");

        // Prefix strings
        prefixes.add("ab");
        prefixes.add("ba");
        prefixes.add("bu");
        prefixes.add("jav");
        prefixes.add("ph");
        prefixes.add("ru");
        prefixes.add("ze");
    }

    // Loop that cycles sources, sets up Trie for each source
    // then checks for matching prefixes. On the first find of
    // prefix we will stop, put the unmatching prefixes in the
    // ret list then continue checking our working prefix until
    // we toss it. Continue this loop until we can print results
    public void run(){
        for(String source : sources) {
            Trie t = new Trie(source);
            if(t.contains(this.still_testing)) {
                ret.add(source);
                continue;
            }
            else {
                for( String pre : prefixes) {
                    if(t.contains(pre)){
                        int i = prefixes.indexOf(pre);
                        still_testing = pre;
                        if(i > 0) {
                            for( int j = 0; j < i; j++){
                                ret.add(prefixes.remove(j));
                            }
                        }
                        prefixes.remove(pre);
                        ret.add(source);
                        break;
                    }
                }
            }
        }
        if(prefixes.size() > 0){
            ret.addAll(prefixes);
        }
        System.out.println(ret);
    }

    public static void main(String[] args){
        MergePrefixes mg = new MergePrefixes();
        mg.run();
    }
}
