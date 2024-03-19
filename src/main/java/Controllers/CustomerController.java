package Controllers;

import Models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private static final Logger logger = LogManager.getLogger(CustomerController.class);
    private List<Customer> customerList;

    public CustomerController() {

        this.customerList = new ArrayList<>();
    }

    public void createCustomer(Customer customer) {
        try {

            customerList.add(customer);
            logger.info("Customer created: " + customer.getName());
        } catch (Exception e) {
            logger.error("Error creating customer: " + e.getMessage());

            throw e;
        }
    }

    public void updateCustomer(Customer customer) {
        try {

            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getId() == customer.getId()) {
                    customerList.set(i, customer);
                    logger.info("Customer updated: " + customer.getName());
                    return;
                }
            }
            logger.warn("Customer not found with ID: " + customer.getId());
            throw new IllegalArgumentException("Customer not found with ID: " + customer.getId());
        } catch (Exception e) {
            logger.error("Error updating customer: " + e.getMessage());

            throw e;
        }
    }

    public void deleteCustomer(int customerId) {
        try {

            for (Customer customer : customerList) {
                if (customer.getId() == customerId) {
                    customerList.remove(customer);
                    logger.info("Customer deleted with ID: " + customerId);
                    return;
                }
            }
            logger.warn("Customer not found with ID: " + customerId);
            throw new IllegalArgumentException("Customer not found with ID: " + customerId);
        } catch (Exception e) {
            logger.error("Error deleting customer: " + e.getMessage());

            throw e;
        }
    }

    public List<Customer> getAllCustomers() {

        logger.info("Retrieved all customers");
        return customerList;
    }

    public Customer getCustomerById(int customerId) {

        for (Customer customer : customerList) {
            if (customer.getId() == customerId) {
                logger.info("Retrieved customer by ID: " + customerId);
                return customer;
            }
        }
        logger.warn("Customer not found with ID: " + customerId);
        return null;
    }
}
