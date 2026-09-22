package ermaster.editor.view.figure.anchor;


import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class XYChopboxAnchor extends ChopboxAnchor {

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
		if (this.location != null) {
			Point point = new Point(this.location);
			this.getOwner().translateToAbsolute(point);


			return point;
		}

		if (reference != null) {
			Rectangle r = new Rectangle(this.getBox());
			r.translate(-1, -1);
			r.resize(1, 1);
			this.getOwner().translateToAbsolute(r);

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
