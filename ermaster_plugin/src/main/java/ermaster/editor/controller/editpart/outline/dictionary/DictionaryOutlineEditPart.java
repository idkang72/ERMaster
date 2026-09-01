package ermaster.editor.controller.editpart.outline.dictionary;

import java.util.Collections;
import java.util.List;

import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.model.diagram_contents.not_element.dictionary.Dictionary;
import ermaster.editor.model.diagram_contents.not_element.dictionary.Word;
import ermaster.editor.model.settings.Settings;

public class DictionaryOutlineEditPart extends AbstractOutlineEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List getModelChildren() {
		Dictionary dictionary = (Dictionary) this.getModel();
		List<Word> list = dictionary.getWordList();

		if (this.getDiagram().getDiagramContents().getSettings()
				.getViewOrderBy() == Settings.VIEW_MODE_LOGICAL) {
			Collections.sort(list, Word.LOGICAL_NAME_COMPARATOR);

		} else {
			Collections.sort(list, Word.PHYSICAL_NAME_COMPARATOR);

		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		this.setWidgetText(ResourceString.getResourceString("label.dictionary")
				+ " (" + this.getModelChildren().size() + ")");
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.DICTIONARY));
	}

}
