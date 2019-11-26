import React, { Component } from "react";
import MainLineGraph from "../graphs/MainLineGraph";
import MainStockPieGraph from "../graphs/MainStockPieGraph";
import MainStrategyPieGraph from '../graphs/MainStrategyPieGraph';
import MainStrategyRadarChart from "../graphs/MainStrategyRadarChart";
import { 
  Container,
  Row,
  Col
} from 'react-bootstrap';

class Main extends Component {
	constructor(props) {
		super(props);
		this.state = {
		}
	}

	render() {
		return (

			<Container>
				<Row>
					<Col style={{
						float: 'left',
						paddingTop:'3.5em'
					}}>
						<h6 style={{textAlign:'center'}}>Overall Position</h6>
						<MainLineGraph/>
					</Col>
					<Col>
						<div style={{marginTop:'-40px'}}><MainStrategyPieGraph/></div>
						<div style={{marginTop:'-200px'}}><MainStockPieGraph/></div>
					</Col>
				</Row>
			</Container>
			
		)
	}

}

export default Main;