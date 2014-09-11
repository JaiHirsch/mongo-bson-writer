package org.jpa.mysql.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<Foo, Long>{

	List<Foo> findByLastName(String lastName);
}
