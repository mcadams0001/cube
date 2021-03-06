package adam;

import adam.exceptions.CubeCreationExceptions;
import adam.fixture.BlockFixture;
import adam.services.CubeServiceImpl;
import adam.services.ExecutionService;
import adam.services.ExecutionServiceImpl;

public class HappyCube {
    private HappyCube() {
        //Do nothing
    }

    public static void main(String[] args) throws CubeCreationExceptions {
        if (args.length == 0) {
            System.out.println("Please provide filename for the result file");
            return;
        }
        ExecutionService executionService = new ExecutionServiceImpl(new CubeServiceImpl());
        executionService.createCubesFile(args[0], BlockFixture.getBlocks());
    }
}
