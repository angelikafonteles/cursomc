package com.angelikafonteles.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelikafonteles.cursomc.domain.Categoria;
import com.angelikafonteles.cursomc.domain.Cidade;
import com.angelikafonteles.cursomc.domain.Cliente;
import com.angelikafonteles.cursomc.domain.Endereco;
import com.angelikafonteles.cursomc.domain.Estado;
import com.angelikafonteles.cursomc.domain.ItemPedido;
import com.angelikafonteles.cursomc.domain.Pagamento;
import com.angelikafonteles.cursomc.domain.PagamentoComBoleto;
import com.angelikafonteles.cursomc.domain.PagamentoComCartao;
import com.angelikafonteles.cursomc.domain.Pedido;
import com.angelikafonteles.cursomc.domain.Produto;
import com.angelikafonteles.cursomc.domain.enums.EstadoPagamento;
import com.angelikafonteles.cursomc.domain.enums.TipoCliente;
import com.angelikafonteles.cursomc.repositories.CategoriaRepository;
import com.angelikafonteles.cursomc.repositories.CidadeRepository;
import com.angelikafonteles.cursomc.repositories.ClienteRepository;
import com.angelikafonteles.cursomc.repositories.EnderecoRepository;
import com.angelikafonteles.cursomc.repositories.EstadoRepository;
import com.angelikafonteles.cursomc.repositories.ItemPedidoRepository;
import com.angelikafonteles.cursomc.repositories.PagamentoRepository;
import com.angelikafonteles.cursomc.repositories.PedidoRepository;
import com.angelikafonteles.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Toalha", 50.00);
		Produto p5 = new Produto(null, "Colcha", 200.00);
		Produto p6 = new Produto(null, "TV", 1200.00);
		Produto p7 = new Produto(null, "Roçadeira", 800.00);
		Produto p8 = new Produto(null, "Abajour", 100.00);
		Produto p9 = new Produto(null, "Pendente", 180.00);
		Produto p10 = new Produto(null, "Shampoo", 80.00);
		Produto p11 = new Produto(null, "Mesa de Escritório", 300.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p11));
		cat3.getProdutos().addAll(Arrays.asList(p4, p5));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p6));
		cat5.getProdutos().addAll(Arrays.asList(p7));
		cat6.getProdutos().addAll(Arrays.asList(p8, p9));
		cat7.getProdutos().addAll(Arrays.asList(p10));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat4));
		p7.getCategorias().addAll(Arrays.asList(cat5));
		p8.getCategorias().addAll(Arrays.asList(cat6));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat7));
		p11.getCategorias().addAll(Arrays.asList(cat2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "CAmpinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "angelikatf@gmail.com", "12345678911", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("12121212", "45454545"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 103", "Jardim", "52000000", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "53000000", c2, cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
