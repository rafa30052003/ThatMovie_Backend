    package org.thatmovie.service;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.thatmovie.exception.RecordNotFoundException;
    import org.thatmovie.model.Member;
    import org.thatmovie.repository.MemberRepository;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class MemberService {
        @Autowired
        MemberRepository memberRepository;



        /**
         * Obtiene todos los usuarios
         *
         * @return La lista de usuarios
         */
        public List<Member> getAllUsers() {
            List<Member> users = memberRepository.findAll();
            return users;
        }

        /**
         * Obtiene un usuario por su id
         *
         * @param id El id del usuario
         * @return El usuario con el id dado
         */
        public Member getUserById(int id) {
            Optional<Member> user = memberRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new RecordNotFoundException("No se encontró un usuario con el id: " + id);
            }
        }

        /**
         * Crea o actualiza un usuario
         *
         * @param user El usuario a crear o actualizar
         * @return El usuario creado o actualizado
         */
        public Member createOrUpdateUser(Member user) {
            Member end;
            if (user.getId() > 0) { // Actualizar
                Optional<Member> result = memberRepository.findById(user.getId());
                if (result.isPresent()) {
                    Member fromDB = result.get();
                    fromDB.setName(user.getName());
                    fromDB.setSurname(user.getSurname());
                    fromDB.setUsername(user.getUsername());
                    fromDB.setEmail(user.getEmail());



                    fromDB.setAvatar(user.getAvatar());
                    fromDB.setBio(user.getBio());
                    end = memberRepository.save(fromDB);
                } else {
                    throw new RecordNotFoundException("No se encontró un usuario con el id: " + user.getId());
                }
            } else { // Insertar
                // Codificar y establecer la contraseña para un nuevo usuario


                end = memberRepository.save(user);
            }
            return end;
        }

        /**
         * Elimina un usuario por su id
         *
         * @param id El id del usuario a eliminar
         */
        public void deleteUser(int id) {
            Optional<Member> result = memberRepository.findById(id);
            if (result.isPresent()) {
                memberRepository.deleteById(id);
            } else {
                throw new RecordNotFoundException("No se encontró un usuario con el id: " + id);
            }
        }

        /**
         * Obtiene un usuario por su nombre de usuario
         *
         * @param username El nombre de usuario del usuario
         * @return El usuario con el nombre de usuario dado
         */
        public Member getUserByUsername(String username) {
            Optional<Member> result = memberRepository.findByUsername(username);
            if (result.isPresent()) {
                return result.get();
            } else {
                throw new RecordNotFoundException("No se encontró un usuario con el nombre de usuario: " + username);
            }
        }


    }