package adam.services;

import adam.dto.*;
import adam.enums.RotationEnum;
import adam.factory.CubeFactory;
import adam.helpers.SeqUtil;

import java.util.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;


public class CubeServiceImpl implements CubeService {

    @Override
    public Cube createCubeFromBlocks(List<Block> blocks) {
        Map<Character, Block> blocksForProcessing = mapBlockToInternalIndex(blocks);
        String blockIndexes = "012345";
        Queue<String> nextSequence = SeqUtil.createNextSequence(blockIndexes);
        CubeResult cubeResult = createCubeStartingFromSequence(blocksForProcessing, nextSequence);
        return cubeResult.getCube();
    }

    @Override
    public List<Cube> createAllCubesFromBlocks(List<Block> blocks) {
        List<Cube> cubes = new ArrayList<>();
        Map<Character, Block> blocksForProcessing = mapBlockToInternalIndex(blocks);
        String nextBlockIndex = blocksForProcessing.keySet().stream().map(String::valueOf).collect(joining());
        Queue<String> nextSequence = SeqUtil.createNextSequence(nextBlockIndex);
        while (true) {
            CubeResult cubeResult = createCubeStartingFromSequence(blocksForProcessing, nextSequence);
            if (!cubeResult.isFound()) {
                break;
            }
            cubes.add(cubeResult.getCube());
            SeqUtil.removeSameRepresentations(nextSequence, cubeResult.getCubeIndexes());
        }
        return cubes;
    }

    /**
     * Creates a cube from blocks starting from given sequence represented by sequence of strings in a queue.
     * @param blocksForProcessing a map of blocks where characters represent id's and values are blocks.
     * @param nextSequence the queue sequence of 6 characters representing id's of 6 blocks to be inserted in the queue in the given order.
     *                     The sequence of "012345" means first block 0 is inserted then 1 etc.
     * @return the result of insertion for given sequence.
     */
    private CubeResult createCubeStartingFromSequence(Map<Character, Block> blocksForProcessing, Queue<String> nextSequence) {
        Cube cube = CubeFactory.createInstance();
        String index = nextSequence.poll();
        if(index == null) {
            return new CubeResult(null, "");
        }
        while (!insertBlocks(cube, blocksForProcessing, index)) {
            index = nextSequence.poll();
            if(index == null) {
                return new CubeResult(null, "");
            }
            cube.clear();
        }
        return new CubeResult(cube, index);
    }

    /**
     * Insert a block into the cube.
     * @param cube the cube.
     * @param blocks map holding all blocks with their id's represented as keys.
     * @param sequence the sequence in which blocks are inserted into the cube.
     * @return true if inserted successfully otherwise false.
     */
    private boolean insertBlocks(Cube cube, Map<Character, Block> blocks, String sequence) {
        int cubeBlockIndex = 0;
        for (int i = 0; i < sequence.length(); ++i) {
            Block block = blocks.get(sequence.charAt(i));
            Block selectedBlock = insertBlock(cube.getBlock(cubeBlockIndex), block);
            if (selectedBlock == null) {
                return false;
            }
            cubeBlockIndex++;
        }
        return true;
    }

    /**
     * Inserts a single block into the cube space (cubeBlock).
     * @param cubeBlock the block in the cube to which a given block is inserted into.
     * @param block the block to be inserted into the cube.
     * @return the instance of the block which was successfully inserted after transformation which might be mirroring and rotation.
     */
    private Block insertBlock(CubeBlock cubeBlock, Block block) {
        Block selectedBlock;
        if (block.isOneAxisSymmetric()) {
            selectedBlock = insertOneAxisSymmetricBlock(cubeBlock, block);
        } else {
            selectedBlock = insertNonSymmetricBlock(cubeBlock, block);
        }
        if (selectedBlock != null) {
            cubeBlock.insertBlock(selectedBlock);
        }
        return selectedBlock;
    }

    /**
     * Inserts a non symmetric block into the cube by performing all possible transformations including mirroring and rotation.
     * The best fit is selected by finding the transformation after which there is a max number of filled spaces in the block. (1 in the shape of edges and vertices).
     * @param cubeBlock the cube block where the new block is supposed to be inserted.
     * @param block the block to be inserted into the cube.
     * @return the new instance of block being the best fit for the given space.
     */
    private Block insertNonSymmetricBlock(CubeBlock cubeBlock, Block block) {
        InsertionResult placeBlockResult = insertedRotatedBlock(cubeBlock, block);
        InsertionResult placeMirroredBlockResult = insertedRotatedBlock(cubeBlock, block.mirrored());
        return placeBlockResult.getDensity() >= placeMirroredBlockResult.getDensity() ? placeBlockResult.getBlock() : placeMirroredBlockResult.getBlock();
    }

    /**
     * Inserts a rotated block by checking each block how it fits after rotation described by RotationEnum.
     * @param cubeBlock the cube block where the new block is supposed to be inserted.
     * @param block the block to be inserted into the cube.
     * @return the insertion result.
     */
    private InsertionResult insertedRotatedBlock(CubeBlock cubeBlock, Block block) {
        Block selectedBlock = null;
        int highDensity = -1;
        for (RotationEnum rotation : EnumSet.allOf(RotationEnum.class)) {
            Block rotatedBlock = block.rotated(rotation);
            int density = cubeBlock.validatePlacement(rotatedBlock);
            if (density > highDensity) {
                highDensity = density;
                selectedBlock = rotatedBlock;
            }
        }
        return new InsertionResult(selectedBlock, highDensity);
    }


    /**
     * Inserts a block which is symmetric in at least one axis.
     * @param cubeBlock the cube block where the new block is supposed to be inserted.
     * @param block the block to be inserted into the cube.
     * @return block being the best fit either original or rotated 90 degree clockwise.
     */
    private Block insertOneAxisSymmetricBlock(CubeBlock cubeBlock, Block block) {
        int density = cubeBlock.validatePlacement(block);
        Block b90 = block.rotated(RotationEnum.DEG_90);
        int density90 = cubeBlock.validatePlacement(b90);
        if (density == -1 && density90 == -1) {
            return null;
        }
        return (density90 > density) ? b90: block;
    }

    /**
     * Creates a map of blocks and their id's represented as characters.
     * @param blocks the set of blocks to be inserted into the cube.
     * @return map with keys being id's of blocks and blocks as values.
     */
    private Map<Character, Block> mapBlockToInternalIndex(List<Block> blocks) {
        return blocks.stream().collect(toMap(Block::getCharIndex, b -> b));
    }

}
