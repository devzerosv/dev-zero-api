package dev.devzero.api.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable> extends CrudService<T, ID> {

}
