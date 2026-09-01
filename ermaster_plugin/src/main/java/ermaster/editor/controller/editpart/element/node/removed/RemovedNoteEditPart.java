package ermaster.editor.controller.editpart.element.node.removed;

import org.eclipse.draw2d.IFigure;
import ermaster.editor.controller.editpart.element.node.IResizable;
import ermaster.editor.model.diagram_contents.element.node.note.Note;
import ermaster.editor.model.tracking.RemovedNote;
import ermaster.editor.view.figure.NoteFigure;

public class RemovedNoteEditPart extends RemovedNodeElementEditPart implements
		IResizable {

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
	protected void refreshVisuals() {
		RemovedNote removedNote = (RemovedNote) this.getModel();

		NoteFigure figure = (NoteFigure) this.getFigure();

		Note note = (Note) removedNote.getNodeElement();
		figure.setText(note.getText(), note.getColor());

		super.refreshVisuals();
	}
}
