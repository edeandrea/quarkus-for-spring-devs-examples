package org.acme.domain;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("fruits")
public class Fruit {
	@Id
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;
	private String description;

	public Fruit() {
	}

	public Fruit(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Fruit.class.getSimpleName() + "[", "]")
			.add("id=" + this.id)
			.add("name='" + this.name + "'")
			.add("description='" + this.description + "'")
			.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		Fruit fruit = (Fruit) o;
		return this.id.equals(fruit.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}
}
