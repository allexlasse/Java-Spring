package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import daos.ProdutoDAO;
import models.CarrinhoCompras;
import models.CarrinhoItem;
import models.Products;
import models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Long id, TipoPreco tipoPreco) {
		ModelAndView mav = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(id,tipoPreco);
		carrinho.add(carrinhoItem);
		return mav;
	}

	private CarrinhoItem criaItem(Long id, TipoPreco tipoPreco) {
		Products product = produtoDAO.find(id);
		CarrinhoItem carrinhoItem = new CarrinhoItem(product, tipoPreco);
		return carrinhoItem;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}
	
	
	@RequestMapping("/remover")
	public ModelAndView remover(Long id, TipoPreco tipoPreco) {
		carrinho.remover(id, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}
	
}
