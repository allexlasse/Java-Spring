package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import daos.ProdutoDAO;
import infra.FileSaver;
import models.Products;
import models.TipoPreco;
import validation.ProductValidation;

@Controller
@RequestMapping("/products")
public class ProductsController {
	//Parametros
	
	@Autowired //indica que o objeto é um bean e queremos que retorne uma instancia por meio de uma injeção de dependencia
	private ProdutoDAO produtoDAO;
	@Autowired
	private FileSaver fileSaver;
	
	//Metodos
	
	//formulario
	@RequestMapping("/form")
	public ModelAndView form() {
		System.out.println("Testando produtos...");
		ModelAndView mav = new ModelAndView("products/form"); //Pega um model e joga na view. O caminho é passado pelo construtor
		mav.addObject("tipos", TipoPreco.values()); //Adicionaremos um objeto à view
		return mav; // Retorna o objeto mav
	}
	
	//GRAVAR!
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile sumario, @Valid Products products, BindingResult result, RedirectAttributes ra) { 
		//verifica a existencia de erros
		if(result.hasErrors())
			return form(); //Se houve erro ao cadastrar, ele retorna para o formulário de cadastro do produto
		
		//Definimos o caminho onde o arquivo será salvo
		String path = fileSaver.write("arquivos-sumario", sumario);
		products.setSumarioPath(path); //Seta o sumarioPath do objeto passado como parametro
		System.out.println(sumario.getOriginalFilename()); //imprime o nome de arquivos anexados ao livro
		
		//Salva o produto através do Data Access Object
		produtoDAO.save(products); 
		ra.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");		//Envia uma mensagem caso o produto teinha sido salvo com sucesso
		return new ModelAndView("redirect:products");//Se tudo deu certo, ele redireciona para a página de visualização dos produtos
	}
	
	@InitBinder
	public void intiBinder(WebDataBinder binder) {
		binder.addValidators(new ProductValidation());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Products> produtos = produtoDAO.listar();
		ModelAndView mav = new ModelAndView("products/lista");
		mav.addObject("products", produtos);
		return mav;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("products/detalhe");
		Products products = produtoDAO.find(id);
		mav.addObject("products", products);
		return mav;
	}
	
	
}
