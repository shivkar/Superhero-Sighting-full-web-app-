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
import com.sg.superherosightings.entity.Locations;
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
public class LocationController {

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

    @GetMapping("locations")
    public String displayOrganization(Model model) {
        List<Locations> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {

        String locname = request.getParameter("name");
        String locdescription = request.getParameter("description");
        String locstreet = request.getParameter("street");
        String loccity = request.getParameter("city");
        String locstate = request.getParameter("state");
        String loczip = request.getParameter("zip");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        Locations location = new Locations();
        location.setLocname(locname);
        location.setLocdescription(locdescription);
        location.setLocstreet(locstreet);
        location.setLoccity(loccity);
        location.setLocstate(locstate);
        location.setLoczip(loczip);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        locationDao.addLocation(location);
        return "redirect:/locations";
    }
    
     @GetMapping("detailLocation")
    public String detailLocation(Integer id, Model model) {
        Locations locations = locationDao.getLocationById(id);
        model.addAttribute("location", locations);
        return "detailLocation";
    }
    

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        locationDao.deleteLocationById(id);
        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
         int id = Integer.parseInt(request.getParameter("id"));
        Locations location = locationDao.getLocationById(id);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Locations location = locationDao.getLocationById(id);
        location.setLocname(request.getParameter("name"));
        location.setLocdescription(request.getParameter("description"));
         location.setLocstreet(request.getParameter("street"));
          location.setLoccity(request.getParameter("city"));
           location.setLocstate(request.getParameter("state"));
            location.setLoczip(request.getParameter("zip"));
             location.setLatitude(request.getParameter("latitude"));
              location.setLongitude(request.getParameter("longitude"));
        locationDao.updateLocation(location);
        return "redirect:/locations";
    }

}
