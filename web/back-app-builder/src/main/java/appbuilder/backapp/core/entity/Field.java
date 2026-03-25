package appbuilder.backapp.core.entity;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the field database table.
 * 
 */
@Entity
@NamedQuery(name="Field.findAll", query="SELECT f FROM Field f")
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nome;

	//bi-directional many-to-one association to Form
	@ManyToOne
	@JoinColumn(name="ID_FORM")
	private Form form;

	//bi-directional many-to-one association to FieldAttribute
	@OneToMany(mappedBy="field", fetch = FetchType.LAZY)
	private List<FieldAttribute> fieldAttributes = new ArrayList<>();

//	//bi-directional many-to-one association to FieldValue
//	@OneToMany(mappedBy="field")
//	private List<FieldValue> fieldValues;

	public Field() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public List<FieldAttribute> getFieldAttributes() {
		return this.fieldAttributes;
	}

	public void setFieldAttributes(List<FieldAttribute> fieldAttributes) {
		this.fieldAttributes = fieldAttributes;
	}

	public FieldAttribute addFieldAttribute(FieldAttribute fieldAttribute) {
		getFieldAttributes().add(fieldAttribute);
		fieldAttribute.setField(this);

		return fieldAttribute;
	}

	public FieldAttribute removeFieldAttribute(FieldAttribute fieldAttribute) {
		getFieldAttributes().remove(fieldAttribute);
		fieldAttribute.setField(null);

		return fieldAttribute;
	}

//	public List<FieldValue> getFieldValues() {
//		return this.fieldValues;
//	}
//
//	public void setFieldValues(List<FieldValue> fieldValues) {
//		this.fieldValues = fieldValues;
//	}


}