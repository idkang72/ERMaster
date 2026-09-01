package ermaster.editor.controller.editpart.element.node;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import ermaster.editor.controller.editpolicy.element.node.NodeElementComponentEditPolicy;
import ermaster.editor.controller.editpolicy.element.node.note.NoteDirectEditPolicy;
import ermaster.editor.model.diagram_contents.element.node.note.Note;
import ermaster.editor.view.editmanager.NoteCellEditor;
import ermaster.editor.view.editmanager.NoteEditManager;
import ermaster.editor.view.editmanager.NoteEditorLocator;
import ermaster.editor.view.figure.NoteFigure;

public class NoteEditPart extends NodeElementEditPart implements IResizable {

	private NoteEditManager editManager = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IFigure createFigure() {
		NoteFigure noteFigure = new NoteFigure();

		this.changeFont(noteFigure);

		return noteFigure;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createEditPolicies() {
		this.installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new NodeElementComponentEditPolicy());
		this.installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new NoteDirectEditPolicy());

		super.createEditPolicies();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doRefreshVisuals() {
		Note note = (Note) this.getModel();

		NoteFigure figure = (NoteFigure) this.getFigure();

		figure.setText(note.getText(), note.getColor());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performRequest(Request request) {
		if (request.getType().equals(RequestConstants.REQ_DIRECT_EDIT)
				|| request.getType().equals(RequestConstants.REQ_OPEN)) {
			performDirectEdit();
		}
	}

	private void performDirectEdit() {
		if (this.editManager == null) {
			this.editManager = new NoteEditManager(this, NoteCellEditor.class,
					new NoteEditorLocator(getFigure()));
		}

		this.editManager.show();
	}

	@Override
	protected void performRequestOpen() {
	}
}
