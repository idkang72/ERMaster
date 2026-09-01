package ermaster.editor.model.dbexport.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ermaster.ResourceString;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.ObjectModel;
import ermaster.editor.model.StringObjectModel;
import ermaster.editor.model.dbexport.AbstractExportManager;
import ermaster.editor.model.dbexport.excel.sheet_generator.AbstractSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.AllIndicesSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.AllSequencesSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.AllTablesSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.AllTriggerSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.AllViewSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.CategorySheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.ColumnSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.HistorySheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.IndexSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.PictureSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.SequenceSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.SheetIndexSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.TableSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.TriggerSheetGenerator;
import ermaster.editor.model.dbexport.excel.sheet_generator.ViewSheetGenerator;
import ermaster.editor.model.dbexport.image.ExportToImageManager;
import ermaster.editor.model.dbexport.image.ImageInfo;
import ermaster.editor.model.dbexport.image.ImageInfoSet;
import ermaster.editor.model.progress_monitor.ProgressMonitor;
import ermaster.editor.model.settings.export.ExportExcelSetting;
import ermaster.preference.PreferenceInitializer;
import ermaster.preference.page.template.TemplatePreferencePage;
import ermaster.util.Check;
import ermaster.util.POIUtils;
import ermaster.util.io.FileUtils;

public class ExportToExcelManager extends AbstractExportManager {

	private static final String WORDS_SHEET_NAME = "words";

	private static final String LOOPS_SHEET_NAME = "loops";

	private Map<String, Integer> sheetNameMap;

	private Map<String, ObjectModel> sheetObjectMap;

	private static final List<AbstractSheetGenerator> SHHET_GENERATOR_LIST = new ArrayList<AbstractSheetGenerator>();

	static {
		SHHET_GENERATOR_LIST.add(new TableSheetGenerator());
		SHHET_GENERATOR_LIST.add(new IndexSheetGenerator());
		SHHET_GENERATOR_LIST.add(new SequenceSheetGenerator());
		SHHET_GENERATOR_LIST.add(new ViewSheetGenerator());
		SHHET_GENERATOR_LIST.add(new TriggerSheetGenerator());
		SHHET_GENERATOR_LIST.add(new ColumnSheetGenerator());
		SHHET_GENERATOR_LIST.add(new AllTablesSheetGenerator());
		SHHET_GENERATOR_LIST.add(new AllIndicesSheetGenerator());
		SHHET_GENERATOR_LIST.add(new AllSequencesSheetGenerator());
		SHHET_GENERATOR_LIST.add(new AllViewSheetGenerator());
		SHHET_GENERATOR_LIST.add(new AllTriggerSheetGenerator());
		SHHET_GENERATOR_LIST.add(new CategorySheetGenerator());
		SHHET_GENERATOR_LIST.add(new HistorySheetGenerator());
	}

	public static class LoopDefinition {

		public int startLine;

		public int spaceLine;

		public String sheetName;

		public LoopDefinition(int startLine, int spaceLine, String sheetName) {
			this.startLine = startLine;
			this.spaceLine = spaceLine;
			this.sheetName = sheetName;
		}
	}

	private PictureSheetGenerator pictureSheetGenerator;

	private SheetIndexSheetGenerator sheetIndexSheetGenerator;

	private Map<String, LoopDefinition> loopDefinitionMap;

	private ExportExcelSetting exportExcelSetting;

	private XSSFWorkbook workbook;

	private File excelFile;

	public ExportToExcelManager(ExportExcelSetting exportExcelSetting)
			throws FileNotFoundException {
		super("dialog.message.export.excel");

		this.exportExcelSetting = exportExcelSetting;

		this.sheetNameMap = new HashMap<String, Integer>();
		this.sheetObjectMap = new LinkedHashMap<String, ObjectModel>();

		this.loopDefinitionMap = new HashMap<String, LoopDefinition>();
	}

	@Override
	public void init(ERDiagram diagram, File projectDir) throws Exception {
		super.init(diagram, projectDir);

		this.excelFile = FileUtils.getFile(this.projectDir,
				this.exportExcelSetting.getExcelOutput());
		this.excelFile.getParentFile().mkdirs();

		// this.backup(this.excelFile, true);

		InputStream templateStream = null;

		try {
			templateStream = this.getSelectedTemplate();
			workbook = this.loadTemplateWorkbook(templateStream, this.diagram);

		} finally {
			if (templateStream != null) {
				templateStream.close();
			}
		}

		// check whether the file is not opened by another process.
		POIUtils.writeExcelFile(excelFile, workbook);
	}

	private InputStream getSelectedTemplate() throws FileNotFoundException {
		if (!Check.isEmpty(this.exportExcelSetting.getExcelTemplatePath())) {
			return new FileInputStream(FileUtils.getFile(this.projectDir,
					this.exportExcelSetting.getExcelTemplatePath()));
		}

		String lang = this.exportExcelSetting.getUsedDefaultTemplateLang();

		if ("en".equals(lang)) {
			return TemplatePreferencePage.getDefaultExcelTemplateEn();

		} else if ("ja".equals(lang)) {
			return TemplatePreferencePage.getDefaultExcelTemplateJa();

		}

		String templateName = this.exportExcelSetting.getExcelTemplate();

		File file = new File(
				PreferenceInitializer.getTemplatePath(templateName));

		return new FileInputStream(file);
	}

	@Override
	protected int getTotalTaskCount() {
		return this.countSheetFromTemplate(workbook, this.diagram);
	}

	@Override
	protected void doProcess(ProgressMonitor monitor) throws Exception {
		if (this.exportExcelSetting.isPutERDiagramOnExcel()) {
			this.pictureSheetGenerator = this.createPictureSheetGenerator(
					monitor, workbook);
		}

		this.createSheetFromTemplate(monitor, workbook, diagram,
				this.exportExcelSetting.isUseLogicalNameAsSheet());

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			workbook.getSheetAt(i).setSelected(false);
		}

		if (workbook.getNumberOfSheets() > 0) {
			workbook.getSheetAt(0).setSelected(true);
			workbook.setActiveSheet(0);
			workbook.setFirstVisibleTab(0);
		}

		POIUtils.writeExcelFile(excelFile, workbook);
	}

	private PictureSheetGenerator createPictureSheetGenerator(
			ProgressMonitor monitor, XSSFWorkbook workbook) throws Exception {
		ImageInfoSet imageInfoSet = ExportToImageManager.outputImage(
				this.diagram, this.exportExcelSetting.getCategory(),
				this.projectDir, monitor);

		ImageInfo imageInfo = imageInfoSet.getDiagramImageInfo();

		return new PictureSheetGenerator(workbook, imageInfo.getImageData(),
				imageInfo.getExcelPictureType());
	}

	private XSSFWorkbook loadTemplateWorkbook(InputStream template,
			ERDiagram diagram) throws IOException {

		XSSFWorkbook workbook = POIUtils.readExcelBook(template);

		if (workbook == null) {
			throw new IOException(
					ResourceString.getResourceString("error.read.file"));
		}

		XSSFSheet wordsSheet = workbook.getSheet(WORDS_SHEET_NAME);

		if (wordsSheet == null) {
			throw new IOException(
					ResourceString
							.getResourceString("error.not.found.words.sheet"));
		}

		XSSFSheet loopsSheet = workbook.getSheet(LOOPS_SHEET_NAME);

		if (loopsSheet == null) {
			throw new IOException(
					ResourceString
							.getResourceString("error.not.found.loops.sheet"));
		}

		this.initLoopDefinitionMap(loopsSheet);

		for (AbstractSheetGenerator sheetGenerator : SHHET_GENERATOR_LIST) {
			sheetGenerator.init(wordsSheet);
		}

		this.sheetIndexSheetGenerator = new SheetIndexSheetGenerator();
		this.sheetIndexSheetGenerator.init(wordsSheet);

		return workbook;
	}

	private void initLoopDefinitionMap(XSSFSheet loopsSheet) {
		for (int i = 2; i <= loopsSheet.getLastRowNum(); i++) {
			String templateSheetName = POIUtils.getCellValue(loopsSheet, i, 0);
			if (templateSheetName == null) {
				break;
			}

			int firstLine = POIUtils.getIntCellValue(loopsSheet, i, 1);
			int spaceLine = POIUtils.getIntCellValue(loopsSheet, i, 2);
			String sheetName = POIUtils.getCellValue(loopsSheet, i, 3);

			this.loopDefinitionMap.put(templateSheetName, new LoopDefinition(
					firstLine, spaceLine, sheetName));
		}
	}

	private AbstractSheetGenerator getSheetGenerator(String templateSheetName) {
		for (AbstractSheetGenerator sheetGenerator : SHHET_GENERATOR_LIST) {
			if (sheetGenerator.getTemplateSheetName().equals(templateSheetName)) {
				return sheetGenerator;
			}
		}

		return null;
	}

	private void initSheetNameMap(XSSFWorkbook workbook) {
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			String sheetName = workbook.getSheetName(i);
			this.sheetNameMap.put(sheetName.toUpperCase(), 0);
		}
	}

	private void createSheetFromTemplate(ProgressMonitor monitor,
			XSSFWorkbook workbook, ERDiagram diagram,
			boolean useLogicalNameAsSheetName) throws InterruptedException {
		this.initSheetNameMap(workbook);

		int originalSheetNum = workbook.getNumberOfSheets();

		int sheetIndexSheetNo = -1;

		while (originalSheetNum > 0) {
			String templateSheetName = workbook.getSheetName(0);

			AbstractSheetGenerator sheetGenerator = this
					.getSheetGenerator(templateSheetName);

			if (sheetGenerator != null) {
				sheetGenerator.generate(monitor, workbook, 0,
						useLogicalNameAsSheetName, this.sheetNameMap,
						this.sheetObjectMap, diagram, loopDefinitionMap);
				workbook.removeSheetAt(0);

			} else {
				if (!isExcludeTarget(templateSheetName)) {
					moveSheet(workbook, 0);
					XSSFSheet sheet = workbook.getSheetAt(workbook
							.getNumberOfSheets() - 1);

					this.sheetObjectMap.put(templateSheetName,
							new StringObjectModel(templateSheetName));

					if (this.pictureSheetGenerator != null) {
						this.pictureSheetGenerator.setImage(workbook, sheet);
					}

					if (this.sheetIndexSheetGenerator.getTemplateSheetName()
							.equals(templateSheetName)) {
						sheetIndexSheetNo = workbook.getNumberOfSheets()
								- originalSheetNum;

						String name = this.sheetIndexSheetGenerator
								.getSheetName();

						name = AbstractSheetGenerator.decideSheetName(name,
								sheetNameMap);

						monitor.subTaskWithCounter(name);

						workbook.setSheetName(workbook.getNumberOfSheets() - 1,
								name);
					} else {
						monitor.subTaskWithCounter(sheet.getSheetName());
					}

				} else {
					monitor.subTaskWithCounter("Removing template sheet");
					workbook.removeSheetAt(0);
				}

				monitor.worked(1);
			}

			originalSheetNum--;
		}

		if (sheetIndexSheetNo != -1) {
			this.sheetIndexSheetGenerator.generate(monitor, workbook,
					sheetIndexSheetNo, useLogicalNameAsSheetName,
					this.sheetNameMap, this.sheetObjectMap, diagram,
					loopDefinitionMap);
		}
	}

	public static XSSFSheet moveSheet(XSSFWorkbook workbook, int sheetNo) {
		XSSFSheet oldSheet = workbook.getSheetAt(sheetNo);
		String sheetName = oldSheet.getSheetName();

		XSSFSheet newSheet = workbook.cloneSheet(sheetNo);
		int newSheetNo = workbook.getSheetIndex(newSheet);

		workbook.removeSheetAt(sheetNo);

		workbook.setSheetName(newSheetNo - 1, sheetName);

		return newSheet;
	}

	private int countSheetFromTemplate(XSSFWorkbook workbook, ERDiagram diagram) {
		int count = 0;

		for (int sheetNo = 0; sheetNo < workbook.getNumberOfSheets(); sheetNo++) {
			String templateSheetName = workbook.getSheetName(sheetNo);

			AbstractSheetGenerator sheetGenerator = this
					.getSheetGenerator(templateSheetName);

			if (sheetGenerator != null) {
				count += sheetGenerator.count(diagram);

			} else {
				count++;
			}
		}

		if (this.exportExcelSetting.isPutERDiagramOnExcel()) {
			count += 1;
		}

		return count;
	}

	private boolean isExcludeTarget(String templateSheetName) {
		if (WORDS_SHEET_NAME.equals(templateSheetName)
				|| LOOPS_SHEET_NAME.equals(templateSheetName)) {
			return true;
		}

		return false;
	}

	public File getOutputFileOrDir() {
		return FileUtils.getFile(this.projectDir,
				this.exportExcelSetting.getExcelOutput());
	}
}
