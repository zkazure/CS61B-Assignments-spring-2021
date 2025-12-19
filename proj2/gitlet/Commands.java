package gitlet;

import java.io.File;
import java.io.IOException;

/** All commands for Gitlet in terminal
 */

public class Commands {
    public void init(String[] args) {
        Methods.judgeOperands(args, 0);
        if (Repository.GITLET_DIR.exists()) {
            Methods.exitWith("A Gitlet version-control system already exists in the current directory.");
        }
        Repository.initializeRepo();
    }
}
