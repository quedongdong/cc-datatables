package cc.datatables.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.datatables.core.DTConstants;
import cc.datatables.core.model.ColumnDef;
import cc.datatables.core.model.DatatablesCriterias;
import cc.datatables.core.model.SortDirection;

public class DatatablesCriteriaUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatatablesCriterias.class);

	public static DatatablesCriterias getFromRequest(HttpServletRequest request) {
		LOGGER.debug("getFromRequest() start");
		Validate.notNull(request, "The HTTP request cannot be null");
		String paramSearch = request.getParameter(DTConstants.DT_S_SEARCH);
		LOGGER.debug("paramSearch {} ", paramSearch);
		String parameEcho = request.getParameter(DTConstants.DT_S_ECHO);
		LOGGER.debug("parameEcho {} ", parameEcho);
		String paramStart = request.getParameter(DTConstants.DT_I_START);
		LOGGER.debug("paramStart {} ", paramStart);

		String paramLength = request.getParameter(DTConstants.DT_I_LENGTH);
		LOGGER.debug("paramLength {} ", paramLength);

		String sSortingCols = request.getParameter(DTConstants.DT_I_SORTING_COLS);
		LOGGER.debug("sSortingCols {} ", sSortingCols);

		String iColumns = request.getParameter(DTConstants.DT_I_COLUMNS);
		LOGGER.debug("iColumns {} ", iColumns);

		Integer echo = StringUtils.isNotBlank(parameEcho) ? Integer.parseInt(parameEcho) : -1;
		Integer start = StringUtils.isNotBlank(paramStart) ? Integer.parseInt(paramStart) : -1;
		Integer length = StringUtils.isNotBlank(paramLength) ? Integer.parseInt(paramLength) : -1;

		Integer sortingCols = StringUtils.isNotBlank(sSortingCols) ? Integer.parseInt(sSortingCols) : -1;
		Integer columns = StringUtils.isNotBlank(iColumns) ? Integer.parseInt(iColumns) : -1;

		// Column definitions
		List<ColumnDef> columnDefs = new ArrayList<ColumnDef>();

		ColumnDef columnDef = null;
		for (int i = 0; i < columns; i++) {
			String columnName = request.getParameter(DTConstants.DT_M_DATA_PROP + i);
			if (StringUtils.isEmpty(columnName)) {
				continue;
			}
			columnDef = new ColumnDef();
			columnDef.setName(columnName);
			columnDef.setSearchable(Boolean.parseBoolean(request.getParameter(DTConstants.DT_B_SEARCHABLE + i)));
			columnDef.setSortable(Boolean.parseBoolean(request.getParameter(DTConstants.DT_B_SORTABLE + i)));
			columnDef.setRegex(request.getParameter(DTConstants.DT_B_REGEX_COL + i));

			if (StringUtils.isNotBlank(paramSearch)) {
				if (columnDef.isSearchable()) {
					columnDef.setFiltered(true);
					columnDef.setSearch(paramSearch);
				}
			}
			columnDefs.add(columnDef);
		}

		// 排序列
		List<ColumnDef> sortedColumnDefs = new LinkedList<ColumnDef>();
		for (int i = 0; i < sortingCols; i++) {
			String paramSortedCol = request.getParameter(DTConstants.DT_I_SORT_COL + i);

			if (StringUtils.isNotBlank(paramSortedCol)) {
				Integer sortedCol = Integer.parseInt(paramSortedCol);
				ColumnDef sortedColumnDef = columnDefs.get(sortedCol - 1);
				String sortedColDirection = request.getParameter(DTConstants.DT_S_SORT_DIR + i);
				if (StringUtils.isNotBlank(sortedColDirection)) {
					sortedColumnDef.setSortDirection(SortDirection.valueOf(sortedColDirection.toUpperCase()));
				}
				sortedColumnDefs.add(sortedColumnDef);
			}
		}
		DatatablesCriterias criterias = new DatatablesCriterias(paramSearch, start, length, columnDefs,
				sortedColumnDefs, echo);
		LOGGER.debug("getFromRequest() finished");
		return criterias;
	}
}
