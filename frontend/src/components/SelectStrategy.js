import React, { Component } from "react";
import {
	Dropdown,
	DropdownButton,
} from 'react-bootstrap';

class SelectStrategy extends Component {
	render() {
		return (
			<DropdownButton size= "lg" variant="outline-light" id="dropdown-basic-button" title="Strategy">
				<Dropdown.Item onClick={()=>this.props.onClick(1)}>Two Moving Averages</Dropdown.Item>
				<Dropdown.Item onClick={()=>this.props.onClick(1)}>Bollinger Bands</Dropdown.Item>
				<Dropdown.Item onClick={()=>this.props.onClick(1)}>Price Breakout</Dropdown.Item>
			</DropdownButton>
		);
	}
}

export default SelectStrategy;