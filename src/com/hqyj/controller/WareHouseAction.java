package com.hqyj.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hqyj.bean.PageUtil;
import com.hqyj.bean.WareHouse;
import com.hqyj.service.WareHouseService;

@Controller
@RequestMapping(value="/wareHouse")
public class WareHouseAction {
	@Autowired
	private WareHouseService wareHouseService;

	public WareHouseService getWareHouseService() {
		return wareHouseService;
	}

	public void setWareHouseService(WareHouseService wareHouseService) {
		this.wareHouseService = wareHouseService;
	}
	@RequestMapping("/wareHouseList.do")
	public String stockDrugList(HttpServletRequest request,Model model){
		int pageIndex = 1;//设置初始的当前页，页面显示的都是第一页
        int pageSize = 4;//设置每一页显示几条数据,用于计算总页数，此处设置的值必须与sql语句的limit最后的数值一样
        PageUtil<WareHouse> pageUtil = new PageUtil<WareHouse>();//初始化工具类
        ArrayList<WareHouse> list = new ArrayList<WareHouse>();
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }//对页面上的分页标签传的值,进行获取,也是就点击'上一页或者下一页'传过来的pageindex
        pageUtil.setPageIndex(pageIndex);//保存至工具类
        int number = wareHouseService.pagecount();//调用service层方法计算出总数据量，就是多少条数据.
        pageUtil.setPageNumber(number);//保存至工具类
        pageUtil.setPageSize(pageSize);//保存至工具类
        pageUtil.setPageCount((int) Math.ceil((double) (pageUtil.getPageNumber() / pageUtil.getPageSize())) + 1);//计算出总页数,并封装到工具类
        int index = (pageIndex -1) * pageSize;//计算出每一页从数据库中第几条数据开始取值，也就是limit后面的第一个数字
        list = wareHouseService.showlist(index);//调用service层的方法，取得数据库中的值
        pageUtil.setList(list);//保存到工具类中的集合
        model.addAttribute("pageUtil", pageUtil);//传递到页面，存入值栈中
        model.addAttribute("list",list);
		return "wareHouseList";
	}
	@RequestMapping("/preEdit")
	public ModelAndView preEdit(int id){
		WareHouse wareHouse = wareHouseService.queryById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("wareHouse", wareHouse);
		mv.setViewName("wareHouseEdit");
		return mv;
	}
	@RequestMapping(value="/edit.do")
	public String edit(WareHouse wareHouse,Model model){
		boolean result = wareHouseService.edit(wareHouse);
		if(result){
				return "redirect:/wareHouse/wareHouseList.do";
		}else{
			model.addAttribute("msg","修改失败");
			return "wareHouseEdit";
		}
	}
	@RequestMapping("/queryWareHouseByCondition.do")
	public String queryUserByCondition(HttpServletRequest request,String drug_id,
			String drug_name,Model model) throws UnsupportedEncodingException{
		drug_id=(new String(drug_id.getBytes("ISO-8859-1"),"UTF-8"));
		drug_name=(new String(drug_name.getBytes("ISO-8859-1"),"UTF-8"));
		int pageIndex = 1;//设置初始的当前页，页面显示的都是第一页
        int pageSize = 4;//设置每一页显示几条数据,用于计算总页数，此处设置的值必须与sql语句的limit最后的数值一样
        PageUtil<WareHouse> pageUtil = new PageUtil<WareHouse>();//初始化工具类
        List<WareHouse> list = new ArrayList<WareHouse>();
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }//对页面上的分页标签传的值,进行获取,也是就点击'上一页或者下一页'传过来的pageindex
        pageUtil.setPageIndex(pageIndex);//保存至工具类
        int number = wareHouseService.queryPagecount(drug_id,drug_name);//调用service层方法计算出总数据量，就是多少条数据.
        pageUtil.setPageNumber(number);//保存至工具类
        pageUtil.setPageSize(pageSize);//保存至工具类
        pageUtil.setPageCount((int) Math.ceil((double) (pageUtil.getPageNumber() / pageUtil.getPageSize())) + 1);//计算出总页数,并封装到工具类
        int index = (pageIndex -1) * pageSize;//计算出每一页从数据库中第几条数据开始取值，也就是limit后面的第一个数字
        list = wareHouseService.queryWareHouseByCondition(drug_id, drug_name, index);//调用service层的方法，取得数据库中的值
        pageUtil.setList(list);//保存到工具类中的集合
        model.addAttribute("pageUtil", pageUtil);//传递到页面，存入值栈中
        model.addAttribute("list",list);
		return "wareHouseList";
	}
//	@RequestMapping("/queryWareHouseByCondition.do")
//	public String queryUserByCondition(WareHouse w,Model model) throws UnsupportedEncodingException{
//		w.setDrug_id(new String(w.getDrug_id().getBytes("ISO-8859-1"),"UTF-8"));
//		w.setDrug_name(new String(w.getDrug_name().getBytes("ISO-8859-1"),"UTF-8"));
//		List<WareHouse> list=wareHouseService.queryWareHouseByCondition(w);
//		model.addAttribute("list", list);
//		return "wareHouseList";
//	}
}
