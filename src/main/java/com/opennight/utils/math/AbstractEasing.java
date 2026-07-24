package com.opennight.utils.math;

import com.opennight.utils.math.Easing;
import com.opennight.utils.math.Point2d;

public abstract class AbstractEasing
implements Easing {
    public final Point2d p1 = new Point2d(0.0, 0.0);
    public final Point2d p2 = new Point2d(1.0, 1.0);
}