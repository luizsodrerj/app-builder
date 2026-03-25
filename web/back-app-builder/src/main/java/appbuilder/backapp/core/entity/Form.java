package appbuilder.backapp.core.entity;

import jakarta.persistence.Entity;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the form database table.
 * 
 */
@Entity
@NamedQuery(name="Form.findAll", query="SELECT f FROM Form f")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="`LABEL`")
	private String label;

	private String nome;

	//bi-directional many-to-one association to App
	@ManyToOne
	@JoinColumn(name="ID_APP")
	private App app;

	//bi-directional many-to-one association to Field
	@OneToMany(mappedBy="form", fetch = FetchType.LAZY)
	private List<Field> fields = new ArrayList<>();

	public Form() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public App getApp() {
		return this.app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public List<Field> getFields() {
		return this.fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Field addField(Field field) {
		getFields().add(field);
		field.setForm(this);

		return field;
	}

	public Field removeField(Field field) {
		getFields().remove(field);
		field.setForm(null);

		return field;
	}

}