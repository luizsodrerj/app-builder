package appbuilder.backapp.core.entity;

import jakarta.persistence.JoinColumn;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the field_value database table.
 * 
 */
@Entity
@Table(name="field_value")
@NamedQuery(name="FieldValue.findAll", query="SELECT f FROM FieldValue f")
public class FieldValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String val;

	//bi-directional many-to-one association to FormRegister
	@ManyToOne
	@JoinColumn(name="ID_REGISTER")
	private FormRegister formRegister;

	//bi-directional many-to-one association to Field
	@ManyToOne
	@JoinColumn(name="ID_FIELD")
	private Field field;

	
	
	public FieldValue() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVal() {
		return this.val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public FormRegister getFormRegister() {
		return this.formRegister;
	}

	public void setFormRegister(FormRegister formRegister) {
		this.formRegister = formRegister;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

}