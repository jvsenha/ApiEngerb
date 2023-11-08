package br.com.api.apiengerb.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.modelo.UserModelo;
import br.com.api.apiengerb.repositorio.ClienteRepositorio;
import br.com.api.apiengerb.repositorio.UserRepositorio;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio cr;

    @Autowired
    private UserRepositorio ur;

    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os Clientes
    public List<ClienteModelo> listar() {
        return cr.findAll();
    }

    // Método para cadastrar ou alterar Clientes
    public ResponseEntity<?> cadastrar(ClienteModelo cm) {
        return new ResponseEntity<ClienteModelo>(cr.save(cm), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> alterar(ClienteModelo cm, Integer idUser) {
        ClienteModelo cliente = this.carregarCLiente(idUser);
        if (cliente != null) {
            cliente.setLogin(cm.getLogin());
            cliente.setEmailCliente(cm.getEmailCliente());
            cliente.setNomeUser(cm.getNomeUser());
            cliente.setPastaCliente(cm.getPastaCliente());
        }
        return new ResponseEntity<ClienteModelo>(cr.save(cliente), HttpStatus.OK);
    }

    // Método para remover Clientes
    public ResponseEntity<RespostaModelo> remover(int idCliente) {

        cr.deleteById(idCliente);

        rm.setMessage("Cliente removido com Sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }

    public ClienteModelo carregarCLiente(Integer idUser) {
        return ur.findById(idUser)
                .filter(usuario -> usuario instanceof ClienteModelo)
                .map(usuario -> (ClienteModelo) usuario)
                .orElse(null);
    }

    public List<ClienteModelo> listarClientesAtivos() {
        List<UserModelo> usuariosAtivos = ur.findByIsEnabled(true);

        List<ClienteModelo> clientesAtivos = usuariosAtivos.stream()
                .filter(usuario -> usuario instanceof ClienteModelo)
                .map(usuario -> (ClienteModelo) usuario)
                .collect(Collectors.toList());

        return clientesAtivos;
    }

    public List<ClienteModelo> listarClientesInativos() {
        List<UserModelo> usuariosAtivos = ur.findByIsEnabled(false);

        List<ClienteModelo> clientesAtivos = usuariosAtivos.stream()
                .filter(usuario -> usuario instanceof ClienteModelo)
                .map(usuario -> (ClienteModelo) usuario)
                .collect(Collectors.toList());

        return clientesAtivos;
    }

    public ResponseEntity<?> solicitacaoReset(String Login) {
        ClienteModelo cliente = cr.findByLogin(Login);
        if (cliente != null) {
            cliente.setReset(true);
            return new ResponseEntity<ClienteModelo>(cr.save(cliente), HttpStatus.OK);
        } else {
            rm.setMessage("Login não encontrado");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<?> resetPassword(String Login) {
        ClienteModelo cliente = cr.findByLogin(Login);
        if (cliente != null) {
            cliente.setReset(false);
            return new ResponseEntity<ClienteModelo>(cr.save(cliente), HttpStatus.OK);
        } else {
            rm.setMessage("Login não encontrado");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    public List<ClienteModelo> listarReset() {
        return cr.findByReset(true);
    }
}
