package java.spring.service;
import java.util.List;

import org.hsqldb.rights.Order;

public interface OrderService {
	void addOrder(Order order);
	List<Order> getAllOrders();
	void deleteOrder(Order order);
	void updateOrder(Order order);
}
