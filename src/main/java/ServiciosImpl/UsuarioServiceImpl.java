package ServiciosImpl;

import Exepciones.UsuarioNoEncontradoExecption;
import Modelos.Usuario;
import Repositorios.UsuarioRepository;
import Servicios.UsuarioService;
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
                .orElseThrow(() -> new UsuarioNoEncontradoExecption(nombre));

    }

    @Override
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoExecption(String.valueOf(id)));

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
            throw new UsuarioNoEncontradoExecption(String.valueOf(id));
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
}
