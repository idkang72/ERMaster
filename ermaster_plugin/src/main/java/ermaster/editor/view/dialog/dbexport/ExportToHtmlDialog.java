package ermaster.editor.view.dialog.dbexport;

import java.io.File;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import ermaster.ResourceString;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.common.widgets.DirectoryText;
import ermaster.common.widgets.MultiLineCheckbox;
import ermaster.editor.model.dbexport.ExportWithProgressManager;
import ermaster.editor.model.dbexport.html.ExportToHtmlManager;
import ermaster.editor.model.settings.ExportSetting;
import ermaster.editor.model.settings.export.ExportHtmlSetting;
import ermaster.util.io.FileUtils;

public class ExportToHtmlDialog extends AbstractExportDialog {

	private DirectoryText outputDirText;

	private MultiLineCheckbox withImageButton;

	private MultiLineCheckbox withCategoryImageButton;

	// private Combo fileEncodingCombo;

	@Override
	protected void initLayout(GridLayout layout) {
		super.initLayout(layout);

		layout.numColumns = 3;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize(Composite parent) {
		this.outputDirText = CompositeFactory
				.createDirectoryText(
						this,
						parent,
						"label.output.dir",
						this.getBaseDir(),
						ResourceString
								.getResourceString("dialog.message.export.html.dir.select"));

		// this.fileEncodingCombo = CompositeFactory
		// .createFileEncodingCombo(this.diagramFile, this, parent,
		// "label.output.file.encoding", 2);

		Composite checkboxArea = this.createCheckboxArea(parent);

		this.withImageButton = CompositeFactory.createMultiLineCheckbox(this,
				checkboxArea, "label.output.image.to.html", false, 1);

		this.withCategoryImageButton = CompositeFactory
				.createMultiLineCheckbox(this, checkboxArea,
						"label.output.image.to.html.category", false, 1);

		this.createOpenAfterSavedButton(checkboxArea, false, 1);
	}

	@Override
	protected String getErrorMessage() {
		// if (this.outputDirText.isBlank()) {
		// return "error.output.dir.is.empty";
		// }

		return null;
	}

	@Override
	protected ExportWithProgressManager getExportWithProgressManager(
			ExportSetting exportSetting) throws InputException {

		ExportHtmlSetting exportHtmlSetting = exportSetting
				.getExportHtmlSetting();

		exportHtmlSetting.setOutputDir(this.outputDirText.getFilePath());
		// exportHtmlSetting.setSrcFileEncoding(this.fileEncodingCombo.getText());
		exportHtmlSetting.setWithImage(this.withImageButton.getSelection());
		exportHtmlSetting.setWithCategoryImage(this.withCategoryImageButton
				.getSelection());
		exportHtmlSetting.setOpenAfterSaved(this.openAfterSavedButton
				.getSelection());

		return new ExportToHtmlManager(exportHtmlSetting);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setData() {
		ExportHtmlSetting exportHtmlSetting = this.settings.getExportSetting()
				.getExportHtmlSetting();

		this.outputDirText.setText(FileUtils.getRelativeFilePath(
				this.getBaseDir(), exportHtmlSetting.getOutputDir()));

		// if (!Check.isEmpty(exportHtmlSetting.getSrcFileEncoding())) {
		// this.fileEncodingCombo.setText(exportHtmlSetting
		// .getSrcFileEncoding());
		// }

		this.withImageButton.setSelection(exportHtmlSetting.isWithImage());
		this.withCategoryImageButton.setSelection(exportHtmlSetting
				.isWithCategoryImage());
		this.openAfterSavedButton.setSelection(exportHtmlSetting
				.isOpenAfterSaved());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTitle() {
		return "dialog.title.export.html";
	}

	@Override
	protected File openAfterSaved() {
		return ExportToHtmlManager.getIndexHtml(FileUtils.getFile(
				this.getBaseDir(), this.settings.getExportSetting()
						.getExportHtmlSetting().getOutputDir()));
	}

	@Override
	protected boolean openWithExternalEditor() {
		return true;
	}

}
