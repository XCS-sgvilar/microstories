package es.uvigo.esei.dgss.teamA.microstories.jsf;

import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named("login")
@RequestScoped
public class LoginMB {
	@Inject
	private Principal currentUser;

	@Inject
	private HttpServletRequest request;

	private String login;
	private String password;

	private boolean error = false;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isError() {
		return error;
	}

	public String doLogin() {
		try {
			request.login(this.getLogin(), this.getPassword());
			this.error = false;
			return redirectTo(this.getViewId());
		} catch (ServletException e) {
			this.error = true;
			return this.getViewId();
		}
	}

	public String doLogout() throws ServletException {
		request.logout();

		return redirectTo("/index.xhtml");
	}

	private String redirectTo(String url) {
		return url + "?faces-redirect=true";
	}

	private String getViewId() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}
	public Principal getCurrentUser() {
		return this.currentUser;
	}


}