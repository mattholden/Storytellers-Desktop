package org.slage.framework;

/**
 * Vector3D represents a 3D vector for game math. It is heavily used in motion
 * and collision. It can also be used for 2D vectors by simply leaving the Z
 * component set to 0.
 */
public class Vector3D implements java.io.Serializable
{

    /** X component */
    public float x = 0.0f;

    /** Y component */
    public float y = 0.0f;

    /** Z component */
    public float z = 0.0f;

    /** Creates a new instance of Vector3D with values (0,0,0) */
    public Vector3D() {
        x = y = z = 0;
    }

    /**
     * Construct a "2D" Vector3D. The Z component will be set to 0.
     * 
     * @param xPos X component
     * @param yPos Y component
     */
    public Vector3D(float xPos, float yPos) {
        this.x = xPos;
        this.y = yPos;
        this.z = 0;
    }

    /**
     * Construct a Vector3D.
     * 
     * @param xPos X component
     * @param yPos Y component
     * @param zPos Z component
     */
    public Vector3D(float xPos, float yPos, float zPos) {
        this.x = xPos;
        this.y = yPos;
        this.z = zPos;
    }

    /**
     * Get a string representation of the Vector3D in the format [x, y, z]
     * 
     * @return string representation
     */
    public String toString() {
        return '[' + Tools.TrimFloat(x) + ", " + Tools.TrimFloat(y) + ", "
                + Tools.TrimFloat(z) + ']';
    }

    /**
     * Get the magnitude (length) of the vector.
     * 
     * @return the magnitude of the vector
     */
    public float getMagnitude() {
        return (float) Math.sqrt((x * x) + (y * y) + (z * z));
    }

    /**
     * Get the magnitude of the vector, squared. This is useful for distance
     * comparisons, as it is much more efficient when the square root portion of
     * the Magnitude function is not required.
     * 
     * @return the magnitude of the vector, squared
     */
    public float getMagnitudeSq() {
        return ((x * x) + (y * y) + (z * z));
    }

    /**
     * Scale the Vector by a scalar value.
     * 
     * @param fScalar
     *            Value to scale
     * @return the vector, scaled by this amount
     */
    public Vector3D scaleBy(float fScalar) {
        return new Vector3D(x * fScalar, y * fScalar, z * fScalar);
    }

    /**
     * Scale the Vector to a given magnitude.
     * 
     * @param fScalar
     *            Value to scale
     * @return the vector, scaled to this magnitude
     */
    public Vector3D setScale(float fScalar) {
        return getNormal().scaleBy(fScalar);
    }

    /**
     * Normalize the vector.
     * 
     * @return a normalized version of the vector
     */
    public Vector3D getNormal() {
        float fMag = getMagnitude();
        return new Vector3D(x / fMag, y / fMag, z / fMag);
    }

    // Todo: Add, Subtract, Projections

}
