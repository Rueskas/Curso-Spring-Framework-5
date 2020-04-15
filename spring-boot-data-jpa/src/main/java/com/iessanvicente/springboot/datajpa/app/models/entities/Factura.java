package com.iessanvicente.springboot.datajpa.app.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="facturas")
public class Factura implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="La descripcion no puede estar vacia")
	private String descripcion;
	private String observacion;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="factura")
	private List<ItemFactura> items = new ArrayList<>();
	
	public void addItem(ItemFactura item) {
		item.setFactura(this);
		items.add(item);
	}
	
	public Double getTotal() {
		return items.stream().mapToDouble(ItemFactura::getTotal).sum();
	}
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -726303074879851005L;

}
