package ermaster.editor.view.action.option.notation.type;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeViewToLogicalAction extends AbstractChangeViewAction {

	public static final String ID = ChangeViewToLogicalAction.class.getName();

	public ChangeViewToLogicalAction(ERDiagramEditor editor) {
		super(ID, "logical", editor);
	}

	@Override
	protected int getViewMode() {
		return Settings.VIEW_MODE_LOGICAL;
	}
}
