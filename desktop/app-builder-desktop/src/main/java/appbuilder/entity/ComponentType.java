package appbuilder.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the COMPONENT_TYPE database table.
 * 
 */
@Entity
@Table(name="COMPONENT_TYPE")
@NamedQuery(name="ComponentType.findAll", query="SELECT c FROM ComponentType c")
public class ComponentType implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int CAIXA_DE_TEXTO 		= 0;
	public static final int AREA_DE_TEXTO 		= 1;
	public static final int CAIXA_DE_CHECAGEM	= 2;
	public static final int LISTA_DE_VALORES 	= 3;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String tipo;

	
	
	public ComponentType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		ComponentType other = (ComponentType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}