package com.autentico.controllers;

import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import com.autentico.beans.LoginBean;
import com.autentico.entities.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;

public class AuthController {
	private UsuarioController usuariocontroller = new UsuarioController();
        
        @EJB
        private com.autentico.beans.UsuarioFacade ejbFacade;
	
	public String[] authenticateUser(LoginBean loginBean)
        {
            String userName = loginBean.getUsuario();
            String password = loginBean.getClave();

            List<Usuario> usersList = null;

            String userNameDB = "";
            String passwordDB = "";
            String[] roleDB = new String[5];

            try
            {
                usersList = ejbFacade.findAll();
                for (Usuario usuario : usersList) {
                    userNameDB = usuario.getEmail();
                    passwordDB = usuario.getClave();
                    roleDB[0] = usuario.getRol().getNombreRol();
                    roleDB[1] = usuario.getIdusuario().toString();
                    String passwordM = getMD5(passwordDB);
                    if(userName.equals(userNameDB) && password.equals(passwordM))
                        return roleDB;
                }
            }
            catch(HibernateException e)
            {
                e.printStackTrace();
            }
            roleDB[0] = "Invalid";
            roleDB[1] = "Invalid";
            return roleDB;
	}
        
        public String getMD5(String input) {
            try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
            }
            return hashtext;
            }
            catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
            }
	}
}
