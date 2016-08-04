package cc.datatables.core.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatatablesResponse<T> implements Serializable {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8281134953302094732L;

	@JsonProperty(value = "aaData")
	private final List<T> data;

	@JsonProperty(value = "iTotalRecords")
	private final Long totalRecords;

	@JsonProperty(value = "iTotalDisplayRecords")
	private final Long totalDisplayRecords;

	@JsonProperty(value = "sEcho")
	private final Integer echo;

	private DatatablesResponse(DataSet<T> dataSet, DatatablesCriterias criterias) {
		this.data = dataSet.getRows();
		this.totalRecords = dataSet.getTotalRecords();
		this.totalDisplayRecords = dataSet.getTotalDisplayRecords();
		this.echo = criterias.getEcho();
	}

	public List<T> getData() {
		return data;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public Long getTotalDisplayRecords() {
		return totalDisplayRecords;
	}

	public Integer getEcho() {
		return echo;
	}

	public static <T> DatatablesResponse<T> build(DataSet<T> dataSet, DatatablesCriterias criterias) {
		return new DatatablesResponse<T>(dataSet, criterias);
	}
}
