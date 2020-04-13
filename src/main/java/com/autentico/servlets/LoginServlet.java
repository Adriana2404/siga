package com.autentico.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.autentico.beans.LoginBean;
import com.autentico.controllers.UsuarioController;
import java.util.List;
import javax.ejb.EJB;

import com.autentico.entities.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

        @EJB
        private com.autentico.beans.UsuarioFacade ejbFacade;
        
	public LoginServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		LoginBean loginBean = new LoginBean();

		loginBean.setUsuario(userName);
		loginBean.setClave(password);
		try
		{
			String[] userValidate = authenticateUser(loginBean);
			System.out.println(userValidate);
			if(!userValidate.equals("Invalid"))
			{
				HttpSession session = request.getSession(); //Creating a session
				session.setMaxInactiveInterval(10*60);
				session.setAttribute("user", userName);
				session.setAttribute("role", userValidate[0]);
                                session.setAttribute("iduser", userValidate[1]);
				if(request.getAttribute("errMessage") != null) {
					session.removeAttribute("errMessage");
				}
				response.sendRedirect(request.getContextPath() + "/dashboard.xhtml");
                                System.out.println(request.getContextPath() + "/dashboard.xhtml");
                                
                                FacesMessage message = new FacesMessage("¡Inicio de Sesión Exitoso!", "El usuario con el que ingresaste es: " + userName);
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else
			{
				System.out.println("Error message = "+userValidate[0]);
				request.setAttribute("errMessage", userValidate[0]);
				response.sendRedirect(request.getContextPath() + "/login.xhtml");
                                System.out.println(request.getContextPath() + "/login.xhtml");
                                
                                FacesMessage message = new FacesMessage("Credenciales Incorrectas", "Por favor verifique.");
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
                                
			}
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	} //End of doPost()
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
        
        public String[] authenticateUser(LoginBean loginBean)
        {
            String userName = loginBean.getUsuario();
            String password = getMD5(loginBean.getClave());

            String userNameDB = "";
            String passwordDB = "";
            String[] roleDB = new String[5];

            List<Usuario> usersList = ejbFacade.findAll();
            try
            {
                
                for (Usuario usuario : usersList) {
                    userNameDB = usuario.getEmail();
                    passwordDB = usuario.getClave();
                    roleDB[0] = usuario.getRol().getNombreRol();
                    roleDB[1] = usuario.getIdusuario().toString();
                    if(userName.equals(userNameDB) && password.equals(passwordDB))
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
}