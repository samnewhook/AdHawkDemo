import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_core.CV_32FC2;
import static org.bytedeco.javacpp.opencv_core.CV_64FC2;

class EyeVector {
    private final double elevationAngle;
    private final double azimuthAngle;

    /**Class for holding a vector containing the elevation angle
     * and azimuth angle of the detected Eye Position.
     * @param elevationAngle Value from 0-1 corresponding to
     *                       min-max angle of rotation (resting
     *                       eye position would be 0.5)
     * @param azimuthAngle Same as elevation but for the azimuthal
     *                     direction.
     */
    private EyeVector(double elevationAngle, double azimuthAngle) {
        this.elevationAngle = elevationAngle;
        this.azimuthAngle = azimuthAngle;
    }

    /**Creates a random vector.
     * @return Random EyeVector
     */
    static EyeVector createRandomVector() {
        return new EyeVector(Math.random(), Math.random());
    }

    /**Returns the matrix corresponding to this vector.
     * @param width Width of Image to be translated to.
     * @param height Height of Image to be translated to.
     * @return Matrix holding the position of the vector on an image.
     */
    opencv_core.Mat getMatFromVector(int width, int height) {
        opencv_core.Mat newMat = new opencv_core.Mat(1, 1, CV_32FC2);
        Indexer idx = newMat.createIndexer();
        idx.putDouble(new long[]{0, 0, 0}, Math.round(this.azimuthAngle * width));
        idx.putDouble(new long[]{0, 0, 1}, Math.round(this.elevationAngle * height));
        return newMat;
    }

}
