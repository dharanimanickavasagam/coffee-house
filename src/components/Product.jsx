/* eslint-disable react/no-direct-mutation-state */
import React, { Component } from 'react';
import '../styles/Product.css';
import SelectOptions from './SelectOptions.jsx';
import UnitPrice from './UnitPrice.jsx';
import BillingOrder from './BillingOrder.jsx';
import Clock from 'react-live-clock';

class Product extends Component {
  state = {
    name: '',
    price: '',
    id: '',
    quantity: '',
    tempPrice: '',
    products: [],
    product: {
      id: '',
      name: '',
      netprice: '',
      price: '',
      quantity: '',
    },
    customerName: null,
    customerMobile: '',
    tax: 0,
  };

  // total tendered price
  totalPrice = 0;
  tax = 0;

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleAddCart()
  // Adds products to billing section
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleAddCart = () => {
    this.totalPrice = 0;

    if (this.state.quantity === '') {
      alert('Enter Quantity');
    } else {
      this.state.product.id = this.state.id;
      this.state.product.name = this.state.name;
      this.state.product.quantity = this.state.quantity;
      this.state.product.price = this.state.price;
      this.state.product.netprice = this.state.tempPrice;

      this.state.products.push(this.state.product);

      this.state.products.every(product => {
        this.totalPrice = this.totalPrice + product.netprice;
        return this.totalPrice;
      });

      this.handleTax();

      this.setState({ product: {} });
      this.setState({ quantity: '' });
      this.setState({ price: '' });
      this.setState({ totalPrice: '' });
      this.setState({ name: '' }, () => {
        this.state.name = null;
      });
    }
  };

  handleTax = () => {
    this.setState({ tax: 0 });
    this.tax = (this.totalPrice * 6) / 100;
    this.setState({ tax: this.tax }, () => {
      console.log(this.state.tax);
    });
  };

  handleResetPrice = () => {
    this.setState({ price: 1 });
  };

  handlerSetStateName = name => {
    this.setState(
      {
        name: name,
      },
      () => {
        this.setState({
          name: name,
        });
      }
    );
  };

  handlerSetStateID = id => {
    this.setState({
      id: id,
    });
  };

  handlerSetStatePrice = price => {
    this.setState({
      price: price,
    });
  };

  handleSetQuantity = event => {
    this.setState({ quantity: event.target.value });
    this.setState({
      tempPrice: this.state.price * event.target.value,
    });
  };

  handleSetCustomerName = event => {
    this.setState({ customerName: event.target.value });
  };

  handleSetCustomerMobile = event => {
    this.setState({ customerMobile: event.target.value });
  };

  handleExistingProducts = (product, netPrice) => {
    this.totalPrice = this.totalPrice - netPrice;
    this.handleTax();
    // console.log('To be removed price : ', netPrice);
    // console.log('Total price : ', this.totalPrice);

    this.setState({ products: product });
  };

  render() {
    let dateObj = new Date();

    let date = dateObj.getDate();
    let month = dateObj.getMonth();

    let year = dateObj.getFullYear();

    return (
      <form>
        <h1>Dunkin Donuts</h1>
        <div id="date" className="dateSpan">
          Today : {month + 1}-{date}-{year}, Time :{' '}
          <Clock format={'HH:mm:ss'} ticking={true} timezone={'US/Eastern'} />
        </div>
        <div class="row">
          <div class="column needs-validation">
            <span>
              <SelectOptions
                id={this.state.id}
                value={this.state.name}
                price={this.state.price}
                quantity={this.state.quantity}
                name={this.state.name}
                handlerSetStateName={this.handlerSetStateName}
                handlerSetStateID={this.handlerSetStateID}
                handlerSetStatePrice={this.handlerSetStatePrice}
              />
            </span>

            <span>
              <input
                id="quantity"
                type="text"
                class="form-control error"
                placeholder="Quantity"
                min="1"
                value={this.state.quantity}
                onChange={this.handleSetQuantity}
                required
              />
            </span>
            <span>
              <UnitPrice price={this.state.price}> </UnitPrice>
            </span>

            <div>
              <button
                className="btn btn-large btn-success"
                onClick={this.handleAddCart}
                type="button"
              >
                Add to Cart
              </button>
            </div>
          </div>

          <div class="column">
            <span>
              <input
                type="text"
                class="form-control"
                value={this.state.customerName}
                placeholder="Customer Name"
                onChange={this.handleSetCustomerName}
              />
            </span>
            <span>
              <input
                type="text"
                value={this.state.customerMobile}
                class="form-control"
                placeholder="Mobile Number"
                onChange={this.handleSetCustomerMobile}
              />
            </span>
            <BillingOrder
              id={this.state.id}
              price={this.state.price}
              quantity={this.state.quantity}
              name={this.state.name}
              products={this.state.products}
              totalPrice={this.totalPrice}
              tax={this.state.tax}
              customerName={this.state.customerName}
              customerMobile={this.state.customerMobile}
              handleResetPrice={this.handleResetPrice}
              handleExistingProducts={this.handleExistingProducts}
            />
          </div>
        </div>
      </form>
    );
  }
}

export default Product;
