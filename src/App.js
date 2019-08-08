import React from 'react';
import './App.css';
import Product from './components/Product.jsx';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import image from './shared/images/dd.jpg';
import AllOrders from './components/AllOrders.jsx';
import Inventory from './components/Inventory.jsx';

function App() {
  return (
    <div className="App">
      <Router>
        <div>
          <nav class="navbar navbar-expand-lg navbar-light bg-light ">
            <a className="navbar-brand" href="/">
              <img
                src={image}
                width="30"
                height="30"
                className="mainIcon"
                alt="doughnut"
              />
              Dunkin Donuts
            </a>
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon" />
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav">
                <li class="nav-item active">
                  <Link to={'/'} className="nav-link">
                    Billing
                  </Link>
                </li>
                <li class="nav-item">
                  <Link to={'/inventory'} className="nav-link">
                    Inventory
                  </Link>
                </li>
                <li class="nav-item">
                  <Link to={'/orders'} className="nav-link">
                    Orders
                  </Link>
                </li>
              </ul>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2 ">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a class="nav-link active" href="/">
                    Log In/Out
                  </a>
                </li>
              </ul>
            </div>
          </nav>

          <Switch>
            <Route exact path="/" component={Product} />
            <Route path="/orders" component={AllOrders} />
            <Route path="/inventory" component={Inventory} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
