package com.objetosdos.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.objetosdos.spring.helper.ViewRouteHelper;
import com.objetosdos.spring.models.SucursalModel;
import com.objetosdos.spring.services.ISucursalService;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	private ISucursalService sucursalServices;
	
	
	//public final static String LOCAL_INDEX="local/index";
	 @GetMapping("")
	    public ModelAndView index(){
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_INDEX);
	        mAV.addObject("sucursal", sucursalServices.getAll());
	        return mAV;
	    }
	 
	 //public final static String LOCAL_NEW="local/new";
	  @GetMapping("/new")
	    public ModelAndView newSucursal(){
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_NEW);
	        mAV.addObject("sucursal", new SucursalModel());
	        return mAV;
	    }
	  
	    @PostMapping("/save")
	    public RedirectView saveSucursal(@ModelAttribute("sucursal") SucursalModel sucursalModel ){
	    	sucursalServices.insertOrUpdate(sucursalModel);
	        return new RedirectView(ViewRouteHelper.LOCAL_ROOT);
	    }
	  
	    //public final static String LOCAL_ID="local/view";
	   @GetMapping("/{id}")
	    public ModelAndView sucursal(@PathVariable("id") int id){
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_ID);
	        mAV.addObject("sucursal", sucursalServices.findById(id));
	        return mAV;
	    }
	   
	   
	   @GetMapping("/delete/{id}")
		  public RedirectView delete(@PathVariable("id") int id) {
		
		   sucursalServices.delete(id);
		   return new RedirectView(ViewRouteHelper.LOCAL_ROOT);
	   }
	   
	 
	   
}
