
import React, { Component } from 'react';

import '../styles/Table.css';
import API from '../utils/API';

class SelectOptions extends Component {
  state = {
    sampleProducts: []
  };

  constructor() {
    super();
    console.log('inside constructor ');
    API.get('products').then(response => {
      console.log(response.data);
      this.setState({ sampleProducts: response.data });
    });
  }

  componentDidUpdate(prevProps, prevState) {
    if (this.props.name != prevProps.name) {
      var product = this.state.sampleProducts.find(
        data => data.name === this.props.name
      );

      if (product) {
        this.props.handlerSetStateID(product.id);
        this.props.handlerSetStatePrice(product.price);
      } else {
        this.setState({
          quantity: ' '
        });
        this.setState({
          price: ' '
        });
      }
    }
  }

  handleSetSelectedOption = event => {
    //this.props.name = event.target.value;
    // this.setState({ name: event.target.value })
    this.props.handlerSetStateName(event.target.value);
  };

  render() {
    return (
      <React.Fragment>
        <select
          class="custom-select custom-select multiple"
          required
          onChange={this.handleSetSelectedOption}
        >
          <option value=""> </option>
          {this.state.sampleProducts.map((product, index) => {
            return (
              <option key={product.id} value={product.name}>
                {product.name}
              </option>
            );
          })}
        </select>
      </React.Fragment>
    );
  }
}

export default SelectOptions;
