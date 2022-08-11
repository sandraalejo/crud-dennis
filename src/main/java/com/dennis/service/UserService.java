package com.dennis.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dennis.model.User;
import com.dennis.repository.UserRepository;

/**
 * UserService
 *
 * Esta clase contiene todos los métodos que necesitará el UserController para
 * recibir y ejecutar todas las peticiones.
 *
 * @author Dennis Pérez
 * @version 1.0
 */
@Service
public class UserService {

	/**
	 * Repositorio que utilizará el servicio para hacer la lectura y escritura en la
	 * base de datos
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Agrega una lista de usuarios a la base de datos
	 * 
	 * @param users
	 * @return
	 *         <ul>
	 *         <li>null: La lista es nula, está vacía o contiene un usuario
	 *         nulo</li>
	 *         <li>List<User>: La lista de usuarios que fue vaidada y agregada a la
	 *         base de datos</li>
	 *         </ul>
	 */
	public List<User> createUsers(List<User> users) {
		if (users == null || users.isEmpty() || users.contains(null) == true) {
			return null;
		}
		return userRepository.saveAll(users);
	}

	/**
	 * Devuelve una lista de todos los usuarios que hay en la base de datos
	 * 
	 * @return
	 *         <ul>
	 *         <li>empty: Si no existe ningún usuario entonces devuelve una lista
	 *         vacía</li>
	 *         <li>List<User>: Devuelva una lista de los usuarios encontrados</li>
	 *         </ul>
	 */
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * Devuelve una lista de los usuarios que tengan el mismo nombre de acuerdo al
	 * parámetro name
	 * 
	 * @param name
	 * @return
	 *         <ul>
	 *         <li>empty: Si no existe ningún usuario entonces devuelve una lista
	 *         vacía</li>
	 *         <li>List<User>: Devuelva una lista de los usuarios encontrados</li>
	 *         </ul>
	 */
	public List<User> getUsersByName(String name) {
		return userRepository.findByName(name);
	}

	/**
	 * Devuelve una lista de los usuarios que tengan la misma edad de acuerdo al
	 * parámetro age
	 * 
	 * @param age
	 * @return
	 *         <ul>
	 *         <li>empty: Si no existe ningún usuario entonces devuelve una lista
	 *         vacía</li>
	 *         <li>List<User>: Devuelva una lista de los usuarios encontrados</li>
	 *         </ul>
	 */
	public List<User> getUsersByAge(int age) {
		return userRepository.findByAge(age);
	}

	/**
	 * Adquiere una lista de los usuarios que tengan el mismo valor de acuerdo al
	 * parámetro value en dos diferentes casos, para nombre y edad. Recibe ambos
	 * parámetros de tipo String y los trata diferente de acuerdo a cada caso: -Para
	 * name, utiliza su valor en el mismo formato de entrada -Para age, verifica si
	 * se puede convertir en un valor numérico y de ser posible lo transforma Dado
	 * caso que no, establece la edad en 0
	 * 
	 * @param param, value
	 * @return
	 *         <ul>
	 *         <li>empty: Si no existe ningún usuario entonces devuelve una lista
	 *         vacía</li>
	 *         <li>List<User>: Devuelva una lista de los usuarios encontrados</li>
	 *         </ul>
	 */
	public List<User> getUsersByParam(String param, String value) {
		List<User> users = Collections.emptyList();

		switch (param) {
		case "name":
			users = getUsersByName(value);
			break;
		case "age":
			int age = 0;
			if (StringUtils.isNumeric(value) == true) {
				age = Integer.parseInt(value);
			}
			users = getUsersByAge(age);
			break;
		}

		return users;
	}

	/**
	 * Actualiza a un usuario de acuerdo a su id
	 * 
	 * @param newUser, id
	 * @return
	 *         <ul>
	 *         <li>null: Si el usuario proporcionado en los parámetros es nulo o
	 *         vacio</li>
	 *         <li>User: Devuelve el usuario que fue actualizado</li>
	 *         </ul>
	 */
	public User updateUserById(User newUser, String id) {
		if (ObjectUtils.isEmpty(newUser) == true) {
			return null;
		}
		newUser.setId(id);
		return userRepository.save(newUser);
	}

	/**
	 * Eliminar un usuario de acuerdo a su id
	 * 
	 * @param id
	 * @return
	 *         <ul>
	 *         <li>null: Si no se encuentra un usuario con ese id</li>
	 *         <li>User: Devuelve el usuario que fue eliminado</li>
	 *         </ul>
	 */
	public User deleteUserById(String id) {
		User ActualUser = userRepository.findById(id).orElse(null);
		if (ObjectUtils.isEmpty(ActualUser) == true) {
			return ActualUser;
		}
		userRepository.delete(ActualUser);
		return ActualUser;
	}

	/**
	 * Elimina los usuarios que tengan el mismo nombre que el parámetro name
	 * 
	 * @param name
	 * @return
	 *         <ul>
	 *         <li>empty: Si no se encuentra usuarios con ese nombre</li>
	 *         <li>List<User>: Devuelve los usuarios que fueron eliminados</li>
	 *         </ul>
	 */
	public List<User> deleteUserByName(String name) {
		List<User> Users = getUsersByName(name);
		userRepository.deleteAll(Users);
		return Users;
	}

	/**
	 * Elimina los usuarios que tengan la misma edad que el parámetro age
	 * 
	 * @param age
	 * @return
	 *         <ul>
	 *         <li>empty: Si no se encuentra usuarios con ese nombre</li>
	 *         <li>List<User>: Devuelve los usuarios que fueron eliminados</li>
	 *         </ul>
	 */
	public List<User> deleteUserByAge(int age) {
		List<User> Users = getUsersByAge(age);
		userRepository.deleteAll(Users);
		return Users;
	}

	/**
	 * Adquiere una lista de los usuarios eliminados que tenían el mismo valor de
	 * acuerdo al parámetro value en dos diferentes casos, para nombre y edad.
	 * Recibe ambos parámetros de tipo String y los trata diferente de acuerdo a
	 * cada caso: -Para name, utiliza su valor en el mismo formato de entrada -Para
	 * age, verifica si se puede convertir en un valor numérico y de ser posible lo
	 * transforma Dado caso que no, establece la edad en 0
	 * 
	 * @param param, value
	 * @return
	 *         <ul>
	 *         <li>empty: Si no se elimino ningún usuario entonces devuelve una
	 *         lista vacía</li>
	 *         <li>List<User>: Devuelva una lista de los usuarios eliminados</li>
	 *         </ul>
	 */
	public List<User> deleteUserByParam(String param, String value) {
		List<User> users = Collections.emptyList();
		;
		switch (param) {
		case "name":
			users = deleteUserByName(value);
			break;
		case "age":
			int age = 0;
			if (StringUtils.isNumeric(value) == true) {
				age = Integer.parseInt(value);
			}
			users = deleteUserByAge(age);
			break;
		}

		return users;
	}

}
