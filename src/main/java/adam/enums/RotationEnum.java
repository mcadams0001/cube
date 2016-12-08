package adam.enums;

/**
 * Represents the rotation of a block.
 */
public enum RotationEnum {
    DEG_0 {
        @Override
        public String[] rotateShapes(String[] shapes) {
            return shapes;
        }
    },
    DEG_90 {
        @Override
        public String[] rotateShapes(String[] shapes) {
            String[] rotatedShapes = new String[shapes.length];
            rotatedShapes[1] = shapes[0];
            rotatedShapes[2] = shapes[1];
            rotatedShapes[3] = shapes[2];
            rotatedShapes[0] = shapes[3];
            return rotatedShapes;
        }
    },
    DEG_180 {
        @Override
        public String[] rotateShapes(String[] shapes) {
            String[] rotatedShapes = new String[shapes.length];
            rotatedShapes[0] = shapes[2];
            rotatedShapes[1] = shapes[3];
            rotatedShapes[2] = shapes[0];
            rotatedShapes[3] = shapes[1];
            return rotatedShapes;
        }
    },
    DEG_270 {
        @Override
        public String[] rotateShapes(String[] shapes) {
            String[] rotatedShapes = new String[shapes.length];
            rotatedShapes[0] = shapes[1];
            rotatedShapes[1] = shapes[2];
            rotatedShapes[2] = shapes[3];
            rotatedShapes[3] = shapes[0];
            return rotatedShapes;
        }
    };

    public abstract String[] rotateShapes(String[] shapes);
}
