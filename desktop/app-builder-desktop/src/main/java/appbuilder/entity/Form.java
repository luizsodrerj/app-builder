package appbuilder.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the FORM database table.
 * 
 */
@Entity
@NamedQuery(name="Form.findAll", query="SELECT f FROM Form f")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="NOME_FORM")
	private String nomeForm;

	//bi-directional many-to-one association to App
	@ManyToOne
	@JoinColumn(name="ID_APP")
	private App app;

	//bi-directional many-to-one association to FormField
	@OneToMany(mappedBy="form")
	private List<FormField> formFields = new ArrayList<>();

	
	
	public Form() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeForm() {
		return this.nomeForm;
	}

	public void setNomeForm(String nomeForm) {
		this.nomeForm = nomeForm;
	}

	public App getApp() {
		return this.app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public List<FormField> getFormFields() {
		return this.formFields;
	}

	public void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}

	public FormField addFormField(FormField formField) {
		getFormFields().add(formField);
		formField.setForm(this);

		return formField;
	}

	public FormField removeFormField(FormField formField) {
		getFormFields().remove(formField);
		formField.setForm(null);

		return formField;
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
		Form other = (Form) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}