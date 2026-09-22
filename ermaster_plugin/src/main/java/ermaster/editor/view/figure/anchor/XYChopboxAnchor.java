package ermaster.editor.view.figure.anchor;


import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class XYChopboxAnchor extends ChopboxAnchor {

	private static final int EDGE_TOLERANCE = 2;

	private Point location;

	public XYChopboxAnchor(IFigure owner) {
		super(owner);
	}


	public void setLocation(Point location) {
		this.location = location;
		this.fireAnchorMoved();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Point getLocation(Point reference) {
		Rectangle r = new Rectangle(this.getBox());
		r.translate(-1, -1);
		r.resize(1, 1);
		this.getOwner().translateToAbsolute(r);

		if (this.location != null) {
			Point point = new Point(this.location);
			this.getOwner().translateToAbsolute(point);

			if (reference != null) {
				boolean isLeftEdge = Math.abs(point.x - r.x) <= EDGE_TOLERANCE;
				boolean isRightEdge = Math.abs(point.x - r.right()) <= EDGE_TOLERANCE;

				if ((isLeftEdge || isRightEdge) && reference.y >= r.y && reference.y <= r.bottom()) {
					int x = isLeftEdge ? r.x : r.right();


					return new Point(x, reference.y);
				}

				boolean isTopEdge = Math.abs(point.y - r.y) <= EDGE_TOLERANCE;
				boolean isBottomEdge = Math.abs(point.y - r.bottom()) <= EDGE_TOLERANCE;

				if ((isTopEdge || isBottomEdge) && reference.x >= r.x && reference.x <= r.right()) {
					int y = isTopEdge ? r.y : r.bottom();


					return new Point(reference.x, y);
				}
			}


			return point;
		}

		if (reference != null) {
			if (! r.isEmpty() && ! r.contains(reference)) {
				if (reference.y >= r.y && reference.y <= r.bottom()) {
					if (reference.x < r.x) {


						return new Point(r.x, reference.y);
					}

					if (reference.x > r.right()) {


						return new Point(r.right(), reference.y);
					}
				}

				if (reference.x >= r.x && reference.x <= r.right()) {
					if (reference.y < r.y) {


						return new Point(reference.x, r.y);
					}

					if (reference.y > r.bottom()) {


						return new Point(reference.x, r.bottom());
					}
				}
			}
		}


		return super.getLocation(reference);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Point getReferencePoint() {
		if (this.location != null) {
			Point point = new Point(this.location);
			this.getOwner().translateToAbsolute(point);


			return point;
		}


		return super.getReferencePoint();
	}

}
