package dev.devzero.api.common;

import java.io.Serializable;


/**
 * Interface que sirve para identificar una entidad de negocio.
 *
 * @author Mauricio Saca <saca.menendez@gmail.com>
 * @param <ID>
 *            Representa el tipo de dato de la llave primaria.
 */
public interface BaseEntity<ID> extends Serializable {

	public ID getId();

	public void setId(ID id);

}
