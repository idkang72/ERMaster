package ermaster.editor.view.figure.view;

import ermaster.ImageKey;
import ermaster.editor.view.figure.table.TableFigure;

public class ViewFigure extends TableFigure {

	public ViewFigure(String tableStyle) {
		super(tableStyle);
	}

	@Override
	public String getImageKey() {
		return ImageKey.VIEW;
	}

}
