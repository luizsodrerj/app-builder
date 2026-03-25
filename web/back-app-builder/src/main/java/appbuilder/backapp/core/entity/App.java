package appbuilder.backapp.core.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the app database table.
 * 
 */
@Entity
@NamedQuery(name="App.findAll", query="SELECT a FROM App a")
public class App implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	private String nome;

	//bi-directional many-to-one association to Form
	@OneToMany(mappedBy="app", fetch = FetchType.LAZY)
	private List<Form> forms = new ArrayList<>();

	public App() {
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

	public List<Form> getForms() {
		return this.forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public Form addForm(Form form) {
		getForms().add(form);
		form.setApp(this);

		return form;
	}

	public Form removeForm(Form form) {
		getForms().remove(form);
		form.setApp(null);

		return form;
	}

}