import React, { Component } from "react";
import './App.css';
import Main from "./pages/Main";
import View from "./pages/View";
import Add from "./pages/Add";
import Details from "./pages/Details";
import MoreDetails from "./pages/MoreDetails";
import ThankYou from "./pages/ThankYou";

import {
  Route,
  NavLink,
  HashRouter
} from "react-router-dom";

import { 
  Button,
  ButtonToolbar,
  ButtonGroup,
  Container,
  Row,
  Col
} from 'react-bootstrap';

class App extends Component {

  constructor (props) {
    super(props);

    this.state = { 
      rSelected: 1,
      viewStrategyName: '',
      detailsStock: {}
    };

    this.onRadioBtnClick = this.onRadioBtnClick.bind(this);
    this.handleViewClick = this.handleViewClick.bind(this);
    this.handleDetailsClick = this.handleDetailsClick.bind(this);
  }

  onRadioBtnClick(rSelected) {
    this.setState({ rSelected });
  }

  handleViewClick(strategyName) {
    this.setState({viewStrategyName: strategyName});
  }

  handleDetailsClick(stock) {
    this.setState({
      detailsStock: {
        stockName: stock.stockNames,
        id: stock.id,
        status: stock.status
      }
    });
  }

  render() {
    return (
      <HashRouter>
        <Container>

          <Row>

            <Col>
              <ButtonToolbar className="navigation">
                <ButtonGroup className="mr-2">
                  <NavLink exact to="/">
                    <Button variant="outline-light" onClick={() => this.onRadioBtnClick(1)} active={this.state.rSelected === 1}>Main</Button>
                  </NavLink>
                </ButtonGroup>
                <ButtonGroup className="mr-2">
                  <NavLink to="/view">
                    <Button variant="outline-light" onClick={() => this.onRadioBtnClick(2)} active={this.state.rSelected === 2}>View</Button>
                  </NavLink>
                </ButtonGroup>
                <ButtonGroup className="mr-2">
                  <NavLink to="/add">
                    <Button variant="outline-light" onClick={() => this.onRadioBtnClick(3)} active={this.state.rSelected === 3}>Add</Button>
                  </NavLink>
                </ButtonGroup>
              </ButtonToolbar>
            </Col>

            <Col xs={7}>
              <h1 className="head" style={{fontSize: '5em'}}>WITI</h1>
            </Col>

          </Row>

          <Row className="justify-content-md-center">
            <div className="content">
              <Route exact path="/" component={ Main } />
              <Route path="/Add" component={ Add } />
              <Route path="/ThankYou" component={ ThankYou } />
              <Route
                path="/View"
                render={ props=> <View onClick={this.handleViewClick}/> }
              />
              <Route
                path="/Details"
                render={ props=> 
                  <Details 
                    strategyName={this.state.viewStrategyName}
                    onClick={this.handleDetailsClick}
                  />
                }
              />
              <Route
                path="/MoreDetails"
                render={ props=> <MoreDetails stock={this.state.detailsStock}/> }
              />
            </div>
          </Row>
          
        </Container>
      </HashRouter>
    );
  }
}

export default App;
