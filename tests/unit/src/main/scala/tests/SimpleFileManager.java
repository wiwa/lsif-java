package tests;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.sourcegraph.semanticdb_javac.SemanticdbPlugin;

public class SimpleFileManager
        extends ForwardingJavaFileManager<StandardJavaFileManager> {

    public List<SimpleClassFile> compiled = new ArrayList<>();
    protected SimpleFileManager(StandardJavaFileManager fileManager) {
        super(fileManager);
    }

    // standard constructors/getters

    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
                                               String className, JavaFileObject.Kind kind, FileObject sibling) {
        SimpleClassFile result = new SimpleClassFile(
                URI.create("string://" + className));
        if (!className.equals(SemanticdbPlugin.stubClassName)) {
            compiled.add(result);
        }
        return result;
    }

}

