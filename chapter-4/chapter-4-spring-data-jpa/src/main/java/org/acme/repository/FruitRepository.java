package org.acme.repository;

import java.util.Optional;

import org.acme.domain.Fruit;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FruitRepository implements PanacheRepositoryBase<Fruit, Long> {
	public Optional<Fruit> findByName(String name) {
		return find("name", name).firstResultOptional();
	}
}
