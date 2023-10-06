package br.com.api.apiengerb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.repositorio.ClienteRepositorio;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio cr;

    //
    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os clientes
    public Iterable<ClienteModelo> listar() {
        return cr.findAll();
    }

    // Metodo para cadastrar ou alterar Clientes
    public ResponseEntity<?> cadastrarAlterarCli(ClienteModelo cm, String acao) {

        if (cm.getNomeUser().equals("")) {
            rm.setMensagem("O nome do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (cm.getEmailCliente().equals("")) {
            rm.setMensagem("O email do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (cm.getLogin().equals("")) {
            rm.setMensagem("O nome de usuario do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (cm.getSenhaUser().equals("")) {
            rm.setMensagem("a senha do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (cm.getPastaCliente().equals("")) {
            rm.setMensagem("a pasta do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<ClienteModelo>(cr.save(cm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ClienteModelo>(cr.save(cm), HttpStatus.OK);
            }
        }

    }

    // Metodo para remove Cliente
    public ResponseEntity<RespostaModelo> remover(int idCliente) {

        cr.deleteById(idCliente);

        rm.setMensagem("Cliente removido com Sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }
}
