package models;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Products {
	
	//parametros da classe

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String titulo;
	private String descricao;
	private int paginas;
	@DateTimeFormat
	private Calendar dataLancamento;
	private String sumarioPath;

	@ElementCollection // Adiciona o preço como parte do produto, criando uma tabela extra só para// guardar essa informação,				
	private List<Preco> listPreco; // mas já o anotando. O preço precisa ser @Embeddable.

	//Metodos da classe
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;

	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public List<Preco> getListPreco() {
		return listPreco;
	}

	public void setListPreco(List<Preco> listPreco) {
		this.listPreco = listPreco;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Products other = (Products) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Products [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}

	public BigDecimal precoPara(TipoPreco tipoPreco) {
		return listPreco.stream().filter(preco -> preco.getTipoPreco().equals(tipoPreco))
				.findFirst().get().getValor();
	}
}