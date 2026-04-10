package appbuilder.backapp.core.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the form_register database table.
 * 
 */
@Entity
@Table(name="form_register")
@NamedQuery(name="FormRegister.findAll", query="SELECT f FROM FormRegister f")
public class FormRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//bi-directional many-to-one association to FieldValue
	@OneToMany(mappedBy="formRegister", fetch = FetchType.LAZY)
	private List<FieldValue> fieldValues = new ArrayList<>();

	//bi-directional many-to-one association to Form
	@ManyToOne
	@JoinColumn(name="ID_FORM")
	private Form form;


	
	public FormRegister() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FieldValue> getFieldValues() {
		return this.fieldValues;
	}

	public void setFieldValues(List<FieldValue> fieldValues) {
		this.fieldValues = fieldValues;
	}

	public FieldValue addFieldValue(FieldValue fieldValue) {
		getFieldValues().add(fieldValue);
		fieldValue.setFormRegister(this);

		return fieldValue;
	}

	public FieldValue removeFieldValue(FieldValue fieldValue) {
		getFieldValues().remove(fieldValue);
		fieldValue.setFormRegister(null);

		return fieldValue;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}





}