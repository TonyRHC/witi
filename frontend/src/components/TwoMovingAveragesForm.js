import React, { Component } from "react";
import {
	Form,
	Button,
	Col
} from 'react-bootstrap';
import axios from 'axios';
import { Redirect } from 'react-router'

class TwoMovingAveragesForm extends Component {
	constructor(props) {
		super(props);
		this.state = {
			tmaStocks: [],
			symbol: '',
			volume: '',
			longTime: '',
			shortTime: '',
			toThankYou: false
		};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	componentDidMount() {
		axios.get('http://nyc31.conygre.com:31/Stock/getSymbolListOrderedBySymbol')
		.then(response => {
			this.setState({
				tmaStocks: response.data
			});
		})
		.catch(error => {
			console.log(error);
		});
	}

	handleChange(event) {
	    let target = event.target;
	    let name = target.name;
	    let value = target.value;

	    this.setState({
	      [name]: value
	    });
	}

	handleSubmit(event) {
		// alert('A symbol was submitted: ' + this.state.symbol + 
		// 	' volume: ' + this.state.volume +
		// 	' longTime: ' + this.state.longTime +
		// 	' shortTime: ' + this.state.shortTime);

		axios.post(process.env.REACT_APP_REST_API + '/addTMA',
			{
				symbol: this.state.symbol,
				volume: this.state.volume,
				longTime: this.state.longTime,
				shortTime: this.state.shortTime
			},
			{headers: {'Content-Type': 'application/json'}})
			.then(res => {
				console.log(res);
				console.log(res.data);
		})

		event.preventDefault();
		this.setState({
			toThankYou: true
		})
	}

	render() {
		if (this.state.toThankYou === true) {
      		return <Redirect to='/thankyou' />
    	}

		return (
			<Form onSubmit={this.handleSubmit}>
				<h3 style={{paddingLeft:"15px"}}>Two Moving Averages</h3>
				<Form.Row>

					<Col>
						<Form.Group as={Col} controlId="formSymbol">
					      <Form.Label>Stock</Form.Label>
					      <Form.Control 
					      	as="select"
					      	name="symbol"
					      	onChange={this.handleChange}
					      >
					        <option>Choose...</option>
					        {this.state.tmaStocks.map((value, index) => {
								return <option
									key={index}
									>{value.symbol.toUpperCase()}</option>
							})}

					      </Form.Control>
					    </Form.Group>
					</Col>

					<Col>
					  <Form.Group controlId="formVolume">
					    <Form.Label>Volume</Form.Label>
					    <Form.Control
						    name="volume"
						    required type="volume"
						    placeholder="Enter volume"
						    value={this.state.volume}
						    onChange={this.handleChange}
					    />
					  </Form.Group>
					</Col>

					<Col>
					  <Form.Group controlId="formLongTime">
					    <Form.Label>Long Time</Form.Label>
					    <Form.Control
					    	name="longTime"
					    	required type="longTime"
					    	placeholder="Enter in seconds"
					    	value={this.state.longTime}
					    	onChange={this.handleChange}
					    />
					  </Form.Group>
					</Col>

					<Col>
					  <Form.Group controlId="formShortTime">
					    <Form.Label>Short Time</Form.Label>
					    <Form.Control
					    	name="shortTime"
					    	required type="shortTime"
					    	placeholder="Enter seconds"
					    	value={this.state.shortTime}
					    	onChange={this.handleChange}
					    />
					  </Form.Group>
					</Col>
				</Form.Row>
			  <Button variant="outline-success" type="submit" style={{float:'right'}}>
			    Submit
			  </Button>
			</Form>
		);
	}
}

export default TwoMovingAveragesForm;