package adam.helpers;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Helper utility for print blocks in given output.
 */
public final class PrintUtil {
    PrintUtil() {
        //Hide default constructor
    }

    /**
     * Prints a block being represented as list of shapes.
     *
     * @param list   the list of strings representing shapes from edges of a block.
     * @param writer the writer to which the information is to be written to.
     */
    public static void printList(List<String> list, Writer writer) throws IOException {
        for (String line : list) {
            writer.write("      ");
            writer.write(line + "\n");
        }
    }

    /**
     * @param writer the writer to which the information is to be written to.
     * @param lists  the variable argument being an array of multiple lists with shapes from edges of a blocks.
     * @throws IOException if write fails.
     */
    @SafeVarargs
    public static void printLists(Writer writer, List<String>... lists) throws IOException {
        for (int i = 0; i < lists[0].size(); i++) {
            writer.write(lists[0].get(i) + " " + lists[1].get(i) + " " + lists[2].get(i));
            writer.write("\n");
        }
    }
}
