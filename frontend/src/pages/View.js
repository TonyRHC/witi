import React, { Component } from "react";
import TwoMovingAveragesLineGraph from "../graphs/TwoMovingAveragesLineGraph";
import BollingerBandsLineGraph from "../graphs/BollingerBandsLineGraph"
import PriceBreakoutLineGraph from "../graphs/PriceBreakoutLineGraph"
import { 
  Container,
  Row,
  Col,
  Button,
} from 'react-bootstrap';
import {
  NavLink
} from "react-router-dom";
import axios from 'axios';

class View extends Component {
	constructor(props) {
		super(props);
		this.state = {
			tmaUniqueVolume: {
				count:'',
				volume:'',
			},
		}
	}

	render() {
		return (

			<Container style={{paddingTop:'2em'}}>

				<Row>
					<Col>
						<h2>Two Moving Averages</h2>

						<div id="divTopMargin">
							<p>Unique Stocks: {this.state.tmaUniqueVolume.count}</p>
							<p>Volume: {this.state.tmaUniqueVolume.volume}</p>

							<NavLink to="/details">
								<Button
									variant="dark"
									onClick={ ()=>
										this.props.onClick("Two Moving Averages")
									}
								>
									More Info
								</Button>
							</NavLink>
						</div>
					</Col>
					<Col>
						<TwoMovingAveragesLineGraph/>
					</Col>
				</Row>

				<hr/>

				<Row>
					<Col>
						<h2>Bollinger Bands</h2>

						<div id="divTopMargin">
							<p>Unique Stocks: 54</p>
							<p>Volume: 4352</p>

							<NavLink to="/details">
								<Button 
									variant="dark"
									onClick={ ()=>
										this.props.onClick("Bollinger Bands")
									}
								>
									More Info
								</Button>
							</NavLink>
						</div>
					</Col>
					<Col>
						<BollingerBandsLineGraph/>
					</Col>
				</Row>

				<hr/>

				<Row>
					<Col>
						<h2>Price Breakout</h2>

						<div id="divTopMargin">
							<p>Unique Stocks: 23</p>
							<p>Volume: 8723</p>

							<NavLink to="/details">
								<Button 
									variant="dark"
									onClick={ ()=>
										this.props.onClick("Price Breakout")
									}
								>
									More Info
								</Button>
							</NavLink>
						</div>
					</Col>
					<Col>
						<PriceBreakoutLineGraph/>
					</Col>
				</Row>

				<hr/>

			</Container>
		);
	}

componentDidMount() {
    axios.get(process.env.REACT_APP_REST_API + '/viewAll/getTMAVolumeAndUniqueStocks')
    .then(response => {
      this.setState({
        tmaUniqueVolume: response.data
      });
    })
    .catch(error => {
      console.log(error);
    });
  }

}

export default View;