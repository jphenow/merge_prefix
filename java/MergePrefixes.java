import java.util.*;
import java.io.*;

/* Little application that uses a Trie datatype to
 * merge a list of source words with prefixes:
 * Words with accepting prefixes
 */
public class MergePrefixes {
    private ArrayList<String> sources, prefixes, ret;

    // Simple variable to 'cache' our last match and keep checking
    // for similar matches before moving on to a new prefix
    private String still_testing = null;

    /**
     * Initalizer, sets up necessary lists
     * */
    public MergePrefixes(){
        sources = new ArrayList<String>();
        prefixes = new ArrayList<String>();
        ret = new ArrayList<String>();
    }

    /** 
     * Constructor that simplifies setup for grabbing list files
     * @param s String that represents path to sources.list
     * @param p String that represents path to prefixes.list
     * */
    public MergePrefixes(String s, String p){
        sources = new ArrayList<String>();
        prefixes = new ArrayList<String>();
        ret = new ArrayList<String>();

        listFile(s, sources);
        listFile(p, prefixes);
    }

    /**
     * Loop that cycles sources, sets up Trie for each source
     * then checks for matching prefixes. On the first find of
     * prefix we will stop, put the unmatching prefixes in the
     * ret list then continue checking our working prefix until
     * we toss it. Continue this loop until we can print results
     */
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

    /** 
     * Grab contents, by line, of some files
     * @param filepath String represents path to file we're loading into array
     * @param list List object we're placing our results in
     * */
    private void listFile(String filepath, ArrayList list){
        try{
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new
            FileInputStream(filepath);

            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "ASCII"));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                // Print the content on the console
                list.add(strLine);
            }
            //Close the input stream
            in.close();
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    /** 
     * Main, so we run the basic setup with the files in lists/
     * and output our results. args do nothing here.
     * */
    public static void main(String[] args){
        MergePrefixes mg = new MergePrefixes("../lists/sources.list", "../lists/prefixes.list");
        mg.run();
    }
}
