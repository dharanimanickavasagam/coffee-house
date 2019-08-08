import React from 'react';
import '../styles/Modal.css';

const modal = props => {
  return (
    <React.Fragment>
      <div
        className="modal-wrapper"
        style={{
          transform: props.show ? 'translateY(0vh)' : 'translateY(-100vh)',
          opacity: props.show ? '1' : '0'
        }}
      >
        <div className="modal-header">
          <h5>Billing Transaction</h5>
        </div>
        <div className="modal-body">
          <p>{props.children}</p>
        </div>
        <div className="modal-footer">
          <button className="btn-cancel btn-sm" onClick={props.close}>
            Cancel
          </button>
          <button className="btn-continue btn-sm" onClick={props.close}>
            Pay
          </button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default modal;
