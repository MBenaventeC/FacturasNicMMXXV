package com.NicFacturas.NicFacturasDemo.controllers;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.NicFacturas.NicFacturasDemo.services.AppService;

@Controller
public class AppController {
    private final AppService appService;
    public AppController(AppService appService) {
        this.appService = appService;
    }
    
    @GetMapping("/")
    public String indexRoute(Model model) {
        List<Map<String, String>> modelData = null;
        model.addAttribute("data", modelData);
        System.out.println("////////////////////////////////////////////");
        return "index";
    }

    @PostMapping("/guardarDatos")
    public String guardarDatos(
        @RequestParam("name-input") String nombre,
        @RequestParam("rut-input") String rut,
        @RequestParam("dir-input") String direccion,
        @RequestParam("comuna-input") String comuna,
        @RequestParam("ciudad-input") String ciudad,
        @RequestParam("telefono-input") String telefono,
        @RequestParam("guia-input") String guia,
        @RequestParam("giro-input") String giro,
        @RequestParam("emit-input") String emitido_por,
        @RequestParam("CDC-input") String CDC,
        @RequestParam("CDV-input") String CDV,
        @RequestParam("item-input") String item,
        @RequestParam("DE-input") String DE,
        @RequestParam("venc-input") String vencimiento,
        @RequestParam("cant-input") String cantidad,
        @RequestParam("tipo-input") String tipo,
        @RequestParam("detalle-input") String detalle,
        @RequestParam("desc-input") String descripcion,
        @RequestParam("precioU-input") String precio_unitario,
        org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes
    ) {
        System.out.println("////////////////////////////////////////////");
        System.out.println("Nombre: " + nombre);
        System.out.println("Rut: " + rut);
        System.out.println("Dirección: " + direccion);
        System.out.println("Comuna: " + comuna);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Guía: " + guia);
        System.out.println("Giro: " + giro);
        System.out.println("Emitido por: " + emitido_por);
        System.out.println("CDC: " + CDC);
        System.out.println("CDV: " + CDV);
        System.out.println("Item: " + item);
        System.out.println("DE: " + DE);
        System.out.println("Vencimiento: " + vencimiento);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Tipo: " + tipo);
        System.out.println("Detalle: " + detalle);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio unitario: " + precio_unitario);
        System.out.println("////////////////////////////////////////////");

        redirectAttributes.addFlashAttribute("nombre", nombre);
        redirectAttributes.addFlashAttribute("rut", rut);
        redirectAttributes.addFlashAttribute("direccion", direccion);
        redirectAttributes.addFlashAttribute("comuna", comuna);
        redirectAttributes.addFlashAttribute("ciudad", ciudad);
        redirectAttributes.addFlashAttribute("telefono", telefono);
        redirectAttributes.addFlashAttribute("guia", guia);
        redirectAttributes.addFlashAttribute("giro", giro);
        redirectAttributes.addFlashAttribute("emit", emitido_por);
        redirectAttributes.addFlashAttribute("cdc", CDC);
        redirectAttributes.addFlashAttribute("cdv", CDV);
        redirectAttributes.addFlashAttribute("item", item);
        redirectAttributes.addFlashAttribute("DE", DE);
        redirectAttributes.addFlashAttribute("venc", vencimiento);
        redirectAttributes.addFlashAttribute("cant", cantidad);
        redirectAttributes.addFlashAttribute("tipo", tipo);
        redirectAttributes.addFlashAttribute("detalle", detalle);
        redirectAttributes.addFlashAttribute("desc", descripcion);
        redirectAttributes.addFlashAttribute("precioU", precio_unitario);

        return "redirect:/vistafinal";
    }

}
