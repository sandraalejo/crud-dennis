package com.dennis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.dennis.model.User;
import com.dennis.service.UserService;

/**
 * UserController
 *
 * Esta clase contiene todos los métodos que se utilizaran por el RESTController
 * para hacer las peticiones al API UserController las recibir y se las
 * solicitara al UserService, respondera un ResponseEntity con el HTTPSTATUS y
 * un body que contendra la informacion de la solicitud
 *
 * @author Dennis Pérez
 * @version 1.0
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/**
	 * Servicio que utilizará el controllador para hacer las solicitudes
	 */
	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Recibe en la solicitud una lista de usuarios para agregarla a la base de
	 * datos
	 * 
	 * @param users
	 * @return
	 *         <ul>
	 *         Solicitud incorrecta
	 *         <li>HttpStatus.BAD_REQUEST: Si el userService regresa una lista de
	 *         usuario nula o vacia</li>
	 *         <li>body: Contenido de la lista que devuelve userService, esta es
	 *         nula o vacia ya que no se agrego ningun usuario</li>
	 *         </ul>
	 * 
	 *         <ul>
	 *         Solicitud correcta
	 *         <li>HttpStatus.OK: Si el userService regresa la lista de usuario
	 *         creada</li>
	 *         <li>body: Contenido de la lista de usuarios creados que devuelve
	 *         userService</li>
	 *         </ul>
	 */
	/// CREATE
	@PostMapping("")
	public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
		List<User> allUser = userService.createUsers(users);
		if (allUser == null || allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(allUser);
	}

	/**
	 * Recibe una solicitud para devolver una lista de usuarios que se encuentran en
	 * la base de datos
	 * 
	 * @return
	 *         <ul>
	 *         Solicitud incorrecta
	 *         <li>HttpStatus.BAD_REQUEST: Si el userService regresa una lista de
	 *         usuario nula o vacia</li>
	 *         <li>body: Contenido de la lista que devuelve userService,para este
	 *         caso es vacia o nula ya que la base de datos está vacía</li>
	 *         </ul>
	 * 
	 *         <ul>
	 *         Solicitud correcta
	 *         <li>HttpStatus.OK: Si el userService regresa la lista de usuarios
	 *         encontrados</li>
	 *         <li>body: Contenido de la lista de usuarios encontrados en la base de
	 *         datos</li>
	 *         </ul>
	 */
	/// READ
	@GetMapping("") /// READ USER
	public ResponseEntity<List<User>> getUsers() {
		List<User> allUser = userService.getUsers();
		if (allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	/**
	 * Recibe una solicitud para devolver una lista de usuarios que se encuentran en
	 * la base de datos de acuerdo a un parámetro y un valor
	 * 
	 * @param parameter, value
	 * @return
	 *         <ul>
	 *         Solicitud incorrecta
	 *         <li>HttpStatus.BAD_REQUEST: Si el userService regresa una lista de
	 *         usuario nula o vacia</li>
	 *         <li>body: Contenido de la lista que devuelve userService,para este
	 *         caso es vacia o nula ya que no se encontraron usuarios de acuerdo al
	 *         parámetro y el valor solicitados</li>
	 *         </ul>
	 * 
	 *         <ul>
	 *         Solicitud correcta
	 *         <li>HttpStatus.OK: Si el userService regresa la lista de usuarios
	 *         encontrados</li>
	 *         <li>body: Contenido de la lista de usuarios encontrados en la base de
	 *         datos de acuerdo al parámetro y valor solicitados</li>
	 *         </ul>
	 */
	@GetMapping("/{parameter}/{value}")
	public ResponseEntity<List<User>> getUserByParam(@PathVariable String parameter, @PathVariable String value) {
		List<User> allUser = userService.getUsersByParam(parameter, value);
		if (allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	/**
	 * Recibe una solicitud para actualizar un usuario de acuerdo a su id
	 * 
	 * @param newUser, id
	 * @return
	 *         <ul>
	 *         Solicitud incorrecta
	 *         <li>HttpStatus.BAD_REQUEST: Si el userService regresa un usuario
	 *         vacío o nulo</li>
	 *         <li>body: Usuario devuelto por el userService, es nulo o vacío ya que
	 *         no se encontró un usuario con ese id o el newUser es nulo o
	 *         vacío</li>
	 *         </ul>
	 * 
	 *         <ul>
	 *         Solicitud correcta
	 *         <li>HttpStatus.OK: Si el userService regresa un usuario</li>
	 *         <li>body: Usuario devuelto por el userService, esto indica que fue
	 *         actualizado con exito</li>
	 *         </ul>
	 */
	/// UPDATE
	@PostMapping("/{id}")
	public ResponseEntity<User> updateUserById(@RequestBody User newUser, @PathVariable String id) {

		User User = userService.updateUserById(newUser, id);

		if (ObjectUtils.isEmpty(User) == true) {
			return ResponseEntity.badRequest().body(User);
		}
		return ResponseEntity.ok().body(User);
	}

	/**
	 * Recibe una solicitud para eliminar un usuario de acuerdo a su id
	 * 
	 * @param id
	 * @return
	 *         <ul>
	 *         Solicitud incorrecta
	 *         <li>HttpStatus.BAD_REQUEST: Si el userService regresa un usuario
	 *         vacío o nulo</li>
	 *         <li>body: Usuario devuelto por el userService, es nulo o vacío ya que
	 *         no se encontró un usuario con ese id</li>
	 *         </ul>
	 * 
	 *         <ul>
	 *         Solicitud correcta
	 *         <li>HttpStatus.OK: Si el userService regresa un usuario
	 *         eliminado</li>
	 *         <li>body: Usuario devuelto por el userService, esto indica que fue
	 *         eliminado con exito</li>
	 *         </ul>
	 */
	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable String id) {
		User User = userService.deleteUserById(id);

		if (ObjectUtils.isEmpty(User) == true) {
			return ResponseEntity.badRequest().body(User);
		}
		return ResponseEntity.ok().body(User);
	}

	/**
	 * Recibe una solicitud para eliminar un usuario de acuerdo a un parametro y un
	 * valor a encontrar
	 * 
	 * @param parameter, value
	 * @return
	 *         <ul>
	 *         Solicitud incorrecta
	 *         <li>HttpStatus.BAD_REQUEST: Si el userService regresa una lista de
	 *         usuarios vacío o nula</li>
	 *         <li>body: Lista de usuarios devuelta por el userService, es nulo o
	 *         vacío ya que no se encontraron usuarios con el valor y parametro
	 *         solicitado</li>
	 *         </ul>
	 * 
	 *         <ul>
	 *         Solicitud correcta
	 *         <li>HttpStatus.OK: Si el userService regresa una lista de usuarios
	 *         eliminados</li>
	 *         <li>body: Lista de usuarios devuelta por el userService, esto indica
	 *         que fueron eliminados con exito</li>
	 *         </ul>
	 */
	@DeleteMapping("/{parameter}/{value}")
	public ResponseEntity<List<User>> deleteUserByParam(@PathVariable String parameter, @PathVariable String value) {
		List<User> allUser = userService.deleteUserByParam(parameter, value);
		if (allUser.isEmpty() == true) {
			return ResponseEntity.badRequest().body(allUser);
		}
		return ResponseEntity.ok().body(allUser);
	}

	/**
	 * Cuando hay un error de tipo RuntimeException genera
	 * HTTPSTATUS.INTERNAL_SERVER_ERROR y arroja un mensaje con el error
	 * 
	 * @param RuntimeException e
	 * @return Devuelve el error con el mensaje "internal Server Error"
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final Exception handleAllExceptions(RuntimeException e) {
		LOGGER.error("Internal server error.", e);
		return e;
	}
}
