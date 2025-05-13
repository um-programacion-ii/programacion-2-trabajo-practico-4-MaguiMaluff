package com.example.TP_4.ServiciosImpl;

import com.example.TP_4.Exepciones.UsuarioNoEncontradoException;
import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Repositorios.UsuarioRepository;
import com.example.TP_4.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario buscarPorNombre(String nombre){
        return usuarioRepository.findByName(nombre)
                .orElseThrow(() -> new UsuarioNoEncontradoException(nombre));

    }

    @Override
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(String.valueOf(id)));

    }

    @Override
    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario){
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException(String.valueOf(id));
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
}
