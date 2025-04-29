package ar.edu.mercadogratis.domain.model;

import org.springframework.data.annotation.Id;
import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	protected String id;

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
}