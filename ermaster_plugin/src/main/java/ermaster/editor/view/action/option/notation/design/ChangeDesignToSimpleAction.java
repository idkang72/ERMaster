package ermaster.editor.view.action.option.notation.design;

import ermaster.editor.ERDiagramEditor;

public class ChangeDesignToSimpleAction extends AbstractChangeDesignAction {

	public static final String ID = ChangeDesignToSimpleAction.class.getName();

	public static final String TYPE = "simple";

	public ChangeDesignToSimpleAction(ERDiagramEditor editor) {
		super(ID, TYPE, editor);
	}

}
