package gitlet;

/** Personal useful method for Gitlet
 */

public class Methods {
    public static void exitWith(String message) {
        System.out.println(message);
        System.exit(0);
    }
    public static void judgeOperands(String[] args, int cnt) {
        if (args.length - 1 != cnt) {
            exitWith("Incorrect Operands.");
        }
    }
}
