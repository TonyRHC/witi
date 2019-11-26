import React, { Component } from "react";
import SelectStrategy from "./SelectStrategy";
import TwoMovingAveragesForm from "./TwoMovingAveragesForm";

class AddForm extends Component {
	constructor(props) {
		super(props);
		this.state = {
			page: 0
		};

		this.handleClick = this.handleClick.bind(this);
	}

	handleClick(i) {
		switch (i) {
			case 1: //Two Moving Averages
				this.setState({
					page: 1
				});
				break;
			case 2: //Bollinger Bands
				break;
			case 3: //Price Breakout
				break;
			default:
				this.setState({
					page: 0
				});
		}
	}

	render() {
		let form;

		switch (this.state.page) {
			case 0:
				form = 
				<SelectStrategy
					onClick={(i)=> this.handleClick(i)}
				/>;
				break;
			case 1:
				form =
				<TwoMovingAveragesForm/>
				break;
			default:
				form = 
				<SelectStrategy
					onClick={(i)=> this.handleClick(i)}
				/>;
		}



		return (
			form
		);
	}
}

export default AddForm;