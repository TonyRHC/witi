import React, { Component } from "react";
import MoreDetailsLineGraph from "../graphs/MoreDetailsLineGraph";
import { 
  Container,
  Row,
  Col,
  ListGroup,
  Button,
  Table
} from 'react-bootstrap';
import axios from 'axios';
import BarLoader from 'react-spinners/BarLoader';

class MoreDetails extends Component {
	constructor(props) {
		super(props);
		this.state = {
			tmaHistory: [],
			apiLoad: 0
		};
	}

	statusDot(status) {

		if (status === "running") {
			return (
				<status-indicator positive pulse></status-indicator>
				);
		} else if (status === "paused") {
			return(
				<status-indicator intermediary pulse></status-indicator>
				);
		} else {
			return(
				<status-indicator negative pulse></status-indicator>
				);
		}
	}

	render() {
		return (
			<Container style={{paddingTop:'2em'}}>
				<Row>
					<Col style={{paddingTop:'5em'}}>
					<div style={{textAlign:'center'}}>
						<h2>{this.props.stock.stockName}</h2>
						{this.statusDot(this.props.stock.status)}
					</div>
					</Col>

					<Col>
						<MoreDetailsLineGraph id={this.props.stock.id}/>
					</Col>
				</Row>

				<Row style={{paddingTop:"2em"}}>

				{this.state.apiLoad === 1 ? 
					<Table striped size="sm" style={{
						color:'#fafafa',
					}}>
					  <thead style={{textAlign:"center"}}>
					    <tr>
					      <th><h6>Transaction ID</h6></th>
					      <th><h6>Action</h6></th>
					      <th><h6>Time</h6></th>
							<th><h6>Price</h6></th>
					      <th><h6>P/L</h6></th>
					    </tr>
					  </thead>

					  <tbody style={{textAlign:"center"}}>
						{this.state.tmaHistory.map((value, index) => {
							return (
								<tr key="index">
									<td>{value.id}</td>
									<td>{value.action}</td>
									<td>{value.time}</td>
									<td>{value.price}</td>
									<td>{value.performance}</td>
								</tr>
							);
						})}
					  </tbody>
					</Table>
					:
					<BarLoader color='white'/>
				}

				</Row>
			</Container>
		);
	}


	componentWillMount() {
		axios.get(process.env.REACT_APP_REST_API + '/viewDetails/strategyHistory', {
			params: {
				id: this.props.stock.id
			}
		})
		.then(response => {
			this.setState({
				tmaHistory: response.data,
				apiLoad: 1
			});
		})
		.catch(error => {
			console.log(error);
		});
	}

}

export default MoreDetails;