package ermaster.editor.model.dbexport.excel.sheet_generator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.ObjectModel;
import ermaster.editor.model.StringObjectModel;
import ermaster.editor.model.dbexport.excel.ExportToExcelManager.LoopDefinition;
import ermaster.editor.model.progress_monitor.ProgressMonitor;
import ermaster.editor.model.tracking.ChangeTracking;
import ermaster.util.POIUtils;
import ermaster.util.POIUtils.CellLocation;

public class HistorySheetGenerator extends AbstractSheetGenerator {

	private static final String KEYWORD_DATE = "$DATE";

	private static final String KEYWORD_CONTENTS = "$CON";

	private static final String KEYWORD_DATE_FORMAT = "$FMT";

	private static final String KEYWORD_SHEET_NAME = "$SHTN";

	private static final String[] FIND_KEYWORDS_LIST = { KEYWORD_DATE,
			KEYWORD_CONTENTS };

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generate(ProgressMonitor monitor, XSSFWorkbook workbook,
			int sheetNo, boolean useLogicalNameAsSheetName,
			Map<String, Integer> sheetNameMap,
			Map<String, ObjectModel> sheetObjectMap, ERDiagram diagram,
			Map<String, LoopDefinition> loopDefinitionMap)
			throws InterruptedException {

		String sheetName = this.getSheetName();

		XSSFSheet newSheet = createNewSheet(workbook, sheetNo, sheetName,
				sheetNameMap);

		sheetName = workbook.getSheetName(workbook.getSheetIndex(newSheet));
		monitor.subTaskWithCounter(sheetName);

		sheetObjectMap.put(sheetName, new StringObjectModel(sheetName));

		this.setHistoryListData(workbook, newSheet, sheetObjectMap, diagram);
		monitor.worked(1);
	}

	public void setHistoryListData(XSSFWorkbook workbook, XSSFSheet sheet,
			Map<String, ObjectModel> sheetObjectMap, ERDiagram diagram) {
		CellLocation cellLocation = POIUtils
				.findCell(sheet, FIND_KEYWORDS_LIST);

		if (cellLocation != null) {
			int rowNum = cellLocation.r;
			XSSFRow templateRow = sheet.getRow(rowNum);

			ColumnTemplate columnTemplate = this.loadColumnTemplate(workbook,
					sheet, cellLocation);
			int order = 1;

			XSSFFont linkCellFont = null;
			int linkCol = -1;

			for (ChangeTracking changeTracking : diagram
					.getChangeTrackingList().getList()) {
				XSSFRow row = POIUtils.insertRow(sheet, rowNum++);

				for (int columnNum : columnTemplate.columnTemplateMap.keySet()) {
					XSSFCell cell = row.createCell(columnNum);
					String template = columnTemplate.columnTemplateMap
							.get(columnNum);

					String value = null;
					if (KEYWORD_ORDER.equals(template)) {
						value = String.valueOf(order);

					} else {
						if (KEYWORD_DATE.equals(template)) {
							DateFormat format = new SimpleDateFormat(
									this.keywordsValueMap
											.get(KEYWORD_DATE_FORMAT));
							try {
								value = format.format(changeTracking
										.getUpdatedDate());

							} catch (Exception e) {
								value = changeTracking.getUpdatedDate()
										.toString();
							}

						} else if (KEYWORD_CONTENTS.equals(template)) {
							value = changeTracking.getComment();
						}

						XSSFRichTextString text = new XSSFRichTextString(value);
						cell.setCellValue(text);
					}

					order++;
				}
			}

			this.setCellStyle(columnTemplate, sheet, cellLocation.r, rowNum
					- cellLocation.r, templateRow.getFirstCellNum());

			if (linkCol != -1) {
				for (int row = cellLocation.r; row < rowNum; row++) {
					XSSFCell cell = sheet.getRow(row).getCell(linkCol);
					cell.getCellStyle().setFont(linkCellFont);
				}
			}
		}
	}

	public String getSheetName() {
		String name = this.keywordsValueMap.get(KEYWORD_SHEET_NAME);

		if (name == null) {
			name = "dialog.title.change.tracking";
		}

		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTemplateSheetName() {
		return "history_template";
	}

	@Override
	public String[] getKeywords() {
		return new String[] { KEYWORD_DATE, KEYWORD_CONTENTS, KEYWORD_ORDER,
				KEYWORD_DATE_FORMAT, KEYWORD_SHEET_NAME };
	}

	@Override
	public int getKeywordsColumnNo() {
		return 28;
	}

	@Override
	public int count(ERDiagram diagram) {
		return 1;
	}
}
