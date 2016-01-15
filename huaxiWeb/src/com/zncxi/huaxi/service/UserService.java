package com.zncxi.huaxi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.UserDao;
import com.zncxi.huaxi.domain.User;

@Service
public class UserService extends BaseService<User, String> {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	@Override
	protected BaseDao<User, String> getEntityDao() {
		return this.userDao;
	}
	
	public User findByUserName(String userName) {
		return userDao.findByUsername(userName);
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username,password);
	}
	
	public boolean isAuth(String code) {
		User user = userDao.findOne(code);
		if(user == null) return false;
		return true;
	}

	/**
	 * 检验用户名是否存在
	 * @param userName
	 * @return  不存在返回true
	 */
	public boolean validateUserName(String userName) {
		try {
			if ("".equals(userName) || userName == null) {
				return true;
			}
			User user = userDao.findByUsername(userName);
			return (user == null);
		} catch (Exception e) {
			logger.error("验证用户名是否存在异常", e);
			return false;
		}
	}
	
	@Transactional(readOnly = true)
	public Page<User> findAllLikeByUser(Pageable pageable, final String queryName, final String queryValue, final String id){
		return this.userDao.findAll(new Specification<User>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (StringUtils.hasText(queryValue) && StringUtils.hasText(queryName)) {
					String[] names = StringUtils.split(queryName, ".");
					if(names != null) {
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						predicates.add(cb.like(expression, "%" + queryValue + "%"));
					} else {
						Path expression = root.get(queryName);
						predicates.add(cb.like(expression, "%" + queryValue + "%"));
					}
				}
				if(StringUtils.hasText(id)){
					predicates.add(cb.equal(root.get("id"), id));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
	}
}
