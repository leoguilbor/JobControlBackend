/**
 *     This is a class from model Tier for Client entity.
 *     Copyright (C) 2018 Leandro Lima
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package br.com.leoguilbor.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.NumberFormat;

import br.com.leoguilbor.domain.Client;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
    private String address;
	@NotBlank
    private String city;
	@NotBlank
	@NumberFormat(pattern= "(##)#####-####")
    private String telephone;
    
	public ClientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.name = client.getName();
		this.address = client.getAddress();
		this.city = client.getCity();
		this.telephone = client.getTelephone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	} 
}