package edu.tienda.core.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteRenderController {

    @GetMapping(value = "/clientes-html",produces = MediaType.TEXT_HTML_VALUE)
    public String getClienteAsHtml(){

        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<body>");
        sb.append(" <div><h1>Cliente</h1>");
        sb.append("  <ul>");
        sb.append("    <li>Nombre: Martin Arana</li>");
        sb.append("    <li>UserName: CMA</li>");
        sb.append("  </ul>");
        sb.append(" </div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    @GetMapping(value = "/clientes-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String getClienteAsXml() {

        StringBuilder sb = new StringBuilder();

        sb.append("<xml>");
        sb.append("<cliente>");
        sb.append("    <nombre>Nombre: Martin Arana</nombre>");
        sb.append("    <username>UserName: CMA</username>");
        sb.append("</cliente>");
        sb.append("</xml>");

        return sb.toString();
    }

}
