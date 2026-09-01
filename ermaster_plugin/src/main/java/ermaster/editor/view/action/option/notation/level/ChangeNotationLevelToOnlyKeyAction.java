package ermaster.editor.view.action.option.notation.level;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationLevelToOnlyKeyAction extends
		AbstractChangeNotationLevelAction {

	public static final String ID = ChangeNotationLevelToOnlyKeyAction.class
			.getName();

	public ChangeNotationLevelToOnlyKeyAction(ERDiagramEditor editor) {
		super(ID, editor);
	}

	@Override
	protected int getLevel() {
		return Settings.NOTATION_LEVLE_KEY;
	}

}
