package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import com.utn.jmg.inversiones.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//@Table
@Getter
@Setter
public class UsuarioEntity  implements java.io.Serializable {



     private Long idUsuario;
     private String nick;
     private String clave;
     private String nombre;
     private String apellido;
    

    public UsuarioEntity() {
    }

	
    public UsuarioEntity(String nick, String clave, String nombre, String apellido) {
        this.nick = nick;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
    }
  
   
    public Long getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNick() {
        return this.nick;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
   

    public boolean validarContrasenia(String pass) {
		return this.clave.equals(StringUtils.getEncryptedPassword(pass));
	}


}


