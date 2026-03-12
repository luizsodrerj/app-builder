package appbuilder.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the APP database table.
 * 
 */
@Entity
@NamedQuery(name="App.findAll", query="SELECT a FROM App a")
public class App implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="NOME_APP")
	private String nomeApp;

	//bi-directional many-to-one association to Form
	@OneToMany(mappedBy="app")
	private List<Form> forms = new ArrayList<>();

	
	
	public App() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeApp() {
		return this.nomeApp;
	}

	public void setNomeApp(String nomeApp) {
		this.nomeApp = nomeApp;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}