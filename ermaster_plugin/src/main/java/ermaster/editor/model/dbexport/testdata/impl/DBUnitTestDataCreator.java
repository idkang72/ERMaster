package ermaster.editor.model.dbexport.testdata.impl;

import java.util.Map;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.column.NormalColumn;
import ermaster.editor.model.testdata.RepeatTestData;
import ermaster.editor.model.testdata.RepeatTestDataDef;
import ermaster.editor.persistent.impl.PersistentXmlImpl;
import ermaster.util.Format;

public class DBUnitTestDataCreator extends AbstractTextTestDataCreator {

	private String encoding;

	public DBUnitTestDataCreator(String encoding) {
		this.encoding = encoding;
	}

	@Override
	protected void writeDirectTestData(ERTable table,
			Map<NormalColumn, String> data, String database) {
		StringBuilder sb = new StringBuilder();

		sb.append("\t\t<row>\r\n");

		for (NormalColumn column : table.getExpandedColumns()) {
			String value = Format.null2blank(data.get(column));

			if (value == null || "null".equals(value.toLowerCase())) {
				sb.append("\t\t\t<null/>\r\n");

			} else {
				sb.append("\t\t\t<value>");
				sb.append(PersistentXmlImpl.escape(value));
				sb.append("</value>\r\n");
			}
		}

		sb.append("\t\t</row>\r\n");

		out.print(sb.toString());
	}

	@Override
	protected void writeRepeatTestData(ERTable table,
			RepeatTestData repeatTestData, String database) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < repeatTestData.getTestDataNum(); i++) {
			sb.append("\t\t<row>\r\n");

			for (NormalColumn column : table.getExpandedColumns()) {

				RepeatTestDataDef repeatTestDataDef = repeatTestData
						.getDataDef(column);

				String value = this.getMergedRepeatTestDataValue(i,
						repeatTestDataDef, column);

				if (value == null || "null".equals(value.toLowerCase())) {
					sb.append("\t\t\t<null/>\r\n");

				} else {
					sb.append("\t\t\t<value>");
					sb.append(PersistentXmlImpl.escape(value));
					sb.append("</value>\r\n");

				}
			}

			sb.append("\t\t</row>\r\n");
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
		StringBuilder sb = new StringBuilder();

		sb.append("\t<table name=\"");
		sb.append(table.getNameWithSchema(diagram.getDatabase()));
		sb.append("\">\r\n");

		for (NormalColumn column : table.getExpandedColumns()) {
			sb.append("\t\t<column>");
			sb.append(column.getPhysicalName());
			sb.append("</column>\r\n");
		}

		out.print(sb.toString());
	}

	@Override
	protected void writeTableFooter(ERTable table) {
		out.print("\t</table>\r\n");
	}

	@Override
	protected String getFileExtention() {
		return ".xml";
	}

}
