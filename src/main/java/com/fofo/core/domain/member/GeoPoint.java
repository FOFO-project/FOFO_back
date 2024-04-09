package com.fofo.core.domain.member;

import org.springframework.data.geo.Point;

public class GeoPoint extends Point {

    public GeoPoint(final double x, final double y) {
        super(x, y);
    }

    public GeoPoint(final Point point) {
        super(point);
    }
}
