package ermaster.editor.view.action.option.notation.level;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationLevelToNameAndKeyAction extends
		AbstractChangeNotationLevelAction {

	public static final String ID = ChangeNotationLevelToNameAndKeyAction.class
			.getName();

	public ChangeNotationLevelToNameAndKeyAction(ERDiagramEditor editor) {
		super(ID, editor);
	}

	@Override
	protected int getLevel() {
		return Settings.NOTATION_LEVLE_NAME_AND_KEY;
	}

}
