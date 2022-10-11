package br.com.springboot.curso_jdev_treinamento.Controller;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingsController {

    @Autowired /*IC/CD ou CDI - Injeção de dependência*/
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public java.lang.String greetingText(@PathVariable java.lang.String name){

        Usuario usuario = new Usuario();
        usuario.setNome(name);

        usuarioRepository.save(usuario); /*Grava no banco de dados*/

        return "Ola mundo " + name;

    }

    @GetMapping(value = "listatodos") /*Primeiro método de API*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario() {

        List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa a consulta no banco de dados */

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*Retorna a lista em JSON*/

    }

    @PostMapping(value = "salvar") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /*Recebe os dados para salvar*/

        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

    }

    @PutMapping(value = "atualizar") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity atualizar(@RequestBody Usuario usuario) {         /*Recebe os dados para atualizar*/


//        if (usuario.getId() == Long.parseLong(null)) {
//            return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
//        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);

    }

    @DeleteMapping(value = "delete") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete(@RequestParam Long iduser) { /*Recebe os dados para deletar*/

        usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);

    }

    @GetMapping(value = "buscaruserid") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) { /*Recebe os dados para consultar*/

        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

    }

    @GetMapping(value = "buscarPorNome") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) { /*Recebe os dados para consultar*/

        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

    }


}
