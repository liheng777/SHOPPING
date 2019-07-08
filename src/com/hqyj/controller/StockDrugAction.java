package com.hqyj.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hqyj.bean.StockDrug;
import com.hqyj.service.StockDrugService;

@Controller
@RequestMapping(value="/stockDrug")
public class StockDrugAction {
	@Autowired
	private StockDrugService stockDrugService;
	
	@RequestMapping("/stockDrugList.do")
	public String stockDrugList(Model model){
		ArrayList<StockDrug> list = stockDrugService.queryAll();
		model.addAttribute("list", list);
		return "stockDrugList";
	}
	@RequestMapping("/preAdd")
	public String preAdd(){
		return "stockDrugAdd";
	}
	@RequestMapping("/stockDrugAdd")
	public String add(StockDrug s,Model model){
		boolean result = stockDrugService.add(s);
		if(result){
			return "redirect:/stockDrug/stockDrugList.do";
		}else{
			model.addAttribute("msg","Ôö¼ÓÊ§°Ü");
			return "stockDrugAdd";
		}
	}
	@RequestMapping("/preEdit")
	public ModelAndView preEdit(Integer id){
		StockDrug stockDrug = stockDrugService.queryById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("stockDrug", stockDrug);
		mv.setViewName("stockDrugEdit");
		return mv;
	}
	@RequestMapping(value="/edit.do")
	public String edit(StockDrug s,Model model){
		int result = stockDrugService.edit(s);
		if(result==1){
				return "redirect:/stockDrug/stockDrugList.do";
		}else{
			model.addAttribute("msg","ÐÞ¸ÄÊ§°Ü");
			return "stockDrugEdit";
		}
	}
	@RequestMapping("/queryStockDrugByCondition.do")
	public String queryStockDrugByCondition(StockDrug s,Model model) throws UnsupportedEncodingException{
		s.setDrug_id(new String(s.getDrug_id().getBytes("ISO-8859-1"),"UTF-8"));
		s.setSupplier(new String(s.getSupplier().getBytes("ISO-8859-1"),"UTF-8"));
		List<StockDrug> list=stockDrugService.queryStockDrugByCondition(s);
		model.addAttribute("list", list);
		return "stockDrugList";
	}
	public StockDrugService getStockDrugService() {
		return stockDrugService;
	}

	public void setStockDrugService(StockDrugService stockDrugService) {
		this.stockDrugService = stockDrugService;
	}
	
}
