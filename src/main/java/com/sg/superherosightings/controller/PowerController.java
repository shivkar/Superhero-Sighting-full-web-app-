/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.PowerDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.entity.Powers;
import com.sg.superherosightings.entity.Sightings;
import com.sg.superherosightings.entity.Superheros;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author SHIVALI
 */

@Controller
public class PowerController {
        @Autowired
    SuperheroDao superheroDao;

    @Autowired
    PowerDao powerDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationDao organizationDao;

 @GetMapping("powers")
    public String displayPowers(Model model) {
        List<Powers> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }
    
    @PostMapping("addPower")
        public String addSuperhero(HttpServletRequest request) {
       
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Powers power = new Powers();
        power.setName(name);
        power.setDescription(description);
        powerDao.addPower(power); 
        return "redirect:/powers";
    }
        
        
     @GetMapping("detailPower")
    public String detailPower(Integer id, Model model) {
        Powers powers = powerDao.getPowerById(id);
        model.addAttribute("power", powers);
        return "detailPower";
    }
    
    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
         int id = Integer.parseInt(request.getParameter("id"));
        powerDao.deletePowerById(id);
        return "redirect:/powers";
    }
    
    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
       Powers power = powerDao.getPowerById(id);
        model.addAttribute("power", power);
        return "editPower";
    }
    
    
    
    @PostMapping("editPower")
    public String performEditPower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Powers power = powerDao.getPowerById(id);
        
        power.setName(request.getParameter("name"));
        power.setDescription(request.getParameter("description"));
        
        
        powerDao.updatePower(power);
        
        return "redirect:/powers";
    }
}
