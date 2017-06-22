package adam.helpers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.util.stream.Collectors.toList;

/**
 * Utilities class used for supplying creation and removal of indexes used for testing
 * placement of blocks in a cube.
 */
public final class SeqUtil {
    SeqUtil() {
        //Do nothing.
    }

    /**
     * Creates a full set of sequences (permutations) to be tested during insertion of block to the cube.
     * @param blockIds initial blockIds.
     * @return a queue of strings will all possible permutations of blockIds.
     */
    public static Queue<String> createNextSequence(String blockIds) {
        Queue<String> queue = new LinkedList<>();
        permute(queue, blockIds, 0);
        return queue;
    }

    /**
     * Fins all possible permutations.
     * @param nextSequence queue holding sequences of blocks to be tested
     * @param blockIds base ids of blocks to be used for initiating the permutation.
     * @param startIndex the start Index used for swapping characters in the block ids.
     */
    private static void permute(Queue<String> nextSequence, String blockIds, int startIndex) {
        if (startIndex == blockIds.length()) {
            return;
        }
        for (int i = 0; i < blockIds.length(); i++) {
            char[] newIndices = blockIds.toCharArray();
            char tmp = newIndices[i];
            newIndices[i] = newIndices[startIndex];
            newIndices[startIndex] = tmp;
            String newIndex = String.valueOf(newIndices);
            if (!nextSequence.contains(newIndex)) {
                nextSequence.add(newIndex);
            }
            permute(nextSequence, newIndex, startIndex + 1);
        }
    }

    /**
     * Removes any instances from nextSequence having the same block inserted as first and last.
     * @param nextSequence the queue with indices.
     * @param blockIds the id representing successful result.
     */
    public static void removeSameRepresentations(Queue<String> nextSequence, String blockIds) {
        char start = blockIds.charAt(0);
        int lastIndex = blockIds.length() - 1;
        char end = blockIds.charAt(lastIndex);

        List<String> forRemoval = nextSequence.stream().filter(s -> s.charAt(0) == start && s.charAt(lastIndex) == end || s.charAt(0) == end && s.charAt(lastIndex) == start).collect(toList());
        forRemoval.forEach(nextSequence::remove);
    }
}
