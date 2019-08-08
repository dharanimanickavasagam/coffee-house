import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import '../styles/Inventory.css';
import '../styles/Table.css';
import API from '../utils/API';

class Inventory extends Component {
  state = {
    products: [],
    name: '',
    price: '',
    id: '',
    isHidden: true
  };

  handleGetData = () => {
    API.get('products').then(response => {
      this.setState({ products: response.data }, () => {
        console.log('Getting');
      });
    });
  };

  componentDidMount() {
    this.handleGetData();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleAddNewProduct()
  // Adds a new product to the inventory
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleAddNewProduct = () => {
    console.log('Adding new product');
    const nameInput = this.state.name;
    const priceInput = this.state.price;

    API.post('products/', {
      name: nameInput,
      price: priceInput
    }).then(response => {
      if (true) this.handleGetData();
    });

    this.setState({ name: '' });
    this.setState({ price: 0 });
    alert('Added Successfully');
  };

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleUpdateProduct()
  // Updates an existing product in the inventory
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleUpdateProduct = event => {
    let toUpdateName = this.state.name;
    let toUpdatePrice = this.state.price;

    if (toUpdateName !== '' && toUpdatePrice !== '') {
      console.log(toUpdateName, toUpdatePrice);
    } else {
      alert('Enter Name and Price to Update');
    }
  };

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleDeleteProduct()
  // Deletes an existing product in the inventory
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleDeleteProduct = event => {
    const toBeDeleted = this.state.name;
    if (toBeDeleted) {
      const toDelete = this.state.products.filter(
        item => item.name === toBeDeleted
      );

      // console.log('To delete ', toBeDeleted, toDelete[0].id);

      API.delete('products', {
        params: {
          id: toDelete[0].id
        }
      }).then(response => {
        this.handleGetData();
      });
      this.setState({
        isHidden: false
      });
      this.setState({ name: '' });
      this.setState({ price: 0 });
      alert('Deleted Successfully');
    } else {
      alert('Choose a product');
    }
  };

  handleChangeName(value, name) {
    this.setState({
      name: value
    });
  }

  handleChangePrice(value, price) {
    this.setState({
      price: value
    });
  }

  ///////////////////////////////////////////////////////////////////////////////////
  //
  // handleAddDelete()
  // Switch for Add Order button to appear
  //
  //////////////////////////////////////////////////////////////////////////////////

  handleAddDelete = event => {
    const value = event.target.value;
    this.setState({ name: value });
    if (this.state.products.find(item => item.name === value)) {
      this.setState({
        isHidden: false
      });
    } else {
      this.setState({
        isHidden: true
      });
    }
  };

  handleOnFocus = event => {
    event.target.value = '';

    this.setState({
      isHidden: false
    });
  };

  render() {
    return (
      <React.Fragment>
        <h1> Manage Products </h1>
        <Form className="addNewProductForm">
          <Form.Group>
            <Form.Label>Product Name</Form.Label>
            <Form.Control
              value={this.state.name}
              type="text"
              list="data"
              onChange={this.handleAddDelete}
              onFocus={this.handleOnFocus}
            />

            <datalist id="data">
              {this.state.products.map(item => (
                <option key={item.id} value={item.name} />
              ))}
            </datalist>
          </Form.Group>

          <Form.Group>
            <Form.Label>Price</Form.Label>
            <Form.Control
              value={this.state.price}
              onChange={(e, price) =>
                this.handleChangePrice(e.target.value, price)
              }
              type="number"
              placeholder="Price per unit"
              min="0"
              step="0.1"
              required
            />
          </Form.Group>

          <div>
            {this.state.isHidden ? (
              <Button
                variant=" btn btn-primary btn-success"
                className="button"
                type="button"
                onClick={this.handleAddNewProduct}
              >
                Add Product
              </Button>
            ) : null}

            <Button
              variant=" btn btn-primary btn-warning"
              className="button"
              type="button"
              onClick={this.handleUpdateProduct}
            >
              Update Product
            </Button>

            <Button
              variant=" btn btn-primary btn-danger"
              className="button"
              type="button"
              onClick={this.handleDeleteProduct}
            >
              Delete Product
            </Button>
          </div>
        </Form>
        <br /> <br /> <br /> <br />
        <div className="centerMe">
          <h2> Existing Products</h2>
          <table id="productTable">
            <thead>
              <tr>
                <td> Item Number </td>
                <td> Item Name </td>
                <td> Price </td>
              </tr>
            </thead>
            <tbody>
              {this.state.products.map(product => {
                return (
                  <tr>
                    <td> {product.id} </td>
                    <td> {product.name} </td>
                    <td> {product.price} </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </React.Fragment>
    );
  }
}

export default Inventory;
