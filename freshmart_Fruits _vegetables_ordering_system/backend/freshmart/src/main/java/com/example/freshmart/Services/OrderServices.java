// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Cart;
// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Entity.OrderItem;
// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Repository.CartRepository;
// import com.example.freshmart.Repository.OrderRepository;
// import com.example.freshmart.Repository.RegisterRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class OrderServices {

//     @Autowired
//     private OrderRepository orderRepository;

//     @Autowired
//     private CartRepository cartRepository;

//     @Autowired
//     private RegisterRepository registerRepository;

//     // Place order from cart
//     public Order placeOrder(Integer userId, String paymentMethod) {
//         Register user = registerRepository.findById(userId).orElse(null);
//         if (user == null) return null;

//         List<Cart> cartItems = cartRepository.findByUser(user);
//         if (cartItems.isEmpty()) return null;

//         List<OrderItem> orderItems = new ArrayList<>();
//         double totalAmount = 0.0;

//         for (Cart cart : cartItems) {
//             OrderItem item = new OrderItem();
//             item.setProduct(cart.getProduct());
//             item.setQuantityKg(cart.getQuantityKg());
//             item.setPricePerKg(cart.getProduct().getPricePerKg());
//             item.setTotalPrice(cart.getTotalPrice());

//             totalAmount += cart.getTotalPrice();
//             orderItems.add(item);
//         }

//         Order order = new Order();
//         order.setUser(user);
//         order.setItems(orderItems);
//         order.setTotalAmount(totalAmount);
//         order.setPaymentMethod(paymentMethod);
//         order.setStatus("Pending");
//         order.setOrderDate(LocalDateTime.now());

//         Order savedOrder = orderRepository.save(order);

//         // Clear user's cart after placing order
//         cartRepository.deleteAll(cartItems);

//         return savedOrder;
//     }

//     // Get all orders for a user
//     public List<Order> getUserOrders(Integer userId) {
//         Register user = registerRepository.findById(userId).orElse(null);
//         if (user == null) return null;
//         return orderRepository.findByUser(user);
//     }

//     // Get all orders (Admin)
//     public List<Order> getAllOrders() {
//         return orderRepository.findAll();
//     }

//     // Update order status (Admin)
//     public Order updateOrderStatus(Integer orderId, String status) {
//         Order order = orderRepository.findById(orderId).orElse(null);
//         if (order == null) return null;

//         order.setStatus(status);
//         return orderRepository.save(order);
//     }
// }













// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Cart;
// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Entity.OrderItem;
// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Repository.CartRepository;
// import com.example.freshmart.Repository.OrderRepository;
// import com.example.freshmart.Repository.RegisterRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class OrderServices {

//     @Autowired
//     private OrderRepository orderRepository;

//     @Autowired
//     private CartRepository cartRepository;

//     @Autowired
//     private RegisterRepository registerRepository;

//     // 🔹 Place order from cart
//     public Order placeOrder(Integer userId, String paymentMethod) {
//         Register user = registerRepository.findById(userId).orElse(null);
//         if (user == null) return null;

//         List<Cart> cartItems = cartRepository.findByUser(user);
//         if (cartItems.isEmpty()) return null;

//         List<OrderItem> orderItems = new ArrayList<>();
//         double totalAmount = 0.0;

//         for (Cart cart : cartItems) {
//             OrderItem item = new OrderItem();
//             item.setProduct(cart.getProduct());
//             item.setQuantityKg(cart.getQuantityKg());
//             item.setPricePerKg(cart.getProduct().getPricePerKg());
//             item.setTotalPrice(cart.getTotalPrice());

//             totalAmount += cart.getTotalPrice();
//             orderItems.add(item);
//         }

//         Order order = new Order();
//         order.setUser(user);
//         order.setItems(orderItems);
//         order.setTotalAmount(totalAmount);
//         order.setPaymentMethod(paymentMethod);
//         order.setStatus("Pending");
//         order.setOrderDate(LocalDateTime.now());

//         Order savedOrder = orderRepository.save(order);

//         // Clear user's cart after placing order
//         cartRepository.deleteAll(cartItems);

//         return savedOrder;
//     }

//     // 🔹 Get order by ID (for PaymentController)
//     public Order getById(Integer orderId) {
//         return orderRepository.findById(orderId).orElse(null);
//     }

//     // 🔹 Get all orders for a user
//     public List<Order> getUserOrders(Integer userId) {
//         Register user = registerRepository.findById(userId).orElse(null);
//         if (user == null) return null;
//         return orderRepository.findByUser(user);
//     }

//     // 🔹 Get all orders (Admin)
//     public List<Order> getAllOrders() {
//         return orderRepository.findAll();
//     }

//     // 🔹 Update order status (Admin)
//     public Order updateOrderStatus(Integer orderId, String status) {
//         Order order = orderRepository.findById(orderId).orElse(null);
//         if (order == null) return null;

//         order.setStatus(status);
//         return orderRepository.save(order);
//     }
// }















// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Cart;
// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Entity.OrderItem;
// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Repository.CartRepository;
// import com.example.freshmart.Repository.OrderRepository;
// import com.example.freshmart.Repository.RegisterRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class OrderServices {

//     @Autowired
//     private OrderRepository orderRepository;

//     @Autowired
//     private CartRepository cartRepository;

//     @Autowired
//     private RegisterRepository registerRepository;

//     // 🔹 Place order from cart
//     public Order placeOrder(Integer userId, String paymentMethod) {

//         Register user = registerRepository.findById(userId).orElse(null);
//         if (user == null) return null;

//         List<Cart> cartItems = cartRepository.findByUser(user);
//         if (cartItems.isEmpty()) return null;

//         // 🔥 ORDER PEHLE BANAYA
//         Order order = new Order();
//         order.setUser(user);
//         order.setPaymentMethod(paymentMethod);
//         order.setStatus("Pending");
//         order.setOrderDate(LocalDateTime.now());

//         List<OrderItem> orderItems = new ArrayList<>();
//         double totalAmount = 0.0;

//         for (Cart cart : cartItems) {

//             OrderItem item = new OrderItem();
//             item.setProduct(cart.getProduct());
//             item.setQuantityKg(cart.getQuantityKg());
//             item.setPricePerKg(cart.getProduct().getPricePerKg());
//             item.setTotalPrice(cart.getTotalPrice());

//             item.setOrder(order);   // 🔥 VERY IMPORTANT FIX

//             totalAmount += cart.getTotalPrice();
//             orderItems.add(item);
//         }

//         order.setItems(orderItems);
//         order.setTotalAmount(totalAmount);

//         Order savedOrder = orderRepository.save(order);

//         // Clear user's cart after placing order
//         cartRepository.deleteAll(cartItems);

//         return savedOrder;
//     }

//     // 🔹 Get order by ID (for PaymentController)
//     public Order getById(Integer orderId) {
//         return orderRepository.findById(orderId).orElse(null);
//     }

//     // 🔹 Get all orders for a user
//     public List<Order> getUserOrders(Integer userId) {
//         Register user = registerRepository.findById(userId).orElse(null);
//         if (user == null) return null;
//         return orderRepository.findByUser(user);
//     }

//     // 🔹 Get all orders (Admin)
//     public List<Order> getAllOrders() {
//         return orderRepository.findAll();
//     }

//     // 🔹 Update order status (Admin)
//     public Order updateOrderStatus(Integer orderId, String status) {
//         Order order = orderRepository.findById(orderId).orElse(null);
//         if (order == null) return null;

//         order.setStatus(status);
//         return orderRepository.save(order);
//     }
// }








package com.example.freshmart.Services;

import com.example.freshmart.Entity.Cart;
import com.example.freshmart.Entity.Order;
import com.example.freshmart.Entity.OrderItem;
import com.example.freshmart.Entity.Register;
import com.example.freshmart.Repository.CartRepository;
import com.example.freshmart.Repository.OrderRepository;
import com.example.freshmart.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RegisterRepository registerRepository;

    // 🔹 Place order from cart
    public Order placeOrder(Integer userId, String paymentMethod) {
        Register user = registerRepository.findById(userId).orElse(null);
        if (user == null) return null;

        List<Cart> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) return null;

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        Order order = new Order(); // create order first
        order.setUser(user);
        order.setPaymentMethod(paymentMethod);
        order.setStatus("Pending");
        order.setOrderDate(LocalDateTime.now());

        // Create OrderItems and link to parent order
        for (Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setProduct(cart.getProduct());
            item.setQuantityKg(cart.getQuantityKg());
            item.setPricePerKg(cart.getProduct().getPricePerKg());
            item.setTotalPrice(cart.getTotalPrice());
            item.setOrder(order); // ✅ Link item to parent order

            totalAmount += cart.getTotalPrice();
            orderItems.add(item);
        }

        order.setOrderItems(orderItems); // ✅ set all items
        order.setTotalAmount(totalAmount);

        // Save order (cascades to OrderItems)
        Order savedOrder = orderRepository.save(order);

        // Clear user's cart
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    // 🔹 Get order by ID (for PaymentController)
    public Order getById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // 🔹 Get all orders for a user
    public List<Order> getUserOrders(Integer userId) {
        Register user = registerRepository.findById(userId).orElse(null);
        if (user == null) return null;
        return orderRepository.findByUser(user);
    }

    // 🔹 Get all orders (Admin)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 🔹 Update order status (Admin)
    public Order updateOrderStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return null;

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
