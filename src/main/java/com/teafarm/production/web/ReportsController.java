package com.teafarm.production.web;

import java.sql.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Credit;
import com.teafarm.production.service.CardService;
import com.teafarm.production.service.CompanyService;
import com.teafarm.production.service.CreditService;
import com.teafarm.production.service.EmployeeService;
import com.teafarm.production.service.WeightService;
import com.teafarm.production.web.dto.CreditCommodityTotals;
import com.teafarm.production.web.dto.CreditReport;
import com.teafarm.production.web.dto.DailySummary;
import com.teafarm.production.web.dto.GrowersSummary;
import com.teafarm.production.web.dto.Salary;
import com.teafarm.production.web.dto.SalaryDue;

@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportsController {
	@Autowired
	WeightService weightService;
	@Autowired
	CardService cardService;
	@Autowired
	EmployeeService empService;
	@Autowired
	CompanyService companyService;
	@Autowired
	CreditService creditService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("salary/{id}/{start}/{end}")
	public List<Salary> getSalary(@PathVariable int id,@PathVariable Date start,@PathVariable Date end){
		List<Salary> salary=weightService.getSalary(start, end,id);
		return salary;
	}
	@GetMapping("salaryDue/{id}/{start}/{end}")
	public List<SalaryDue> getSalaryDue(@PathVariable int id,@PathVariable Date start,@PathVariable Date end){
		List<SalaryDue> salaryDue=weightService.getSalaryDue(start, end,id);
		return salaryDue;
	}
	@GetMapping("dailySummary/{id}")
	public List<DailySummary> getSummary(@PathVariable int id){
		List<DailySummary> dailySummary=weightService.getDailySummary(id);
		return dailySummary;
	}
	
	@GetMapping("growersSummary/{id}/{start}/{end}")
	public List<GrowersSummary> getGrowersSummary(@PathVariable int id,@PathVariable Date start,@PathVariable Date end){
		List<GrowersSummary> summary=cardService.getGrowersSummary(id, start,end);
		return summary;
	}
	@GetMapping("creditReport/{start}/{end}/{id}")
	public List<CreditReport> getReport(@PathVariable Date start,@PathVariable Date end,@PathVariable int id){
		List<CreditReport> report=creditService.getReport(start, end, id);
		return report;
	}
	@GetMapping("commodityReport/{start}/{end}/{id}")
	public List<CreditCommodityTotals> getTotals(@PathVariable Date start,@PathVariable Date end,@PathVariable int id){
		List<CreditCommodityTotals> report=creditService.getCommodityTotals(start, end, id);
		return report;
	}
	
	
}
