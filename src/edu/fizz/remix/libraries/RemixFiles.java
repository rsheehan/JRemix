package edu.fizz.remix.libraries;

import edu.fizz.remix.runtime.Context;
import edu.fizz.remix.runtime.Function;
import edu.fizz.remix.runtime.LibraryExpression;
import edu.fizz.remix.runtime.ReturnException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RemixFiles extends LibraryExpression {

    public static final class HomeDirectory extends Function {

        public HomeDirectory() {
            super(
                    List.of("home"),
                    List.of(),
                    List.of(),
                    false,
                    "Return the user's home directory."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            return System.getProperty("user.home");
        }
    }

    public static final class PathSeparator extends Function {

        public PathSeparator() {
            super (
                    List.of("path separator"),
                    List.of(),
                    List.of(),
                    false,
                    "The file separator on this OS."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            return File.separator;
        }
    }

    public static final class ReadFileContents extends Function {

        public ReadFileContents() {
            super(
                    List.of("read file |"),
                    List.of("Name"),
                    List.of(false),
                    false,
                    "Read the entire contents of file \"Name\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            String fileName = (String)context.retrieve(("Name"));
            String result = "";
            try {
                result = Files.readString(Path.of(fileName));
            } catch (IOException e) {
                System.err.println(e);
                throw new RuntimeException(e);
            }
            return result;
        }
    }

}