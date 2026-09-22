package ermaster.editor.view.figure.connection.decoration;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import ermaster.Resources;
import ermaster.editor.view.figure.connection.ERDiagramConnection;

public class ERDecoration extends PolygonDecoration {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintFigure(Graphics graphics) {
		graphics.setAntialias(SWT.ON);

		Color color = this.getLocalBackgroundColor();
		if (color != null && ! color.isDisposed()) {
			graphics.setForegroundColor(color);
			graphics.setBackgroundColor(color);
		}

		super.paintFigure(graphics);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getLocalBackgroundColor() {
		IFigure parent = this.getParent();
		if (parent instanceof ERDiagramConnection) {
			Color color = ((ERDiagramConnection) parent).getColor();
			if (color != null && ! color.isDisposed()) {


				return color;
			}
		}

		if (Resources.isDarkMode()) {


			return Resources.getColor(Resources.DEFAULT_DARK_CONNECTION_COLOR);
		}


		return ColorConstants.black;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getForegroundColor() {


		return this.getLocalBackgroundColor();
	}
}
