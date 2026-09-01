package ermaster.preference.page.translation;

import org.eclipse.swt.widgets.Composite;
import ermaster.preference.PreferenceInitializer;
import ermaster.preference.editor.FileListEditor;

public class TranslationFileListEditor extends FileListEditor {

	public TranslationFileListEditor(String name, String labelText,
			Composite parent) {
		super(name, labelText, parent, "*.txt");
	}

	@Override
	protected String getStorePath(String name) {
		return PreferenceInitializer.getTranslationPath(name);
	}

}
