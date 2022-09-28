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
import com.sg.superherosightings.entity.Organizations;
import com.sg.superherosightings.entity.Powers;
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
public class OrganizationController {

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

    @GetMapping("organizations")
    public String displayOrganization(Model model) {
        List<Organizations> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String hotline = request.getParameter("hotline");

        Organizations organization = new Organizations();
        organization.setName(name);
        organization.setDescription(description);
        organization.setHotline(hotline);
        organizationDao.addOrganization(organization);
        return "redirect:/organizations";
    }
    
    @GetMapping("detailOrganization")
    public String detailOrganization(Integer id, Model model) {
        Organizations organizations = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organizations);
        return "detailOrganization";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        organizationDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organizations organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }
    
     @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request) {
         int id = Integer.parseInt(request.getParameter("id"));
          Organizations organization  = organizationDao.getOrganizationById(id);
         organization.setName(request.getParameter("name"));
        organization.setDescription(request.getParameter("description"));
         organization.setHotline(request.getParameter("hotline"));
        organizationDao.updateOrganization(organization);
        return "redirect:/organizations";
    }
}
