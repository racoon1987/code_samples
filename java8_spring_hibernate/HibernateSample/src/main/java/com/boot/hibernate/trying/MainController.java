package com.boot.hibernate.trying;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Subgraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.hibernate.trying.association.OrderDetail;
import com.boot.hibernate.trying.association.UserImmutable;
import com.boot.hibernate.trying.association.UserLazy;
import com.boot.hibernate.trying.basic.Person;
import com.boot.hibernate.trying.basic.Phone;

@Controller
public class MainController {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "/test-basic")
	@Transactional
	public void testBasic() {
		Person person = new Person();
		entityManager.persist(person);

		Phone phone = new Phone();
		phone.setNumber("123-456-7890");
		phone.setPerson(person);
		entityManager.persist(phone);

		entityManager.flush();
	}

	@RequestMapping(value = "/test-onetomany-lazy")
	@Transactional
	public void testLazyOneToMany() {
		List<UserLazy> users = sessionFactory.getCurrentSession()
				.createQuery("From UserLazy", UserLazy.class).list();
		UserLazy userLazyLoaded = users.get(1);

		System.out.println(userLazyLoaded.getOrderDetail());
	}
	
	@RequestMapping(value = "/test-onetomany-lazy-joinfetch")
	@Transactional
	public void testLazyOneToManyJoinFetch() {
		List<UserLazy> users = sessionFactory.getCurrentSession()
				.createQuery("select u From UserLazy u join fetch u.orderDetail", UserLazy.class).list();
		UserLazy userLazyLoaded = users.get(1);

		System.out.println(userLazyLoaded.getOrderDetail());
	}
	
	@RequestMapping(value = "/test-onetomany-lazy-NamedEntityGraph")
	@Transactional
	public void testLazyOneToManyNamedEntityGraph() {
		Session session = sessionFactory.getCurrentSession();
		
		EntityGraph<?> graph = session.createEntityGraph("graph.User.orderDetail");
		
		List<UserLazy> users = sessionFactory.getCurrentSession()
				.createQuery("From UserLazy", UserLazy.class).setHint("javax.persistence.loadgraph", graph).list();
		
		UserLazy userLazyLoaded = users.get(1);

		System.out.println(userLazyLoaded.getOrderDetail());
	}
	
	@RequestMapping(value = "/test-onetomany-lazy-DynamicEntityGraph")
	@Transactional
	public void testLazyOneToManyDynamicEntityGraph() {
		Session session = sessionFactory.getCurrentSession();
		
		// cách này ko cần dùng annotation giống như NamedEntityGraph
		EntityGraph<UserLazy> graph = session.createEntityGraph(UserLazy.class);
		Subgraph<OrderDetail> orderDetailSubgraph = graph.addSubgraph("orderDetail");
		// orderDetailSubgraph.addSubgraph(arg0)	// có thể add subgraph nữa nếu có
		
		List<UserLazy> users = sessionFactory.getCurrentSession()
				.createQuery("From UserLazy", UserLazy.class).setHint("javax.persistence.loadgraph", graph).list();
		
		UserLazy userLazyLoaded = users.get(1);

		System.out.println(userLazyLoaded.getOrderDetail());
	}

	@RequestMapping(value = "/test-manytoone-lazy")
	@Transactional
	public void testLazyManyToOne() {
		List<OrderDetail> orderList = sessionFactory.getCurrentSession()
				.createQuery("From OrderDetail", OrderDetail.class).list();

		OrderDetail orderDetail = orderList.get(0);

		UserLazy owner = orderDetail.getUser();

		// hibernate only execute query if owner is used
		System.out.println(owner);
	}
	
	@RequestMapping(value = "/test-brigde-association")
	@Transactional
	public void testBrigdeAssociation() {
		List<OrderDetail> orderList = sessionFactory.getCurrentSession()
				.createQuery("From OrderDetail", OrderDetail.class).list();

		OrderDetail orderDetail = orderList.get(0);

		UserLazy owner = orderDetail.getUser();
		
		Set<OrderDetail> orderList2 = owner.getOrderDetail();

		System.out.println(orderList2.size());
	}

	@RequestMapping(value = "/test-persist-by-set-method")
	@Transactional
	public void testPersistBySetMethod() {
		UserLazy user = sessionFactory.getCurrentSession().get(UserLazy.class, 1L);
		
		user.setName("Linh");	// this will be update into db
		
		System.out.println("done");
	}
	
	@RequestMapping(value = "/test-persist-immutable")
	@Transactional
	public void testPersistImmutable() {
		UserImmutable user = sessionFactory.getCurrentSession().get(UserImmutable.class, 1L);
		
		user.setName("yyyy");	// this will be update into db
		
		System.out.println("done");
	}

	@Deprecated
	@RequestMapping(value = "/test-qbe-like")
	@Transactional
	public void testCriteriaLike() {
		UserLazy user = new UserLazy();
		user.setName("%inh");
		Example userExample = Example.create(user).ignoreCase().enableLike();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserLazy.class).add(userExample);
		List<UserLazy> userList = criteria.list();
		
		System.out.println(userList);
	}
	
	@RequestMapping(value = "/test-find-by-hql")
	@Transactional
	public void testFindByHql() {
		String searchValue = "%inh%";
		String fieldName = "name";
		
		String hql = "from UserLazy where "+fieldName+" like :fieldValue";
		Query<UserLazy> query = sessionFactory.getCurrentSession().createQuery(hql, UserLazy.class);
		query.setParameter("fieldValue", searchValue);
		
		List<UserLazy> userList = query.list();
		
		UserLazy user1 = userList.get(0);
		
		System.out.println(user1.getName());
	}
	
	@RequestMapping(value = "/test-pagination-hql")
	@Transactional
	public void testPaginationHql() {
		Session session = sessionFactory.getCurrentSession();
		
		int pageSize = 5;
	    String countQ = "Select count (id) from UserLazy";
	    Query countQuery = session.createQuery(countQ);
	    Long countResults = (Long) countQuery.uniqueResult();
	    int lastPageNumber = (int) (Math.ceil(countResults / pageSize));
	 
	    Query<UserLazy> selectQuery = session.createQuery("From UserLazy", UserLazy.class);
	    selectQuery.setFirstResult(3);				// bắt đầu từ vị trí thứ 3
	    selectQuery.setMaxResults(pageSize);		// số bản ghi mỗi trang
	    List<UserLazy> lastPage = selectQuery.list();
	}
	
	@RequestMapping(value = "/test-pagination-ScrollableResults")
	@Transactional
	public void testPaginationScrollableResults() {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from UserLazy";
		Query query = session.createQuery(hql);
		int pageSize = 5;
		 
		ScrollableResults resultScroll = query.scroll(ScrollMode.FORWARD_ONLY);
		resultScroll.first();
		resultScroll.scroll(0);
		List<UserLazy> userPage = new ArrayList<>();
		
		int i = 0;
		while (pageSize > i++) {
		    userPage.add((UserLazy) resultScroll.get(0));
		    if (!resultScroll.next())
		        break;
		}
		
		resultScroll.last();
		int totalResults = resultScroll.getRowNumber() + 1;
		
		System.out.println("Tổng số trang: " + totalResults);
	}
	
	@RequestMapping(value = "/test-criteria")
	@Transactional
	public void testCriteria() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserLazy> cr = cb.createQuery(UserLazy.class);
		Root<UserLazy> root = cr.from(UserLazy.class);
		cr.select(root).where(cb.equal(root.get("name"), "Linh"));
		 
		Query<UserLazy> query = session.createQuery(cr);
		List<UserLazy> userList = query.getResultList();
		
		System.out.println(userList.get(0).getName());
	}
}
