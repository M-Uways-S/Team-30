package com.group30.major;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.group30.major.model.Category;
import com.group30.major.model.Order;
import com.group30.major.model.Product;
import com.group30.major.model.User;
import com.group30.major.repository.RoleRepository;
import com.group30.major.repository.UserRepository;
import com.group30.major.service.CategoryService;
import com.group30.major.service.OrderService;
import com.group30.major.service.ProductService;
import com.group30.major.service.UserService;

@SpringBootTest
class MajorApplicationTests {

	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderService orderService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	RoleRepository roleRepository;

	// Creating a product and retrieving it from the DB
	@Test
	public void createProductTest() {
		Product p = new Product();
		p.setName("TestP");
		p.setCategory(categoryService.getCategoryById(1).get());
		p.setPrice(888.0);
		p.setStock(777);
		p.setDescription("666");
		p.setImageName("phone.png");
		productService.addProduct(p);
		assertNotNull(productService.getProductById(p.getId()).get());
	}

	// Creating a user and retrieving it from the DB
	@Test
	public void createUserTest() {
		User u = new User();
		u.setFirstName("JUnit");
		u.setLastName("Test");
		u.setEmail("junit@test.com");
		u.setPassword("junittest");
		userRepository.save(u);
		assertNotNull(userService.getUserByEmail("junit@test.com").get());
	}

	// Creating an order and retrieving it from the DB
	@Test
	public void createOrderTest() {
		Order o = new Order();
		o.setUser(userRepository.findUserByEmail("test1@gmail.com").get());
		o.setProduct(productService.getProductById(6).get());
		o.setPrice(999.0);
		o.setStock(8);
		o.setDate(java.time.LocalDate.now().toString());
		orderService.addOrder(o);
		assertNotNull(orderService.getOrderById(o.getId()).get());
	}

	// Creating a category and retrieving it from the DB
	@Test 
	void createCategoryTest() {
		Category c = new Category();
		c.setName("JUnit");
		categoryService.addCategory(c);
		assertNotNull(categoryService.getCategoryById(c.getId()).get());
	}

}
