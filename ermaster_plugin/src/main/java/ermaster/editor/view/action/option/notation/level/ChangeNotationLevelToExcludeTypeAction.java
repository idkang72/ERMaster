package ermaster.editor.view.action.option.notation.level;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationLevelToExcludeTypeAction extends
		AbstractChangeNotationLevelAction {

	public static final String ID = ChangeNotationLevelToExcludeTypeAction.class
			.getName();

	public ChangeNotationLevelToExcludeTypeAction(ERDiagramEditor editor) {
		super(ID, editor);
	}

	@Override
	protected int getLevel() {
		return Settings.NOTATION_LEVLE_EXCLUDE_TYPE;
	}

}
