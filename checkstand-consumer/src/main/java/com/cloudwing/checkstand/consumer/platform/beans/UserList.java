package com.cloudwing.checkstand.consumer.platform.beans;

import java.util.List;

/**
 * 列表
 * @author 云翼RPC-java-代码自动生成
 *
 */
public class UserList {

	/**
	 * 用户集合
	 */
	private List<User> users;

	/**
	 * 现在的页
	 */
	private Integer current_page;

	/**
	 * 最后一页
	 */
	private Integer last_page;

	/**
	 * 集合数据首项
	 */
	private Integer from;

	/**
	 * 集合数据尾项
	 */
	private Integer to;

	/**
	 * 每页大小
	 */
	private Integer per_page;

	/**
	 * 总数据量
	 */
	private Integer total;

	/**
	 * get用户集合
	 */
	public List<User> getUsers(){
		return this.users;
	}

	/**
	 * set用户集合
	 */
	public void setUsers(List<User> users){
		this.users = users;
	}

	/**
	 * get现在的页
	 */
	public Integer getCurrent_page(){
		return this.current_page;
	}

	/**
	 * set现在的页
	 */
	public void setCurrent_page(Integer current_page){
		this.current_page = current_page;
	}

	/**
	 * get最后一页
	 */
	public Integer getLast_page(){
		return this.last_page;
	}

	/**
	 * set最后一页
	 */
	public void setLast_page(Integer last_page){
		this.last_page = last_page;
	}

	/**
	 * get集合数据首项
	 */
	public Integer getFrom(){
		return this.from;
	}

	/**
	 * set集合数据首项
	 */
	public void setFrom(Integer from){
		this.from = from;
	}

	/**
	 * get集合数据尾项
	 */
	public Integer getTo(){
		return this.to;
	}

	/**
	 * set集合数据尾项
	 */
	public void setTo(Integer to){
		this.to = to;
	}

	/**
	 * get每页大小
	 */
	public Integer getPer_page(){
		return this.per_page;
	}

	/**
	 * set每页大小
	 */
	public void setPer_page(Integer per_page){
		this.per_page = per_page;
	}

	/**
	 * get总数据量
	 */
	public Integer getTotal(){
		return this.total;
	}

	/**
	 * set总数据量
	 */
	public void setTotal(Integer total){
		this.total = total;
	}

}
