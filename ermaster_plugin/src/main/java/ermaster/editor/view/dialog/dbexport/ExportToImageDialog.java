package ermaster.editor.view.dialog.dbexport;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.common.widgets.FileText;
import ermaster.common.widgets.MultiLineCheckbox;
import ermaster.editor.model.dbexport.ExportWithProgressManager;
import ermaster.editor.model.dbexport.image.ExportToImageManager;
import ermaster.editor.model.settings.ExportSetting;
import ermaster.editor.model.settings.export.ExportImageSetting;
import ermaster.util.Check;
import ermaster.util.Format;
import ermaster.util.io.FileUtils;

public class ExportToImageDialog extends AbstractExportDialog {

	private static final String DEFAULT_EXTENTION = ".png";

	private FileText outputFileText;

	// private DirectoryText categoryDirText;

	private MultiLineCheckbox withCategoryImageButton;

	private Label categoryLabel;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize(Composite parent) {
		this.outputFileText = CompositeFactory.createFileText(true, this,
				parent, "label.output.file", this.getBaseDir(),
				this.getDefaultOutputFileName(DEFAULT_EXTENTION), new String[] {
						"*.png", "*.jpeg", "*.bmp" });

		CompositeFactory.createLabel(parent, "label.category");
		this.categoryLabel = CompositeFactory.createLabelAsValue(parent, "", 2);

		Composite checkboxArea = this.createCheckboxArea(parent);

		this.withCategoryImageButton = CompositeFactory
				.createMultiLineCheckbox(this, checkboxArea,
						"label.output.category.image", false, 3);

		// CompositeFactory.createLabel(parent,
		// "label.output.category.image.dir");
		// this.categoryDirText = new DirectoryText(parent, SWT.BORDER, null);
		// this.categoryDirText.setLayoutData(gridData);

		this.createOpenAfterSavedButton(checkboxArea, false, 3);
	}

	@Override
	protected String getErrorMessage() {
		// this.categoryDirText.setEnabled(this.withCategoryImageButton
		// .getSelection());

		if (this.outputFileText.isBlank()) {
			return "error.output.file.is.empty";
		}

		return null;
	}

	@Override
	protected ExportWithProgressManager getExportWithProgressManager(
			ExportSetting exportSetting) throws InputException {
		ExportImageSetting exportImageSetting = exportSetting
				.getExportImageSetting();

		exportImageSetting.setOutputFilePath(this.outputFileText.getFilePath());
		exportImageSetting.setWithCategoryImage(this.withCategoryImageButton
				.getSelection());
		exportImageSetting.setOpenAfterSaved(this.openAfterSavedButton
				.getSelection());
		exportImageSetting.setCategory(this.diagram.getCurrentCategory());

		return new ExportToImageManager(exportImageSetting);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IOException
	 */
	@Override
	protected void setData() {
		ExportImageSetting exportImageSetting = settings.getExportSetting()
				.getExportImageSetting();

		String outputFile = Format.null2blank(exportImageSetting
				.getOutputFilePath());

		if (Check.isEmpty(outputFile)) {
			outputFile = this.getDefaultOutputFilePath(DEFAULT_EXTENTION);
		}

		this.outputFileText.setText(FileUtils.getRelativeFilePath(
				this.getBaseDir(), outputFile));

		this.withCategoryImageButton.setSelection(exportImageSetting
				.isWithCategoryImage());
		this.openAfterSavedButton.setSelection(exportImageSetting
				.isOpenAfterSaved());

		this.setCategoryData(this.categoryLabel);
		if (this.diagram.getCurrentCategory() != null) {
			this.withCategoryImageButton.setEnabled(false);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTitle() {
		return "dialog.title.export.image";
	}

	@Override
	protected File openAfterSaved() {
		return FileUtils
				.getFile(this.getBaseDir(), this.settings.getExportSetting()
						.getExportImageSetting().getOutputFilePath());
	}

	@Override
	protected boolean openWithExternalEditor() {
		return true;
	}

}
