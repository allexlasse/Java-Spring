package models;

import java.math.BigDecimal;

public class CarrinhoItem {

	private Products products;
	private TipoPreco tipoPreco;

	public CarrinhoItem(Products products, TipoPreco tipoPreco) {
		this.products = products;
		this.tipoPreco = tipoPreco;

	}
	
	public BigDecimal getPreco() {
		return products.precoPara(this.tipoPreco);
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (tipoPreco != other.tipoPreco)
			return false;
		return true;
	}

	public BigDecimal getTotal(int quantidade) {
		return this.getPreco().multiply(new BigDecimal(quantidade));
	}
	
	

}
