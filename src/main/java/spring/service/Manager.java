package spring.service;
import java.util.List;

import spring.model.AddressModel;
import spring.model.OrderModel;
import spring.model.ProductModel;
import spring.model.RoleModel;
import spring.model.UserModel;

public interface Manager {
	void addUser(UserModel user);
	List<UserModel> getAllUsers();
	void deleteUser(UserModel user);
	void updateUser(UserModel user);
	UserModel findUserById(UserModel user);
	
	void addRole(RoleModel role);
	List<RoleModel> getAllRoles();
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);
	RoleModel findRoleById(RoleModel role);
	
	void addAddress(AddressModel address);
	List<AddressModel> getAllAddresses();
	void deleteAddress(AddressModel address);
	void updateAddress(AddressModel address);
	AddressModel findAddressById(AddressModel address);

	void addProduct(ProductModel product);
	List<ProductModel> getAllProducts();
	void deleteProduct(ProductModel product);
	void updateProduct(ProductModel product);
	ProductModel findProductById(ProductModel product);
	
	void addRole(OrderModel order);
	List<OrderModel> getAllOrders();
	void deleteOrder(OrderModel order);
	void updateOrder(OrderModel order);
	OrderModel findOrderById(OrderModel order);

}
