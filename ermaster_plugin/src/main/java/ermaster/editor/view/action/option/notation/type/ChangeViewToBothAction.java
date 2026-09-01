package ermaster.editor.view.action.option.notation.type;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeViewToBothAction extends AbstractChangeViewAction {

	public static final String ID = ChangeViewToBothAction.class.getName();

	public ChangeViewToBothAction(ERDiagramEditor editor) {
		super(ID, "both", editor);
	}

	@Override
	protected int getViewMode() {
		return Settings.VIEW_MODE_BOTH;
	}

}
