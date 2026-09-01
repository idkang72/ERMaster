package ermaster.preference.page.template;

import org.eclipse.swt.widgets.Composite;
import ermaster.preference.PreferenceInitializer;
import ermaster.preference.editor.FileListEditor;

public class TemplateFileListEditor extends FileListEditor {

	public TemplateFileListEditor(String name, String labelText,
			Composite parent) {
		super(name, labelText, parent, "*.xlsx");
	}

	@Override
	protected String getStorePath(String name) {
		return PreferenceInitializer.getTemplatePath(name);
	}

}
