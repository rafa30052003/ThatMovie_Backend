package org.thatmovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thatmovie.model.Member;
import org.thatmovie.service.MemberService;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * Obtiene todos los usuarios
     *
     * @return Lista de usuarios
     */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(){
        List<Member> list = memberService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    /**
     * Obtiene un usuario por su id
     *
     * @param id El id del usuario
     * @return El usuario con el id dado
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable("id") int id){
        Member user = memberService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Obtiene un usuario por su nombre de usuario
     *
     * @param username El nombre de usuario del usuario
     * @return El usuario con el nombre de usuario dado
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<Member> getUserByUsername(@PathVariable("username") String username){
        Member user = memberService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    /**
     * Crea o actualiza un usuario
     *
     * @param member El usuario a crear o actualizar
     * @return El usuario creado o actualizado
     */
    @PostMapping
    public ResponseEntity<Member> createOrUpdateMember(@RequestBody Member member){
        Member end = memberService.createOrUpdateUser(member);
        return ResponseEntity.ok(end);
    }

    /**
     * Elimina un usuario por su id
     *
     * @param id El id del usuario a eliminar
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
        memberService.deleteUser(id);
    }





}