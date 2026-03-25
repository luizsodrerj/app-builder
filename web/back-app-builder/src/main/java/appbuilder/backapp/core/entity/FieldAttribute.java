package appbuilder.backapp.core.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the field_attribute database table.
 * 
 */
@Entity
@Table(name="field_attribute")
@NamedQuery(name="FieldAttribute.findAll", query="SELECT f FROM FieldAttribute f")
public class FieldAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="DATA_TYPE")
	private Integer dataType;

	@Column(name="FIELD_TYPE")
	private Integer fieldType;

	@Column(name="FORMAT_TYPE")
	private Integer formatType;

	@Column(name="`LABEL`")
	private String label;

	//bi-directional many-to-one association to Field
	@ManyToOne
	@JoinColumn(name="ID_FIELD")
	private Field field;

	public FieldAttribute() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public Integer getFormatType() {
		return this.formatType;
	}

	public void setFormatType(Integer formatType) {
		this.formatType = formatType;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

}