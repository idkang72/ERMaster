package ermaster.editor.view.action.option.notation.level;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationLevelToDetailAction extends
		AbstractChangeNotationLevelAction {

	public static final String ID = ChangeNotationLevelToDetailAction.class
			.getName();

	public ChangeNotationLevelToDetailAction(ERDiagramEditor editor) {
		super(ID, editor);
	}

	@Override
	protected int getLevel() {
		return Settings.NOTATION_LEVLE_DETAIL;
	}

}
