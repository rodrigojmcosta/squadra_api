package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Endereco;
import br.com.squadra.rodrigocosta.model.Pessoa;
import br.com.squadra.rodrigocosta.repository.*;
import br.com.squadra.rodrigocosta.request.EnderecoRequest;
import br.com.squadra.rodrigocosta.request.PessoaRequest;
import br.com.squadra.rodrigocosta.response.EnderecoResponse;
import br.com.squadra.rodrigocosta.response.PessoaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaCustomRepository pessoaCustomRepository;

    @Autowired
    BairroRepository bairroRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoCustomRepository enderecoCustomRepository;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    MunicipioRepository municipioRepository;

    @Autowired
    UfRepository ufRepository;


    public void salvaPessoaComEnderecos(PessoaRequest pessoaRequest) {
        Pessoa pessoaSeraSalva = PessoaRequest.toModel(pessoaRequest);
        Pessoa pessoaSalva = pessoaRepository.save(pessoaSeraSalva);
        List<Endereco> listaEnderecosPessoaSalva = new ArrayList<>();
        for (EnderecoRequest enderecoRequest : pessoaRequest.getEnderecos()) {
            listaEnderecosPessoaSalva.add(EnderecoRequest.toModel(enderecoRequest, pessoaSalva, getBairro(enderecoRequest.getCodigoBairro())));
        }
        enderecoRepository.saveAll(listaEnderecosPessoaSalva);
    }

    private Bairro getBairro(Long codigoBairro) {
        Optional<Bairro> bairroBuscado = bairroRepository.findById(codigoBairro);
        if (bairroBuscado.isPresent()) {
            return bairroBuscado.get();
        } else {
            throw new NullPointerException("O código de bairro referenciado não corresponde a nenhum bairro" + " cadastrado no banco de dados!");
        }
    }

    public List<PessoaResponse> listaPessoas() {
        List<Pessoa> listaPessoas = pessoaRepository.findAll();
        List<PessoaResponse> listaPessoaResponse = new ArrayList<>();
        for (Pessoa pessoa : listaPessoas) {
            listaPessoaResponse.add(PessoaResponse.toResponse(pessoa, new ArrayList<>()));
        }
        return listaPessoaResponse;
    }

    public List<PessoaResponse> listaPessoasComParametro(Long codigoPessoa, Long idade, String login, String nome,
                                                         String senha, String sobrenome, Long status) {
        List<Pessoa> listaPessoas = pessoaCustomRepository.busca(codigoPessoa, nome, sobrenome, idade, login, senha, status);
        if (codigoPessoa != null && idade == null && login == null && nome == null &&
                senha == null && sobrenome == null && status == null) {
            List<Endereco> listaEnderecosPessoa = enderecoCustomRepository.busca(codigoPessoa);
            List<EnderecoResponse> listaEnderecoResponse = new ArrayList<>();
            for (Endereco endereco : listaEnderecosPessoa) {
                listaEnderecoResponse.add(EnderecoResponse.toResponse(endereco));
            }
            return toListPessoaResponse(listaPessoas, listaEnderecoResponse);
        } else {
            return toListPessoaResponse(listaPessoas, new ArrayList<>());
        }
    }

    private List<PessoaResponse> toListPessoaResponse(List<Pessoa> listaPessoas, List<EnderecoResponse> listaEnderecoResponse) {
        List<PessoaResponse> listaPessoaResponse = new ArrayList<>();
        for (Pessoa pessoa : listaPessoas) {
            listaPessoaResponse.add(PessoaResponse.toResponse(pessoa, listaEnderecoResponse));
        }
        return listaPessoaResponse;
    }


    public List<PessoaResponse> atualizaPessoa(PessoaRequest pessoaRequest) throws Exception {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(pessoaRequest.getCodigoPessoa());
        if (pessoaSalva.isPresent()) {
            atualizaDadosPessoa(pessoaSalva.get(), pessoaRequest);
            for (EnderecoRequest enderecoRequest : pessoaRequest.getEnderecos()) {
                Optional<Bairro> bairroSalvo = bairroRepository.findById(enderecoRequest.getCodigoBairro());
                if (bairroSalvo.isPresent()) {
                    if (enderecoRequest.getCodigoEndereco() == null || enderecoRequest.getCodigoEndereco() == 0) {
                        enderecoService.salvaEndereco(enderecoRequest, pessoaSalva.get(), bairroSalvo.get());
                    } else {
                        enderecoService.atualizaEndereco(enderecoRequest, bairroSalvo.get());
                    }
                }
            }
            List<Long> enderecosRecebidos = new ArrayList<>();
            for (EnderecoRequest enderecoRequest : pessoaRequest.getEnderecos()) {
                if (enderecoRequest.getCodigoEndereco() != null) {
                    enderecosRecebidos.add(enderecoRequest.getCodigoEndereco());
                }
            }
            enderecoCustomRepository.excluiEnderecosNaoRecebidos(enderecosRecebidos);
            return PessoaResponse.toPutResponse(pessoaRepository.findAll());
        } else {
            throw new Exception();
        }

    }

    private void atualizaDadosPessoa(Pessoa pessoaSeraAtualizada, PessoaRequest pessoaRequest) {
        pessoaSeraAtualizada.setNome(pessoaRequest.getNome());
        pessoaSeraAtualizada.setSobrenome(pessoaRequest.getSobrenome());
        pessoaSeraAtualizada.setIdade(pessoaRequest.getIdade());
        pessoaSeraAtualizada.setLogin(pessoaRequest.getLogin());
        pessoaSeraAtualizada.setSenha(pessoaRequest.getSenha());
        pessoaSeraAtualizada.setStatus(pessoaRequest.getStatus());
        pessoaRepository.save(pessoaSeraAtualizada);
    }
}
