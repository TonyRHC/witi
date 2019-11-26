import React, { Component } from "react";
import ModalDelete from "../components/ModalDelete";
import { 
  Container,
  Row,
  Col,
  ListGroup,
  Button,
  Table,
  ButtonGroup
} from 'react-bootstrap';
import axios from 'axios';
import {
  NavLink
} from "react-router-dom";
import TwoMovingAveragesLineGraph from "../graphs/TwoMovingAveragesLineGraph";
import 'status-indicator/styles.css'
import BarLoader from 'react-spinners/BarLoader';


class Details extends Component {
	constructor(props) {
		super(props);
		this.state = {
			tmaStocks: [],
			apiLoad: 0
		};

		this.handleStop = this.handleStop.bind(this);
	}

	handleResume(id) {
		axios.post(process.env.REACT_APP_REST_API + '/TMA/runTMA',
			{
				id: id
			},
			{headers: {'Content-Type': 'application/json'}})
			.then(res => {
				this.componentDidMount();
		});
	}

	handlePause(id) {
		axios.post(process.env.REACT_APP_REST_API + '/TMA/pauseTMA',
			{
				id: id
			},
			{headers: {'Content-Type': 'application/json'}})
			.then(res => {
				this.componentDidMount();
		});
	}

	handleStop(id) {
		axios.post(process.env.REACT_APP_REST_API + '/TMA/stopTMA',
			{
				id: id
			},
			{headers: {'Content-Type': 'application/json'}})
			.then(res => {
				this.componentDidMount();
		})
	}

	buttonStatus(status, id) {
		if (status === "running") {
			return (
				<Button 
					variant="warning"
					onClick={
						()=> {
							this.handlePause(id)
						}
					}
				>Pause</Button>
				);
		} else if (status === "paused") {
			return(
				<Button 
					variant="success"
					onClick={
						()=> {
							this.handleResume(id)
						}
					}
				>Resume</Button>
				);
		} else {
			return(
				<Button
					variant="secondary"
					disabled
				>Pause</Button>
				)
		}
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
		let graph;
		if (this.props.strategyName === "Two Moving Averages") {
			graph = <TwoMovingAveragesLineGraph/>
		} else {
			graph = <TwoMovingAveragesLineGraph/>
		}


		return (
			<Container style={{paddingTop:'2em'}}>

				<Row>
					<Col style={{paddingTop:'5em'}}>
						<h2>{this.props.strategyName}</h2>
					</Col>

					<Col>
						{graph}
					</Col>
				</Row>


				<Row style={{paddingTop:"2em"}}>


				{this.state.apiLoad === 1 ? 
					<Table striped style={{
						color:'#fafafa',
					}}>
					  <thead style={{textAlign:"center"}}>
					    <tr>
					      <th><h6>Stock</h6></th>
					      <th><h6>Volume</h6></th>
  					      <th><h6>Short</h6></th>
					      <th><h6>Long</h6></th>
					      <th><h6>P/L</h6></th>
					      <th><h6>Status</h6></th>
					      <th></th>
					      <th></th>
					      <th></th>
					    </tr>
					  </thead>

					  <tbody style={{textAlign:"center"}}>

						{this.state.tmaStocks.map((value, i) => {
							return (
								<tr key="i">
									<td>{value.stockNames.toUpperCase()}</td>
									<td>{value.volume}</td>
									<td>{value.shortTime}</td>
									<td>{value.longTime}</td>
									<td>{value.profits}</td>
									<td>{this.statusDot(value.status)}</td>
									<td>
										<NavLink to="/moredetails" onClick={ ()=>
											this.props.onClick(value)
										}
										>
											<Button variant="info">Info</Button>
										</NavLink>
									</td>
									<td>
											{this.buttonStatus(value.status, value.id)}

									</td>
									<td>
										{
											!(value.status === "stopped") ?
											<ModalDelete
													onClick={this.handleStop}
													id={value.id}
											/> :
											<Button variant="secondary" disabled>Stop</Button>
										}
									</td>
								</tr>
							);
						})}
					  </tbody>
					</Table>
					: <BarLoader color='white'/>
				}

				</Row>

			</Container>
		);
	}

	componentDidMount() {
		axios.get(process.env.REACT_APP_REST_API + '/TMA/getTMA')
		.then(response => {
			this.setState({
				tmaStocks: response.data,
				apiLoad: 1
			});
		})
		.catch(error => {
			console.log(error);
		});
	}
}

export default Details;