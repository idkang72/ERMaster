package ermaster.editor.view.action.option.notation.level;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationLevelToOnlyTitleAction extends
		AbstractChangeNotationLevelAction {

	public static final String ID = ChangeNotationLevelToOnlyTitleAction.class
			.getName();

	public ChangeNotationLevelToOnlyTitleAction(ERDiagramEditor editor) {
		super(ID, editor);
	}

	@Override
	protected int getLevel() {
		return Settings.NOTATION_LEVLE_TITLE;
	}

}
