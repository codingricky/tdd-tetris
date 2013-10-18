package tddtetris;

import java.util.ArrayList;
import java.util.List;

public class Block implements Blockable {
    private final char type;

    private Point centrePoint;
    private Point[] points;

    public Block(char type) {
        this(type, new Point(0, 0), new Point[]{new Point(0, 0)});
    }

    public Block(char type, Point centrePoint, Point[] points) {
        this.type = type;
        this.centrePoint = centrePoint;
        this.points = points;
    }

    public char getType() {
        return type;
    }

    public int getDepth() {
        return points.length - 1;
    }

    public Block dropBlockDownOnePosition() {
        Point newCentrePoint = centrePoint.drop();
        Point[] newPoints = new Point[points.length];
        
        for (int i = 0; i < points.length; i++) {
            newPoints[i] = points[i].drop();
        }

        return new Block(type, newCentrePoint, newPoints);
    }

    public Block move(Direction direction) {
        Point newCentrePoint = centrePoint.drop();
        Point[] newPoints = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            newPoints[i] = points[i].move(direction);
        }

        return new Block(type, newCentrePoint, newPoints);
    }

    public Point getCentrePoint() {
        return centrePoint;
    }

    public Point[] getPoints() {
        return points;
    }

    public Block newBlockableRelativeToNewCentrePoint(Point centrePoint) {
        List<Point> pointsAsList = new ArrayList<Point>();

        for (Point point : points) {
            if (point.equals(centrePoint)) {
                pointsAsList.add(centrePoint);
            } else {
                pointsAsList.add(point.add(centrePoint));
            }
        }
        Point[] points = pointsAsList.toArray(new Point[pointsAsList.size()]);
        return new Block(type, centrePoint, points);
    }

    public char getBlock() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }
}
