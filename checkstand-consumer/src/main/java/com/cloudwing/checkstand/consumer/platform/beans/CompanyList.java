package com.cloudwing.checkstand.consumer.platform.beans;

import java.util.List;

/**
 * 
 * @author 云翼RPC-java-代码自动生成
 *
 */
public class CompanyList {

	/**
	 * 公司集合
	 */
	private List<Company> companies;

	/**
	 * 
	 */
	private Integer current_page;

	/**
	 * 
	 */
	private Integer last_page;

	/**
	 * 
	 */
	private Integer from;

	/**
	 * 
	 */
	private Integer to;

	/**
	 * 
	 */
	private Integer per_page;

	/**
	 * 
	 */
	private Integer total;

	/**
	 * get公司集合
	 */
	public List<Company> getCompanies(){
		return this.companies;
	}

	/**
	 * set公司集合
	 */
	public void setCompanies(List<Company> companies){
		this.companies = companies;
	}

	/**
	 * get
	 */
	public Integer getCurrent_page(){
		return this.current_page;
	}

	/**
	 * set
	 */
	public void setCurrent_page(Integer current_page){
		this.current_page = current_page;
	}

	/**
	 * get
	 */
	public Integer getLast_page(){
		return this.last_page;
	}

	/**
	 * set
	 */
	public void setLast_page(Integer last_page){
		this.last_page = last_page;
	}

	/**
	 * get
	 */
	public Integer getFrom(){
		return this.from;
	}

	/**
	 * set
	 */
	public void setFrom(Integer from){
		this.from = from;
	}

	/**
	 * get
	 */
	public Integer getTo(){
		return this.to;
	}

	/**
	 * set
	 */
	public void setTo(Integer to){
		this.to = to;
	}

	/**
	 * get
	 */
	public Integer getPer_page(){
		return this.per_page;
	}

	/**
	 * set
	 */
	public void setPer_page(Integer per_page){
		this.per_page = per_page;
	}

	/**
	 * get
	 */
	public Integer getTotal(){
		return this.total;
	}

	/**
	 * set
	 */
	public void setTotal(Integer total){
		this.total = total;
	}

}
