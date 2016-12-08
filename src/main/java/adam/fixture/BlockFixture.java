package adam.fixture;

import adam.dto.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fixture holding definition of blocks from blue set.
 */
public class BlockFixture {
    /*
   0 0 1 0 0
 0     []     0
 0   [][][]   0
 1 [][][][][] 1
 0   [][][]   0
 0     []     0
   0 0 1 0 0
     */
    public static Block getBlock0() {
        return Block.createInstance(0, new String[]{"00100", "00100", "00100", "00100"});
    }

    /*
     1 0 1 0 1
 1  []  []  [] 1
 1  [][][][][] 1
 0    [][][]   0
 1  [][][][][] 1
 1  []  []  [] 1
    1 0 1 0 1
     */

    public static Block getBlock1() {
        return Block.createInstance(1, new String[]{"10101", "11011", "10101", "11011"});
    }

    /*
     0 0 1 0 0
   0     []     0
   0   [][][][] 1
   1 [][][][]   0
   0   [][][][] 1
   0     []     0
     0 0 1 0 0
     */

    public static Block getBlock2() {
        return Block.createInstance(2, new String[]{"00100", "01010", "00100", "00100"});
    }


    /*
       <-

       0 1 0 1 0
    0    []  []   0
 |  1  [][][][]   0  /\
\/  0    [][][][] 1   |
    1  [][][][]   0
    1  [][]  []   0
       1 1 0 1 0

        ->
     */

    public static Block getBlock3() {
        return Block.createInstance(3, new String[]{"11010", "00100", "01010", "01011"});
    }

    /*
     0 1 0 1 0
  0   []  []   0
  1 [][][][][] 1
  0   [][][]   0
  1 [][][][][] 1
  1 []  []     0
    1 0 1 0 0

     */

    public static Block getBlock4() {
        return Block.createInstance(4, new String[]{"10100", "01010", "01010", "01011"});
    }

    /**
       0 1 0 1 0
   0    []  []   0
   0    [][][][] 1
   1  [][][][]   0
   0    [][][][] 1
   1  [][]  [][] 1
       1 1 0 1 1

     */

    public static Block getBlock5() {
        return Block.createInstance(5, new String[]{"11011", "11010", "01010", "00101"});
    }

    public static List<Block> getBlocks() {
        return new ArrayList<>(Arrays.asList(getBlock0(), getBlock1(), getBlock2(), getBlock3(), getBlock4(), getBlock5()));
    }
}
