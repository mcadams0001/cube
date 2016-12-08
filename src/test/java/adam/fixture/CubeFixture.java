package adam.fixture;

import adam.dto.Cube;
import adam.enums.RotationEnum;
import adam.factory.CubeFactory;

public class CubeFixture {
    public static Cube createInstance() {
        Cube cube = CubeFactory.createInstance();
        cube.getBlock(0).insertBlock(BlockFixture.getBlock5());
        cube.getBlock(1).insertBlock(BlockFixture.getBlock1());
        cube.getBlock(2).insertBlock(BlockFixture.getBlock0());
        cube.getBlock(3).insertBlock(BlockFixture.getBlock3().mirrored().rotated(RotationEnum.DEG_90));
        cube.getBlock(4).insertBlock(BlockFixture.getBlock2().rotated(RotationEnum.DEG_270));
        cube.getBlock(5).insertBlock(BlockFixture.getBlock4().rotated(RotationEnum.DEG_180));
        return cube;
    }
}
