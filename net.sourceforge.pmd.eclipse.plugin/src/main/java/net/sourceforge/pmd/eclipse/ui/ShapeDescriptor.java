/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.eclipse.ui;

import org.eclipse.swt.graphics.RGB;

/**
 * 
 * @author Brian Remedios
 */
public class ShapeDescriptor implements Cloneable {

    public Shape shape;
    public RGB rgbColor;
    public int size;

    public ShapeDescriptor(Shape theShape, RGB theColor, int theSize) {
        shape = theShape;
        rgbColor = theColor;
        size = theSize;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (other.getClass() != getClass()) {
            return false;
        }

        ShapeDescriptor otherOne = (ShapeDescriptor) other;

        return shape.equals(otherOne.shape) && rgbColor.equals(otherOne.rgbColor) && size == otherOne.size;
    }

    public int hashCode() {
        return rgbColor.hashCode() ^ shape.hashCode() ^ size;
    }

    public ShapeDescriptor clone() {
        ShapeDescriptor copy = new ShapeDescriptor(shape, new RGB(rgbColor.red, rgbColor.green, rgbColor.blue), size);
        return copy;
    }

    public String toString() {

        return shape.name() + ", " + rgbColor + ", " + size;
    }
}
