package ServiciosImpl;

import Exepciones.PrestamoNoEncontradoException;
import Modelos.Prestamo;
import Modelos.Usuario;
import Repositorios.PrestamoRepository;
import Servicios.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {
    @Autowired
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo buscarPorUsuario(Usuario usuario) {
        return prestamoRepository.findByUser(usuario)
                .orElseThrow(() -> new PrestamoNoEncontradoException(usuario.getNombre()));
    }

    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException(String.valueOf(id)));
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }

    @Override
    public Prestamo actualizar(Long id, Prestamo prestamo) {
        if (!prestamoRepository.existsById(id)) {
            throw new PrestamoNoEncontradoException(String.valueOf(id));
        }
        prestamo.setId(id);
        return prestamoRepository.save(prestamo);
    }
}
