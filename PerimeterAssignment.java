import edu.duke.*;
import java.io.File;

public class PerimeterAssignment {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for (Point pt : s.getPoints())
        {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double averageLength = getPerimeter(s) / getNumPoints(s);
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist >= largestSide){
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        Point prevPt = s.getLastPoint();
        double largestX = prevPt.getX();
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (currX >= largestX){
                largestX = currX;
            }
            prevPt = currPt;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);

            if (length  >= largestPerimeter)
            {
                largestPerimeter = length;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);

            if (length  >= largestPerimeter)
            {
                largestPerimeter = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("datatest1.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + getNumPoints(s));
        System.out.println("average length = " + getAverageLength(s));
        System.out.println("largest side = " + getLargestSide(s));
        System.out.println("largest X = " + getLargestX(s));

    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("largest perimeter from multiple files = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("file name for largest perimeter from multiple files = " + getFileWithLargestPerimeter());
    }

    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
