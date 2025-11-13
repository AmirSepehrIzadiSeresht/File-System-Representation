import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSysRep {
    static final int indent = 4;
    static int dirCount = 0;

    /**
     * O(n) algorithm which uses dynamic array 
        with geometric size exapnding logic
     *  */ 
    public static void spaces(int n) {
        for(int i = 0; i <= n; ++i) {
            if(i % indent == 0) {
                System.out.print('|');
            }
            System.out.print(" ");
        }
        System.out.print("|___ ");
    }

    /**
     * experimental runs showed an asymptotic behaviour of O(n);
     */
    public static void diveDirectory(File root, List<Integer> path)
        throws NullPointerException {
        int depth = path.size();
        int parentDepth = depth - 1;
        boolean isDirec = false;

        if(parentDepth >= 0) {
            spaces((indent * parentDepth) - 1);
        }
        
        // for(int i = 0; i < depth; ++i){
        //     System.out.print(path.get(i) + ((i == parentDepth) ? " " : "."));
        // }
        
        System.out.println(root.getName());
        isDirec = root.isDirectory();

        try {
            if(isDirec) {
                path.add(1);
        
                for(String childName : root.list()) {
                File childNode = new File(root, childName);
                diveDirectory(childNode, path);
                path.set(depth, 1 + path.get(depth));
                }
        
                path.remove(depth);
            }
    
        } catch(NullPointerException e){
            // do nothing
        } finally {
            
            dirCount++;
        }
    }

    public static void main(){
        
        File root = new File("F:\\C# intermediate tutorial");
        
        List<Integer> path = new ArrayList<>();
        System.out.println("");

        // long startTime = System.currentTimeMillis();
        diveDirectory(root, path);
        // long endTime = System.currentTimeMillis();

        // long elapsed = endTime - startTime;

        // System.out.println("elapsed time: " + elapsed);
        // System.out.println("directory count: " + dirCount);
    }
}