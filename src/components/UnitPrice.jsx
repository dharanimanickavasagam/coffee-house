import React, { Component } from 'react';

class UnitPrice extends Component {
  state = {};

  render() {
    return (
      <React.Fragment>
        <input
          type="text"
          placeholder="Price per Unit"
          class="form-control price"
          value={this.props.price}
          readOnly
        />
      </React.Fragment>
    );
  }
}

export default UnitPrice;
