package dev.devzero.api.common;

import java.io.Serializable;

public abstract class CrudService <T extends BaseEntity<ID>, ID extends Serializable> extends BaseCrud<T, ID>{

}
