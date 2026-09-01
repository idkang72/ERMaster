package ermaster.common.widgets;

import java.io.File;

import org.eclipse.swt.widgets.Composite;
import ermaster.ERDiagramActivator;
import ermaster.util.io.FileUtils;

public class DirectoryText extends AbstractPathText {

	private String message;

	public DirectoryText(Composite parent, final File projectDir,
			final String message) {
		this(parent, projectDir, message, true);
	}

	public DirectoryText(Composite parent, final File projectDir,
			final String message, boolean indent) {
		super(parent, projectDir, indent);

		this.message = message;
	}

	@Override
	protected String selectPathByDilaog() {
		String filePath = FileUtils.getFile(this.projectDir, getFilePath())
				.getAbsolutePath();

		return ERDiagramActivator.showDirectoryDialog(filePath, this.message);
	}

}
