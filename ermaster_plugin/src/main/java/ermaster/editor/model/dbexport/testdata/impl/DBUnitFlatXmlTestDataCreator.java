package ermaster.editor.model.dbexport.testdata.impl;

import java.util.Map;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.column.NormalColumn;
import ermaster.editor.model.testdata.RepeatTestData;
import ermaster.editor.model.testdata.RepeatTestDataDef;
import ermaster.editor.persistent.impl.PersistentXmlImpl;
import ermaster.util.Format;

public class DBUnitFlatXmlTestDataCreator extends AbstractTextTestDataCreator {

	private String encoding;

	public DBUnitFlatXmlTestDataCreator(String encoding) {
		this.encoding = encoding;
	}

	@Override
	protected void writeDirectTestData(ERTable table,
			Map<NormalColumn, String> data, String database) {
		StringBuilder sb = new StringBuilder();

		sb.append("\t<");
		sb.append(table.getNameWithSchema(database));

		for (NormalColumn column : table.getExpandedColumns()) {
			String value = Format.null2blank(data.get(column));

			if (value != null && !"null".equals(value.toLowerCase())) {
				sb.append(" ");
				sb.append(column.getPhysicalName());
				sb.append("=\"");
				sb.append(PersistentXmlImpl.escape(value));
				sb.append("\"");
			}
		}

		sb.append("/>\r\n");

		out.print(sb.toString());
	}

	@Override
	protected void writeRepeatTestData(ERTable table,
			RepeatTestData repeatTestData, String database) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < repeatTestData.getTestDataNum(); i++) {
			sb.append("\t<");
			sb.append(table.getNameWithSchema(database));

			for (NormalColumn column : table.getExpandedColumns()) {
				RepeatTestDataDef repeatTestDataDef = repeatTestData
						.getDataDef(column);

				String value = this.getMergedRepeatTestDataValue(i,
						repeatTestDataDef, column);

				if (value != null && !"null".equals(value.toLowerCase())) {
					sb.append(" ");
					sb.append(column.getPhysicalName());
					sb.append("=\"");

					sb.append(PersistentXmlImpl.escape(value));
					sb.append("\"");
				}
			}

			sb.append("/>\r\n");
		}

		out.print(sb.toString());
	}

	@Override
	protected String getHeader() {
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"");
		sb.append(this.encoding);
		sb.append("\" ?>\r\n<dataset>\r\n");

		return sb.toString();
	}

	@Override
	protected String getFooter() {
		return "</dataset>";
	}

	@Override
	protected void writeTableHeader(ERDiagram diagram, ERTable table) {
	}

	@Override
	protected void writeTableFooter(ERTable table) {
	}

	@Override
	protected String getFileExtention() {
		return ".xml";
	}

}
