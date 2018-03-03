package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;

	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.dev = dev;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String getTitle() {
		return "Bergsmassiv";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(a.getX(), a.getY());
		fractalTriangle(turtle, order, a, b, c, dev);
	}

	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {

		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());

		} else {
			double randDev = RandomUtilities.randFunc(dev);
			turtle.moveTo(a.getX(), a.getY());

			Point ab2 = new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2 + randDev);
			Point bc2 = new Point((b.getX() + c.getX()) / 2, (b.getY() + c.getY()) / 2 + randDev);
			Point ca2 = new Point((a.getX() + c.getX()) / 2, (a.getY() + c.getY()) / 2 + randDev);
			fractalTriangle(turtle, order - 1, a, ab2, ca2, dev / 2);
			fractalTriangle(turtle, order - 1, ab2, b, bc2, dev / 2);
			fractalTriangle(turtle, order - 1, ca2, bc2, c, dev / 2);
			fractalTriangle(turtle, order - 1, ca2, ab2, bc2, dev / 2);
		}
	}
}