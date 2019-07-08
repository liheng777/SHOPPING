package com.hqyj.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hqyj.bean.SaleDrug;
import com.hqyj.service.SaleDrugService;

@Controller
@RequestMapping(value="/saleDrug")
public class SaleDrugAction {
	@Autowired
	private SaleDrugService saleDrugService;

	public SaleDrugService getSaleDrugService() {
		return saleDrugService;
	}

	public void setSaleDrugService(SaleDrugService saleDrugService) {
		this.saleDrugService = saleDrugService;
	}
	@RequestMapping("/saleDrugList.do")
	public String stockDrugList(Model model){
		ArrayList<SaleDrug> list = saleDrugService.queryAll();
		model.addAttribute("list", list);
		return "saleDrugList";
	}
	@RequestMapping("/preAdd")
	public String preAdd(){
		return "saleDrugAdd";
	}
	@RequestMapping("/saleDrugAdd")
	public String add(SaleDrug s,Model model){
		int result = saleDrugService.add(s);
		if(result==1){
			return "redirect:/saleDrug/saleDrugList.do";
		}else{
			if(result==-1){
				model.addAttribute("msg","所售药品在库存中不存在");
			}else if(result==0){
				model.addAttribute("msg","库存存货不够，所售药品的数量大于库存中的数量");
			}else if(result==-2){
				model.addAttribute("msg","所售药品增加失败，系统错误");
			}
			return "saleDrugAdd";
		}
	}
	@RequestMapping("/preEdit")
	public ModelAndView preEdit(Integer id){
		SaleDrug saleDrug = saleDrugService.queryById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("saleDrug", saleDrug);
		mv.setViewName("saleDrugEdit");
		return mv;
	}
	@RequestMapping(value="/edit.do")
	public String edit(SaleDrug s,Model model){
		int result = saleDrugService.edit(s);
		if(result==1){
			return "redirect:/saleDrug/saleDrugList.do";
		}else{
			if(result==-1){
				model.addAttribute("msg","所售药品在库存中不存在");
			}else if(result==0){
				model.addAttribute("msg","所售药品的数量大于库存中的数量");
			}else if(result==-2){
				model.addAttribute("msg","所售药品修改失败，系统错误");
			}
//			return "redirect:/saleDrug/saleDrugEdit.do";
			return "saleDrugEdit";
		}
	}
//	@RequestMapping(value="/saleDrugEdit.do")
//	public String saleDrugEdit(){
//		return "saleDrugEdit";
//	}
	@RequestMapping("/delete.do")
	public String delete(Integer id){
		saleDrugService.delete(id);
		return "redirect:/saleDrug/saleDrugList.do";
	}
	@RequestMapping("/querySaleDrugByCondition.do")
	public String querySaleDrugByCondition(SaleDrug s,Model model) throws UnsupportedEncodingException{
		s.setDrug_id(new String(s.getDrug_id().getBytes("ISO-8859-1"),"UTF-8"));
		List<SaleDrug> list=saleDrugService.querySaleDrugByCondition(s);
		model.addAttribute("list", list);
		return "saleDrugList";
	}
}
