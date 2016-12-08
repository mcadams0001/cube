package adam.dto;

public class CubeResult {
    private Cube cube;
    private String cubeIndexes;

    public CubeResult(Cube cube,String cubeIndexes) {
        this.cube = cube;
        this.cubeIndexes = cubeIndexes;
    }

    public Cube getCube() {
        return cube;
    }

    public String getCubeIndexes() {
        return cubeIndexes;
    }

    public boolean isFound() {
        return cube != null;
    }
}
