import React, { Component } from 'react';
import Modal from './Modal.jsx';
import API from '../utils/API';

class BillingOrder extends Component {
  constructor() {
    super();
    this.state = {
      isShowing: false,
      tenderedAmount: 0,
    };
  }

  openModalHandler = () => {
    const customerName = this.props.customerName;
    const customerMobile = parseInt(this.props.customerMobile);

    var products = this.props.products.map(product => {
      return {
        product_id: product.id,
        product_name: product.name,
        unitPrice: product.price,
        quantity: product.quantity,
        netPrice: product.netprice,
      };
    });

    console.log(products);

    API.post('billorder', {
      orderDetails: {
        orders: {
          totalPrice: this.props.totalPrice,
          tax: this.props.tax,
          tenderedAmount: this.state.tenderedAmount,
        },
        customer: {
          customerName: customerName,
          customerMobile: customerMobile,
        },
        orderItems: products,
      },
    })
      .then(response => {
        this.setState({
          isShowing: true,
        });
      })
      .catch(error => {
        if (error.response) {
          this.setState({
            isShowing: false,
          });
          alert('Customer is already existing !');
        }
      });
  };

  handleExistingProducts = toRemove => {
    let netPrice;
    const removed = this.props.products.filter(key => {
      if (key !== toRemove) {
        return 1;
      } else {
        netPrice = key.netprice;
        return 0;
      }
    });

    if (removed) {
      // console.log('After Removal : ', removed);
      this.props.handleExistingProducts(removed, netPrice);
    }
  };

  closeModalHandler = () => {
    this.setState({ name: ' ' });
    this.setState({ price: 0 });
    this.setState({
      isShowing: false,
    });
  };

  continueFunction = () => {
    // your function body goes here
  };

  render() {
    return (
      <React.Fragment>
        <table id="productTable">
          <thead>
            <tr>
              <td> Item No </td>
              <td> Item Name </td>
              <td> Unit Price </td>
              <td> Quantity </td>
              <td> Net Price </td>
            </tr>
          </thead>
          <tbody>
            {this.props.products.map(product => {
              return (
                <React.Fragment>
                  <tr>
                    <td>{product.id} </td>
                    <td>{product.name} </td>
                    <td>{product.price} </td>
                    <td>{product.quantity} </td>
                    <td>{product.netprice} </td>
                    <button
                      type="button"
                      class="btn btn-default delete btn-sm"
                      onClick={() => this.handleExistingProducts(product)}
                    >
                      X
                    </button>
                  </tr>
                </React.Fragment>
              );
            })}
            {this.props.handleResetprice}
          </tbody>
        </table>

        <table id="billingTable">
          <tr>
            <td> Total</td>
            <td> ${this.props.totalPrice}</td>
          </tr>

          <tr>
            <td> MI State Tax at 6% </td>
            <td> ${this.props.tax}</td>
          </tr>

          <tr>
            <td> Tendered Amount </td>
            <td>
              $
              {
                // eslint-disable-next-line react/no-direct-mutation-state
                (this.state.tenderedAmount =
                  this.props.tax + this.props.totalPrice)
              }
            </td>
          </tr>
        </table>

        {this.state.isShowing ? <div onClick={this.closeModalHandler} /> : null}

        <button
          className="btn btn-large btn-success open-modal-btn"
          type="button"
          onClick={this.openModalHandler}
        >
          Bill Order
        </button>
        <Modal
          className="modal"
          show={this.state.isShowing}
          close={this.closeModalHandler}
        >
          Total Tendered : {this.state.tenderedAmount}
        </Modal>
      </React.Fragment>
    );
  }
}

export default BillingOrder;
