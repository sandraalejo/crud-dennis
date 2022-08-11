package com.dennis.model;

import org.springframework.data.annotation.Id;

/**
 * Clase User
 *
 * Contiene información de cada usuario, como lo son *el id, elnombre y su edad.
 *
 * @author Dennis Pérez
 * @version 1.0
 */
public class User {
	// Atributos

	/**
	 * Id del usuario
	 */
	@Id
	private String id;
	/**
	 * Nombre del usuario
	 */
	private String name;
	/**
	 * Edad del usuario
	 */
	private int age;

	// Metodos publicos

	/**
	 * Asigna el nombre a un usuario
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Asigna el id a un usuario
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Asigna la edad a un usuario
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Devuelve el nombre de un usuario
	 * 
	 * @return
	 *         <ul>
	 *         <li>el nombre del usuario</li>
	 *         </ul>
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Devuelve la edad de un usuario
	 * 
	 * @return
	 *         <ul>
	 *         <li>el edad del usuario</li>
	 *         </ul>
	 */
	public int getage() {
		return this.age;
	}

	/**
	 * Devuelve el id de un usuario
	 * 
	 * @return
	 *         <ul>
	 *         <li>el id del usuario</li>
	 *         </ul>
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Devuelve el objeto User en una cadena de texto
	 * 
	 * @return
	 *         <ul>
	 *         <li>User{id=id, name='name', age='age'}</li>
	 *         </ul>
	 */
	public String toString() {
		return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", age='" + this.age + '\'' + '}';
	}
}
