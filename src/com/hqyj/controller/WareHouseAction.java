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
		int pageIndex = 1;//���ó�ʼ�ĵ�ǰҳ��ҳ����ʾ�Ķ��ǵ�һҳ
        int pageSize = 4;//����ÿһҳ��ʾ��������,���ڼ�����ҳ�����˴����õ�ֵ������sql����limit������ֵһ��
        PageUtil<WareHouse> pageUtil = new PageUtil<WareHouse>();//��ʼ��������
        ArrayList<WareHouse> list = new ArrayList<WareHouse>();
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }//��ҳ���ϵķ�ҳ��ǩ����ֵ,���л�ȡ,Ҳ�Ǿ͵��'��һҳ������һҳ'��������pageindex
        pageUtil.setPageIndex(pageIndex);//������������
        int number = wareHouseService.pagecount();//����service�㷽��������������������Ƕ���������.
        pageUtil.setPageNumber(number);//������������
        pageUtil.setPageSize(pageSize);//������������
        pageUtil.setPageCount((int) Math.ceil((double) (pageUtil.getPageNumber() / pageUtil.getPageSize())) + 1);//�������ҳ��,����װ��������
        int index = (pageIndex -1) * pageSize;//�����ÿһҳ�����ݿ��еڼ������ݿ�ʼȡֵ��Ҳ����limit����ĵ�һ������
        list = wareHouseService.showlist(index);//����service��ķ�����ȡ�����ݿ��е�ֵ
        pageUtil.setList(list);//���浽�������еļ���
        model.addAttribute("pageUtil", pageUtil);//���ݵ�ҳ�棬����ֵջ��
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
			model.addAttribute("msg","�޸�ʧ��");
			return "wareHouseEdit";
		}
	}
	@RequestMapping("/queryWareHouseByCondition.do")
	public String queryUserByCondition(HttpServletRequest request,String drug_id,
			String drug_name,Model model) throws UnsupportedEncodingException{
		drug_id=(new String(drug_id.getBytes("ISO-8859-1"),"UTF-8"));
		drug_name=(new String(drug_name.getBytes("ISO-8859-1"),"UTF-8"));
		int pageIndex = 1;//���ó�ʼ�ĵ�ǰҳ��ҳ����ʾ�Ķ��ǵ�һҳ
        int pageSize = 4;//����ÿһҳ��ʾ��������,���ڼ�����ҳ�����˴����õ�ֵ������sql����limit������ֵһ��
        PageUtil<WareHouse> pageUtil = new PageUtil<WareHouse>();//��ʼ��������
        List<WareHouse> list = new ArrayList<WareHouse>();
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }//��ҳ���ϵķ�ҳ��ǩ����ֵ,���л�ȡ,Ҳ�Ǿ͵��'��һҳ������һҳ'��������pageindex
        pageUtil.setPageIndex(pageIndex);//������������
        int number = wareHouseService.queryPagecount(drug_id,drug_name);//����service�㷽��������������������Ƕ���������.
        pageUtil.setPageNumber(number);//������������
        pageUtil.setPageSize(pageSize);//������������
        pageUtil.setPageCount((int) Math.ceil((double) (pageUtil.getPageNumber() / pageUtil.getPageSize())) + 1);//�������ҳ��,����װ��������
        int index = (pageIndex -1) * pageSize;//�����ÿһҳ�����ݿ��еڼ������ݿ�ʼȡֵ��Ҳ����limit����ĵ�һ������
        list = wareHouseService.queryWareHouseByCondition(drug_id, drug_name, index);//����service��ķ�����ȡ�����ݿ��е�ֵ
        pageUtil.setList(list);//���浽�������еļ���
        model.addAttribute("pageUtil", pageUtil);//���ݵ�ҳ�棬����ֵջ��
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
