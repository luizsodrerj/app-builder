package appbuilder.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DATA_TYPE database table.
 * 
 */
@Entity
@Table(name="DATA_TYPE")
@NamedQuery(name="DataType.findAll", query="SELECT d FROM DataType d")
public class DataType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String descricao;

	
	
	public DataType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		DataType other = (DataType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}