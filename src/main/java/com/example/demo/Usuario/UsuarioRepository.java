package com.example.demo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository implements UsuarioDAO {

    private List<Usuario> lista = new ArrayList<>();
    private int contador = 1;

    public UsuarioRepository() {

        Usuario admin = new Usuario( "admin@gmail.com" , "123", "ADMIN", null);

        save(admin);

    }

    @Override
    public void save(Usuario usuario){
        usuario.setId(contador ++);
        lista.add(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return lista;
    }

    @Override
    public Usuario findById(int id) {
        return lista.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Usuario usuario){
        for(int i = 0; i < lista.size() ; i++) {
            if (lista.get(i).getId() == usuario.getId()){
                lista.set(i, usuario);
                return;
            }
        }
    }

    @Override
    public void delete (int id) {
        lista.removeIf(u -> u.getId() == id);
    }

    @Override
    public List<Usuario> findByDni(String dni) {
        return lista.stream()
                .filter(u -> u.getDoctor() != null)
                .filter(u -> u.getDoctor().getDni().equals(dni))
                .collect(Collectors.toList());
    }

    @Override
    public Usuario findByCorreo(String correo) {
         return lista.stream()
                .filter(u -> u.getCorreo().equals(correo))
                .findFirst()
                .orElse(null);
    }
    
}
