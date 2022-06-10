package br.com.squadra.rodrigocosta.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

// Sinaliza ao Spring que essa classe é um Handler (manipulador) de exceções
// Ele extenderá da classe ResponseEntityExceptionHandler, que possui os métodos certos para cada tipo de exceção suportada.
@RestControllerAdvice
public class ValidacaoHandler extends ResponseEntityExceptionHandler {

    // Aqui daremos override (sobreescreveremos, ou seja, implementaremos) na handleMethodArgumentNotValid
    // Pois essa é a exceção que estoura quando as validações de campos como @NotNull são violadas.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Aqui estamos tentando resgatar a Default Message (mensagem que passamos como parâmetro nos métodos de validação)
        // o getDefaultMessage() pode retornar nulo, portanto, temos que usar um try/catch para tratarmos essa exceção
        String mensagem = "";
        try {
            mensagem = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();
        } catch (NullPointerException e) {
            mensagem = ex.getLocalizedMessage();
        }

        // Aqui resgatamos o status http disparado pela exceção, que nesse caso será 400
        int httpStatus = status.value();

        // Aqui criamos o objeto erro e em seguida retornamos ele via ResponseEntity.
        Erro erro = new Erro(mensagem, httpStatus);
        return ResponseEntity.badRequest().body(erro);
    }
}
