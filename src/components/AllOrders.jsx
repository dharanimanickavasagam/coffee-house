/* eslint-disable react/no-direct-mutation-state */
import React, { Component } from 'react';
import API from '../utils/API';
import { Card, Button, Modal, Accordion } from 'react-bootstrap';
import '../styles/AllOrders.css';
import '../styles/Table.css';

class AllOrders extends Component {
  state = {
    customers: [],

    customer: {
      customerId: '',
      customerName: '',
      customerMobileNumber: '',

      orders: []
    },

    order: {
      id: '',
      totalPrice: '',
      tax: '',
      tenderedAmount: ''
    },

    orderItems: [],
    orderItem: {
      id: '',
      netPrice: '',
      order_id: '',
      product_id: '',
      product_name: '',
      quantity: '',
      unitPrice: ''
    },

    isShowing: false,
    activeModal: null
  };

  jsonData = null;
  orderItems = null;

  constructor() {
    super();
    this.handleGetData();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleGetData()
  // Get all existing orders in the application
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleGetData = () => {
    this.state.customers = [];
    this.state.customer = {
      orders: []
    };

    API.get('billorder').then(response => {
      this.jsonData = response.data;

      this.jsonData.map(customer => {
        // eslint-disable-next-line no-unused-expressions

        const orderEntities = customer.orderEntities;
        //const orderItemsEntities = customer.orderEntities.orderItemsEntities;

        //Extracting Customers
        this.state.customer.customerId = customer.id;
        this.state.customer.customerName = customer.name;
        this.state.customer.customerMobileNumber = customer.mobileNumber;

        //Extracting Orders
        orderEntities.map((value,index) => {
          console.log(
            'Serving Customer',
            this.state.customer.customerName,
            this.state.customer
          );
          this.state.order.id = value.id;
          this.state.order.tax = value.tax;
          this.state.order.tenderedAmount = value.tenderedAmount;
          this.state.order.totalPrice = value.totalPrice;

          console.log('Order Obj is ready', this.state.order);
          console.log('Adding order to customer');
          this.state.customer.orders.push(this.state.order);
          this.state.order = {};

          return this.state.order;
        });

        this.handleCustomer(this.state.customer);
        this.state.customer = {
          orders: []
        };

        return this.state.customer;
      });
    });
  };

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleCustomer()
  // Push a customer to the actual customers object
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleCustomer = customer => {
    this.state.customers.push(customer);
    console.log('Result', this.state.customers);
  };

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleOrderItems
  // lists the order items of an order
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleOrderItems = (event, orderId) => {
    API.get('billorder/' + orderId)
      .then(response => {
        return response.data;
      })
      .then(response => {
        this.orderItems = response;
        console.log('OI ', this.orderItems);
        this.state.orderItems = [];

        this.orderItems.map((orderItem, index) => {
          this.state.orderItem.product_id = orderItem.productId;
          this.state.orderItem.product_name = orderItem.productName;
          this.state.orderItem.quantity = orderItem.quantity;
          this.state.orderItem.netPrice = orderItem.netPrice;

          this.handleOrderItemsArray(this.state.orderItem, () => {
            console.log('Waiting');
          });
          this.state.orderItem = {};
          this.forceUpdate();
          return this.state.orderItems;
        });
      });
  };

  handleOrderItemsArray = orderItem => {
    this.state.orderItems.push(orderItem);
    this.state.orderItem = {};
  };
  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleViewOrders and closeModalHandler
  // Modal Methods
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleViewOrders = (e, index) => {
    this.setState({
      activeModal: index
    });
  };

  closeModalHandler = () => {
    this.setState({
      activeModal: null
    });
  };

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleDeleteOrder
  // Deletes a particular order from orders table
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleDeleteOrder = (event, orderId) => {
    console.log(orderId);
    API.delete('billorder', {
      params: {
        orderId: orderId
      }
    })
      .then(response => {
        this.handleGetData(() => {
          return console.log('Waiting');
        });
      })
      .catch(error => alert(error));

    this.setState({
      activeModal: null
    });
  };

  handleDeleteCustomer = () => {};

  render() {
    return (
      <React.Fragment>
        <h1> All Orders </h1>

        {this.state.customers.map((customer, index) => {
          return (
            <div class = "container"> 
            <Card className="cards">
              <Card.Body className="cardBody">
                <Card.Title>Customer Details </Card.Title>
                <Card.Text>Customer ID : {customer.customerId}</Card.Text>
                <Card.Text>
                  Customer Mobile: {customer.customerMobileNumber}
                </Card.Text>
                <Card.Text> Customer Name : {customer.customerName}</Card.Text>

                <Button
                  id={index}
                  variant="btn btn-primary btn-success"
                  onClick={e => this.handleViewOrders(e, index)}
                >
                  View Orders
                </Button>

                <Button
                  variant="btn btn-primary btn-danger floatLeft"
                  onClick={() => this.handleDeleteCustomer()}
                >
                  Delete Customer
                </Button>

                {this.state.activeModal ? (
                  <div onClick={this.closeModalHandler} />
                ) : null}

                <Modal
                  name={index}
                  className="modal"
                  show={this.state.activeModal === index}
                  close={this.closeModalHandler}
                >
                  <h3>Orders Till Date </h3>

                  {customer.orders.map((order, index) => {
                    let orderId = order.id;
                    return (
                      <React.Fragment>
                        <hr />

                        <Accordion>
                          <Card>
                            <Accordion.Toggle
                              as={Card.Header}
                              eventKey="0"
                              variant="link"
                              class="orders"
                              tooltip="Click to Expand"
                              onClick={event =>
                                this.handleOrderItems(event, orderId)
                              }
                            >
                              <table id="productTable">
                                <tr>
                                  <td> Order ID</td>
                                  <td> Total Price </td>
                                  <td> MI Tax </td>
                                  <td> Tendered Amount </td>
                                </tr>
                                <tr key={index}>
                                  <td> {order.id}</td>
                                  <td> $ {order.totalPrice} </td>
                                  <td> $ {order.tax}</td>
                                  <td> $ {order.tenderedAmount} </td>
                                </tr>
                              </table>
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="0">
                              <Card.Body class="expand">
                                <h5> Order Items</h5>
                                <table id="productTable">
                                  <tr>
                                    <td> Product ID</td>
                                    <td> Product Price </td>
                                    <td> Quantity </td>
                                    <td> Net Price </td>
                                  </tr>

                                  {this.state.orderItems.map(
                                    (orderItem, indexVal) => {
                                      return (
                                        <tr>
                                          <td> {orderItem.product_id}</td>
                                          <td> {orderItem.product_name} </td>
                                          <td> {orderItem.quantity}</td>
                                          <td> $ {orderItem.netPrice} </td>
                                        </tr>
                                      );
                                    }
                                  )}
                                </table>
                              </Card.Body>
                            </Accordion.Collapse>
                          </Card>
                        </Accordion>
                        <Button
                          variant="btn btn-sm btn-primary btn-danger delete width"
                          onClick={event =>
                            this.handleDeleteOrder(event, order.id)
                          }
                        >
                          Delete Order
                        </Button>
                      </React.Fragment>
                    );
                  })}

                  <Button
                    variant="btn btn-primary btn-danger"
                    onClick={this.closeModalHandler}
                  >
                    Cancel
                  </Button>
                </Modal>
              </Card.Body>
            </Card>
            </div>
          );
        })}
      </React.Fragment>
    );
  }
}

export default AllOrders;
