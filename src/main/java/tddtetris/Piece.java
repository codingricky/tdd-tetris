package tddtetris;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Piece {
    private List<Point> points;
    private Point centrePoint;
    private int maxX;
    private int maxY;
    private char block;

    public Piece(List<Point> points, int maxX, int maxY, char block) {
        this.points = points;
        this.maxX = maxX;
        this.maxY = maxY;
        centrePoint = new Point(maxX / 2, maxY / 2);
        this.block = block;
    }
    public Piece(List<Point> points, Point centrePoint, int maxX, int maxY, char block) {
        this.points = points;
        this.maxX = maxX;
        this.maxY = maxY;
        this.centrePoint = centrePoint;
        this.block = block;
    }
    
    public Piece(String s) {
        points = new ArrayList<Point>();
        StringTokenizer stringTokenizer = new StringTokenizer(s, "\n", false);
        int y = 0;
        maxY = stringTokenizer.countTokens();
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            // they should all be the same length, so we can keep on setting it
            maxX = token.length();
            for (int x = 0; x < token.length(); x++) {
                if (token.charAt(x) != '.') {
                    this.block = token.charAt(x);
                    points.add(new Point(x, y));
                }
            }
            y++;
        }
        centrePoint = new Point(maxX / 2, maxY / 2);
    }

    public Piece rotateRight() {
        List<Point> newPoints = new ArrayList<Point>();
        for (Point point : points) {
            if (point.equals(centrePoint)) {
                newPoints.add(centrePoint);
            } else {
                Point pointRelativeToZero = centrePoint.minus(point);
                Point newPoint = new Point(pointRelativeToZero.getY(), -1 * pointRelativeToZero.getX()).add(centrePoint);
                newPoints.add(newPoint);
            }
        }
        return new Piece(newPoints, maxX, maxY, block);
    }
    
    public Point[] getPoints() {
        return points.toArray(new Point[points.size()]);
    }

    public Point getCentrePoint() {
        return centrePoint;
    }

    public Piece rotateLeft() {
        return rotateRight().rotateRight().rotateRight();
    }

    public Piece relativeToCentrePoint(Point newCentrePoint) {
        List<Point> pointsRelativeToZeroZero = new ArrayList<Point>(points.size());
        for (Point point : points) {
            pointsRelativeToZeroZero.add(point.minus(centrePoint));
        }
        
        List<Point> pointsRelativeToNewCentrePoint = new ArrayList<Point>(points.size());
        for (Point point : pointsRelativeToZeroZero) {
            pointsRelativeToNewCentrePoint.add(newCentrePoint.add(point));
        }
        return new Piece(pointsRelativeToNewCentrePoint, newCentrePoint, maxX, maxY, block);
    }

    public char getBlock() {
        return block;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (points.contains(new Point(x, y))) {
                    sb.append(block);
                } else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
