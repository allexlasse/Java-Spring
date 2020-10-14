package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet(); //Como o map é comporsto por chaves (CarrinhoItem) e a quantidade (Integer), usaremos a Collection, retornando
							   //um set de chaves, ou seja, apenas os items, sem sua quantidade
	}
	
	public void add(CarrinhoItem carrinhoItem) {
		itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);
	}

	public int getQuantidade(CarrinhoItem carrinhoItem) {
		if (!itens.containsKey(carrinhoItem)) {
			itens.put(carrinhoItem, 0);
		}
		return itens.get(carrinhoItem);
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(CarrinhoItem carrinhoItem) {
		return carrinhoItem.getTotal(getQuantidade(carrinhoItem));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remover(Long id, TipoPreco tipoPreco) {
		Products products = new Products();
		products.setId(id);
		itens.remove(new CarrinhoItem(products, tipoPreco));		
	}

}
