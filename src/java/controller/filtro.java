package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import entidade.Cliente;
import entidade.Administrador;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "filtro", urlPatterns = {"/*"})
public class filtro implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String uri = ((HttpServletRequest) request).getRequestURI();
        // aqui os servlets da parte pública que não precisa de login
        // e os CSS e JS necessários
        if ((uri.equals("/BancoZeta/"))
                || (uri.contains("/bootstrap"))
                || (uri.contains("/AutenticaController"))
                || (uri.contains("/formLoginAdmin"))
                || (uri.contains("/formLoginCliente"))
                || (uri.contains("/menu"))
                || (uri.contains("/index"))) {
            chain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);
        } else {
            // se a área necessita de login verifica se o usuário está na sessão - está logado
            Cliente cliente = (Cliente) ((HttpServletRequest) request).getSession().getAttribute("cliente");
            Administrador administrador = (Administrador) ((HttpServletRequest) request).getSession().getAttribute("administrador");
            
            if ((cliente != null) && (!((String) cliente.getNome()).isEmpty()) 
             || (administrador != null) && (!((String) administrador.getNome()).isEmpty())) {
                chain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);
            } else {
                ((HttpServletRequest) request).setAttribute("msgError", "É necessário fazer login");
                
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);

            }
        }

    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void destroy() {
    }

}
